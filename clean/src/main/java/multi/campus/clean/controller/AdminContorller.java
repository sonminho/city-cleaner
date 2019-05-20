package multi.campus.clean.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import multi.campus.clean.domain.GarbageCollection;
import multi.campus.clean.domain.HandleMsg;
import multi.campus.clean.domain.PageInfo;
import multi.campus.clean.domain.ResultMsg;
import multi.campus.clean.domain.User;
import multi.campus.clean.service.GarbageCollectionService;
import multi.campus.clean.service.UserService;

@Controller
@Slf4j
public class AdminContorller {
	@Autowired
	UserService userService;
	
	@Autowired
	GarbageCollectionService garbageCollectionService;
	
	Gson gson = new Gson();
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "redirect:/admin/list";
	}
	
	@GetMapping("/admin/list")
	public String getList(@RequestParam(value="page", defaultValue="1") int page, Model model) throws Exception {
		PageInfo<User> pi = userService.getPage(page);
		model.addAttribute("pi", pi);		
		
		return "admin/list";
	}
	
	@GetMapping("/admin/edit/{userid}")
	public String getEdit(@PathVariable String userid,Model model) throws Exception {
		User user = userService.getUser(userid);
		System.out.println("수정할 회원> "+ user);
		model.addAttribute("user", user);
		
		return "admin/edit";
	}
	
	@PostMapping("/admin/edit/{userid}")
	public String postEdit(@PathVariable String userid, User user, Model model) throws Exception {		
		System.out.println("수정할 회원 " + user);
		
		if(userService.update(user) > 0)		
			return "redirect:/admin/list";
		else {
			model.addAttribute("user", user);
			return "admin/edit";
		}
	}
	
	@GetMapping("/admin/monitor")
	public void getMonitor(Model model) throws Exception {
		List<User> list = userService.getUsers();
		
		Gson gson = new Gson();
		String gsonList = gson.toJson(list);
		model.addAttribute("userList", gsonList);
	}
	
	
	@PostMapping("/admin/capUpdate")
	@ResponseBody
	public ResponseEntity<ResultMsg> checkId(@RequestBody HandleMsg user) throws Exception {	    
		System.out.println("사용자로부터 입력 받은 아이디 " + user.getUserid() + " 용량 :" + user.getCap());
		User searchedUser = userService.getUser(user.getUserid());
		System.out.println("검색된 회원 " + searchedUser);
		
		if (searchedUser != null) {
			searchedUser.setCap(user.getCap());
			userService.update(searchedUser);
			return ResultMsg.response("ok", "갱신되었습니다.");
		} else {
			return ResultMsg.response("fail", "잘못된 접근입니다.");
		}
	}
	
	@GetMapping("/admin/collectingList")
	@ResponseBody
	public ResponseEntity<ResultMsg> getCollectingList(Model model) throws Exception {
		List<User> collectingList = userService.getCollectingList();
		Gson gson = new Gson();
		
		if(collectingList.size() > 0) {
			model.addAttribute("collectingList", gson.toJson(collectingList));
			return ResultMsg.response("ok", gson.toJson(collectingList));
		} else {
			return ResultMsg.response("fail", gson.toJson(collectingList));
		}
	}
	
	@PostMapping("/admin/updateCollectingList")
	@ResponseBody
	public ResponseEntity<ResultMsg> postUpdateCollectingList(@RequestBody User user) throws Exception {
		String updateCondition = user.getCondition();
		User updatedUser = userService.getUser(user.getUserid());
		updatedUser.setCondition(updateCondition);
		
		if(userService.update(updatedUser) > 0) {
			return ResultMsg.response("ok", "리스트 추가 성공");
		} else
			return ResultMsg.response("fail", "리스트 추가 실패");
	}
	
	@GetMapping("/admin/collection-list")
	public String getCollectionList(@RequestParam(value="page", defaultValue="1") int page, Model model) throws Exception {
		PageInfo<GarbageCollection> pi = garbageCollectionService.getPage(page);
		model.addAttribute("pi", pi);	
		
		System.out.println("수집 리스트 사이즈 " + pi);
		return "admin/collection-list";
	}
}
