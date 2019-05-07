package multi.campus.clean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import multi.campus.clean.domain.PageInfo;
import multi.campus.clean.domain.User;
import multi.campus.clean.service.UserService;

@Controller
@Slf4j
public class AdminContorller {
	
	@Autowired
	UserService service;
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "admin/main";
	}
	
	@GetMapping("/admin/list")
	public String getList(@RequestParam(value="page", defaultValue="1") int page, Model model) throws Exception {
		PageInfo<User> pi = service.getPage(page);
		model.addAttribute("pi", pi);		
		
		return "admin/list";
	}
}
