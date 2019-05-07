package multi.campus.clean.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import multi.campus.clean.domain.LoginInfo;
import multi.campus.clean.domain.ResultMsg;
import multi.campus.clean.domain.User;
import multi.campus.clean.service.UserService;

@Controller
@Slf4j
public class UserContorller {
	@Autowired
	UserService service;
	
	@GetMapping("/user/login")
	public String getLogin(LoginInfo loginInfo, @ModelAttribute("target") String target,
			@ModelAttribute("reason") String reason) {
		loginInfo.setTarget(target);
		loginInfo.setReason(reason);

		return "/user/login";
	}

	@PostMapping("/user/login")
	public String postLogin(@Valid LoginInfo loginInfo, BindingResult result, HttpServletRequest req, Model model)
			throws Exception {
		System.out.println("입력받은 회원 " + loginInfo.getUserid());

		if (result.getFieldError("userid") != null || result.getFieldError("passwd") != null) {
			log.info("[사용자 로그인] : 잘못된 입력");
			// log.info(result.toString());
			return "/user/login";
		}

		User searchedUser = service.getUser(loginInfo.getUserid());
		// System.out.println("검색된 회원 " + searchedUser);
		if (searchedUser != null) {
			String target = loginInfo.getTarget();
			if (searchedUser.getPasswd().equals(loginInfo.getPasswd())) {
				HttpSession session = req.getSession();
				session.setAttribute("USER", searchedUser);

				if (target != null && !target.isEmpty()) {
					return "redirect:" + target;
				} else {
					return "redirect:/";
				}
			}
			return "redirect:" + target;
		} else {
			result.reject("fail", "사용자 아이디 또는 비밀번호가 일치하지 않습니다.");
			return "/user/login";
		}
	}


	@GetMapping("/user/join")
	public String getJoin(User user) {
		return "/user/join";
	}

	@PostMapping("/user/join")
	public String postJoin(@Valid User user, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			log.info("[사용자 회원가입] : 잘못된 입력");
			log.info(result.toString());
			return "/user/join";
		}

		if (service.create(user) > 0) {
			System.out.println(user.getUserid() + "회원가입 성공");
			return "redirect:/";
		}

		return "redirect:/";
	}
	
	/*
	 * Ajax request from join.jsp 사용자 아이디 중복확인
	 */
	@GetMapping("/user/id-check/{userid}")
	@ResponseBody
	public ResponseEntity<ResultMsg> checkId(@PathVariable String userid) throws Exception {
		System.out.println("사용자로부터 입력 받은 아이디 " + userid);
		if (service.getUser(userid) == null) {
			System.out.println("사용가능한 아이디");
			return ResultMsg.response("ok", "사용가능한 아이디 입니다.");
		} else {
			System.out.println("사용 불가능한 아이디");
			return ResultMsg.response("duplicate", "이미 사용중인 아이디 입니다.");
		}
	}

	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		System.out.println("로그아웃");
		return "redirect:/";
	}
}
