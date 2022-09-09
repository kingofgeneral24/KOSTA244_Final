package com.kosta.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.finalproject.dto.MemberDTO;
import com.kosta.finalproject.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @packageName    : com.kosta.finalproject.controller
* @fileName        : MemberController.java
* @author        : Hye
* @date            : 2022.09.09
* @description   : 회원 관리 컨트롤러
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2022.09.09        Hye       최초 생성
*/

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")

public class MemberController {
	private final MemberService memberService;

	/**
	* @methodName    : joinForm
	* @author        : Hye
	* @date        : 2022.09.09
	* @description   :  회원가입 페이지 이동
	* @return
	*/
	@GetMapping("/joinForm")
	public String joinForm() {
		return "member/joinForm";
	}	
	
	/**
	* @methodName    : myPageForm
	* @author        : Hye
	* @date        : 2022.09.09
	* @description   : 마이페이지 상세보기 이동
	* @return
	*/
	@GetMapping("/myPageForm")
	public String myPageForm() {
		return "member/myPage";
	}	

	/*마이페이지 수정페이지 이동*/
	@GetMapping("/myPageUpdateForm")
	public String myPageUpdateForm() {
		return "member/myPageUpdateForm";
	}	

	/*비밀번호 수정페이지 이동*/
	@GetMapping("/passwordUpdateForm")
	public String passwordUpdateForm() {
		return "member/passwordUpdateForm";
	}	
	
	@PostMapping("/save")
	public String save(@ModelAttribute MemberDTO memberDTO) {
		
		log.info("memberDTO : "+memberDTO.toString());
		
		memberService.save(memberDTO);
		return "redirect:/login/loginForm";
	}
	
	@GetMapping("/")
	public String findAll(Model model) {
		List<MemberDTO> memberDTOList =memberService.findAll();
		model.addAttribute("memberList", memberDTOList);
		return "member/list";
		
	}
	
	
	/**
	* @methodName    : findById
	* @author        : Hye
	* @date        : 2022.09.09
	* @description   :  아이디 중복검사
	* @param memberId
	* @param model
	* @return
	*/
	@GetMapping("/idChk/{memberId}")
	@ResponseBody
	public String findByMemberId(@PathVariable String memberId) {
		
		String result = "";
		
		MemberDTO memberDTO = memberService.findById(memberId);
		
		if(memberDTO != null) {
			result = "N";
		}else {
			result = "Y";
		}
		
		return result;
		
	}
	//ajax상세 조회
	@PostMapping("/ajax/{id}")
	public @ResponseBody MemberDTO findByIdAjax(@PathVariable String memberId) {
		MemberDTO memberDTO = memberService.findById(memberId);
		return memberDTO;
	}
	
	//get 요청 삭제 /member/delete/3
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id ) {
		memberService.delete(id);
		return "redirect:/member/loginForm";
	//	return"memberPages.list";//X
	}
	
	 /**
	  * /member/3: 조회(get) R, 저장(post) C, 수정(put) U, 삭제(delete)  D
	  */
	
	
	
		//delete요청 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity deleteAjax(@PathVariable Long id) {
		memberService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK); //ajax 호출한 부분에 리턴으로 200 응답을 줌.
		
	}
	}
