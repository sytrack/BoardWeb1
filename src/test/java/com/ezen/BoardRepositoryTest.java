package com.ezen;

import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ezen.board.domain.Board;
import com.ezen.board.domain.Member;
import com.ezen.board.domain.Role;
import com.ezen.board.persistence.BoardRepository;
import com.ezen.board.persistence.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	public void testInsert() {	
		Member member1 = new Member();
		
		member1.setId("member1");
		member1.setPassword("1111");
		member1.setName("member1");
		member1.setRole(Role.ROLE_MEMBER);
		member1.setEnabled(true);

		
		Member member2 = new Member();
		
		member2.setId("member2");
		member2.setPassword("2222");
		member2.setName("member2");
		member2.setRole(Role.ROLE_ADMIN);
		member2.setEnabled(true);

		
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			
			board.setTitle("member1의 게시글" + i);
			board.setContent("반갑습니다" + i);
			board.setMember(member1);
			
			
		}
		memberRepo.save(member1);
		
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			
			board.setTitle("member2의 게시글" + i);
			board.setContent("반갑습니다" + i);
			board.setMember(member2);
			
			
		}
		memberRepo.save(member2);
	}
	
	@Test
	@Ignore
	public void testGetBoard() {
		Optional<Board> optionalBoard = boardRepo.findById(1L);
		
		Board board = optionalBoard.get();
		
		System.out.println(board.getSeq());
		System.out.println(board.getTitle());
		System.out.println(board.getContent());
		System.out.println(board.getMember().getName());
		System.out.println(board.getMember().getRole());
	}
	
	@Test
	@Ignore
	public void testGetBoardList() {
		List<Board> boardList = (List<Board>) boardRepo.findAll();
		for (Board board : boardList) {
			System.out.println("---> " + board);
		}
	}
}
