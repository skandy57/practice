package sol.one.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import sol.one.VO.MemberVO;
import sol.one.service.MemService;

@Controller
@Log4j
@RequestMapping("/member/*")
@AllArgsConstructor
public class MemController {
	
	private MemService service;
	
	//로그인 폼으로 이동
	@GetMapping("/login")
	public String loginForm() {
		return "/member/login";
	}
	
	//로그인 처리
	@PostMapping("/loginProc")
	public String loginProc(@ModelAttribute MemberVO member, HttpSession session) {
		
		MemberVO loginMem = service.getMemById(member);
		log.info("loginProc loginMem = " + loginMem);
		
		if(loginMem == null) {
			log.info("로그인 실패");
			return "/member/login";
		} else {
			// 로그인 성공 하면 service에서 반환 받은 loginMem 을 세션에 담아서 메인 페이지로 이동한다
			session.setAttribute("mem", loginMem);
			return "redirect:/loginMain";
		}
	}
	
	//로그아웃 처리
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	//마이페이지 이동
	@GetMapping("/myPage")
	public String myPage(@ModelAttribute MemberVO member, HttpSession session) {
		log.info("myPage s = " + session);
		log.info("myPage s1 = " + session.getAttribute("mem"));
		
		return "/member/myPage";
	}
	
	//회원가입 폼으로 이동
	@GetMapping("/join")
	public void joinForm() {}
	
	//회원가입 처리
	@PostMapping("/joinProc")
	public String joinProc(@Valid MemberVO member, BindingResult r) {
		log.info("1. joinProc member ========== " + member);
		if(r.hasErrors()) {
			return "/join";
		}
		
		//가입된 메일인지 확인 후 중복이면 가입 안됨
		
		  int chkMail = service.mailChk(member.getEmail()); 
		  if(chkMail != 0) {
			  System.out.println("MC81: 가입된 메일입니다"); 
			  return "/member/join"; 
		  } else {
			  service.registerMem(member); 
			  return "redirect:/member/login"; 
		  }
		 
		
	}
	
	
	
	//메일 중복 확인
	@PostMapping("/mailChk")
	@ResponseBody
	public int mailChk(@RequestParam("email") String email) {
		System.out.println("넘어온 메일 값: "+email);

		int result = service.mailChk(email);
		System.out.println("반환받은 체크 값: "+result);
		return result; 
	}

}
