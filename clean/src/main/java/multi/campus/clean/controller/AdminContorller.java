package multi.campus.clean.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import multi.campus.clean.domain.PageInfo;
import multi.campus.clean.domain.ResultMsg;
import multi.campus.clean.domain.User;
import multi.campus.clean.service.UserService;

@Controller
@Slf4j
public class AdminContorller {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "admin/list";
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
		//model.addAttribute("userList", list);
		Gson gson = new Gson();
		String gsonList = gson.toJson(list);
		System.out.println(gsonList);
		model.addAttribute("userList", gsonList);
		System.out.println("---쓰레기통 설치 사용자 리스트---");
		for(User user : list) {
			System.out.println(user);
		}
	}
	
	
	@GetMapping("/admin/capUpdate")
	@ResponseBody
	public ResponseEntity<ResultMsg> checkId(User user) throws Exception {	    
		System.out.println("사용자로부터 입력 받은 아이디 " + user.getUserid());
		if (userService.getUser(user.getUserid()) == null) {
			System.out.println("사용가능한 아이디");
			return ResultMsg.response("ok", "사용가능한 아이디 입니다.");
		} else {
			System.out.println("사용 불가능한 아이디");
			return ResultMsg.response("duplicate", "이미 사용중인 아이디 입니다.");
		}
	}
	
}
