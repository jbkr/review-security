package hello.world.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hello.world.v1.model.Member;
import hello.world.v1.repository.MemberRepository;

@Service
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Member member = memberRepository.findByUsername(username);

		if (member == null) {
			throw new UsernameNotFoundException(username);
		}

		return User.builder().username(member.getUsername()).password(member.getPassword()).roles(member.getRole())
				.build();
	}

	// 비밀번호 암호화 : Encoding
	public Member joinMember(Member member) {
//		member.encodePassword();
		member.encodePassword(passwordEncoder);
		return memberRepository.save(member);
	}

}
