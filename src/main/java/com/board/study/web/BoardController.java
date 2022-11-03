package com.board.study.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.study.dto.board.BoardRequestDto;
import com.board.study.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/board/list")
	public String getBoardListPage(Model model ,@RequestParam(required=false,defaultValue="0") Integer page, 
			@RequestParam(required=false, defaultValue = "20") Integer size) throws Exception{
		
		try {
			model.addAttribute("resultMap",boardService.findAll(page,size));
			
			
		}catch(Exception e) {
			throw new Exception(e.getMessage());
			
			
		}
		return "/board/list";
		
	}
	
	
	
	
	
	@GetMapping("/board/writer")
	public String getBoardWritePage(Model model,BoardRequestDto boardRequestDto) {
		return "/board/writer";
	}
	
	
	
	
	@GetMapping("/board/view")
	public String getBoardViewPage(Model model,BoardRequestDto boardRequestDto) throws Exception{
		try {
			if (boardRequestDto.getId()!=null) {
				model.addAttribute("info",boardService.findById(boardRequestDto.getId()));
			}
			
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "/board/view";
		
		
		
	}
	
	
	
	
	@PostMapping("/board/writer/action")
	public String boardWriteAction(Model model,BoardRequestDto boardRequestDto)throws Exception{
		
		try {
			Long result = boardService.save(boardRequestDto);
			
			if(result <0) {
				throw new Exception("#Exception boardWriterAction!");
			}
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return "redirect:/board/list";
		
	}
	
	
	
	
	
	
	
	
	@GetMapping("/testBoard")
	public String testBoard() {
		return "board/view3";
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
}