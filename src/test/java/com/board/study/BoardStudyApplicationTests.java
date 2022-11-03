package com.board.study;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.study.dto.board.BoardRequestDto;
import com.board.study.dto.board.BoardResponseDto;
import com.board.study.service.BoardService;

@SpringBootTest
class BoardApplicationTests {
	
	
	
	
	@Autowired
	private BoardService boardService;
	
	
	
	
	/*
	@Test
	void delete() {
		boardService.deleteById(8L);
		
		
		
	}
	
	*/
	
	
	/*
	@Test  
	void findOne() {
		

		
		BoardResponseDto response = boardService.findById(2L);
		
		
		System.out.println("find one");
		System.out.println(response.toString());
		
		
		
		
	}
	
	*/
	
	@Test
	void save() {
		BoardRequestDto boardSaveDto = new BoardRequestDto();
		
		boardSaveDto.setTitle("제목입니다.");
		boardSaveDto.setContent("내용입니다");
		boardSaveDto.setRegisterId("작성자");
		
		
		Long result= boardService.save(boardSaveDto);
		
		if(result > 0) {
			System.out.println("#Sucees save()~");
			findAll();
			findById(result);
			
		}else {
			
			System.out.println("#Fail Save()~");
			
		}
		
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
		
		void findAll() {
			
			List <BoardResponseDto> list = boardService.findAll();
			if(list !=null) {
				System.out.println("#Success findAll() : " + list.toString());
			}else {
				System.out.println("#Fail findAll() ~");
			}
			
		}
		
		
		
		
		
		
		void findById(Long id) {
			BoardResponseDto info = boardService.findById(id);
			
			if(info != null) {
				System.out.println("# Success findById() : " + info.toString());
				updateBoard(id);
				
			}else {
				System.out.println("#Fail findById() ~");
				
			}
		}
		
		
		
		
		
		void updateBoard(Long id) {
			
			BoardRequestDto boardRequestDto = new BoardRequestDto();
			
			boardRequestDto.setId(id);
			boardRequestDto.setTitle("업데이트 제목");
			boardRequestDto.setContent("업데이트 내용");
			boardRequestDto.setRegisterId("작성자");
			
			
			int result = boardService.updateBoard(boardRequestDto);
			
			
			if(result > 0) {
				System.out.println("#Sueccess updateBoard() ~");
				
			}
			else {
				System.out.println("#Fail updateBoard() ~");
			}
		}
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	void contextLoads() {
	}

}
