package hello.world.v1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hello.world.v1.dto.MemberDto;
import hello.world.v1.model.Member;
import hello.world.v1.repository.MemberRepository;
import hello.world.v1.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final ModelMapper modelMapper;
	private final MemberRepository memberRepository;
	private final MemberService memberService;

	@GetMapping("/join")
	public String join() {
		return "join";
	}

	@PostMapping("/join/{role}")
	public String createMember(@ModelAttribute MemberDto memberDto, Model model) {
		// DTO to entity
		Member entity = modelMapper.map(memberDto, Member.class);
		
		// save
//		Member savedEntity = memberRepository.save(entity);
		Member savedEntity = memberService.joinMember(entity);

		// Entity to DTO
		MemberDto savedDto = modelMapper.map(savedEntity, MemberDto.class);

		model.addAttribute("member", savedDto);

		return "join-success";
	}

}
