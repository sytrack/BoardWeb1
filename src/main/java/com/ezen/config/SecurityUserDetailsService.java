package com.ezen.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ezen.board.domain.Member;
import com.ezen.board.domain.SecurityUser;
import com.ezen.board.persistence.MemberRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		Optional<Member> optionalUser = memberRepo.findById(username);
		
		if(optionalUser.isPresent()) {
			Member member = optionalUser.get();
			return new SecurityUser(member);
		} else {
			throw new UsernameNotFoundException(username + "사용자 없음");
		}
	}

}
