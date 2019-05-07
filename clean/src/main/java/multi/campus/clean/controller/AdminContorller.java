package multi.campus.clean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import multi.campus.clean.domain.PageInfo;
import multi.campus.clean.domain.User;
import multi.campus.clean.service.UserService;

@Controller
@Slf4j
public class AdminContorller {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "admin/main";
	}
	
	@GetMapping("/admin/list")
	public String getList(@RequestParam(value="page", defaultValue="1") int page, Model model) throws Exception {
		PageInfo<User> pi = userService.getPage(page);
		model.addAttribute("pi", pi);		
		
		return "admin/list";
	}
	
	@GetMapping("/admin/edit/{userid}")
	public String getEdit(@PathVariable String userid, Model model) throws Exception {
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
}
