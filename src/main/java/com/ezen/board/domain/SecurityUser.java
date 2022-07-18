package com.ezen.board.domain;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User{

	private static final long serialVersionUID = 1L;
	private Member member;

	public SecurityUser(Member member) {
		super(member.getId(), "{noop}"+member.getPassword(), 
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

}
