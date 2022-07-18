package com.ezen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.board.domain.Board;
import com.ezen.board.domain.Search;
import com.ezen.board.domain.SecurityUser;
import com.ezen.service.BoardService;

@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/getBoardList")
	public String getBoardList(Search search, Model model) {
		
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");
		
		List<Board> boardList = boardService.getBoardList().getContent();
		
		model.addAttribute("boardList", boardList);
		
		return "board/getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		
		model.addAttribute("board", boardService.getBoard(board));
		
		return "board/getBoard";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		
		return "board/insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, 
							@AuthenticationPrincipal SecurityUser principal) {
		
		board.setMember(principal.getMember());
		boardService.insertBoard(board);
		
		return "redirect:getBoardList";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		
		boardService.updateBoard(board);
		
		return "redirect:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {

		boardService.deleteBoard(board);
		
		return "forward:getBoardList";
	}

}
