package com.board.study.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.study.dto.board.BoardRequestDto;
import com.board.study.dto.board.BoardResponseDto;
import com.board.study.entity.board.Board;
import com.board.study.entity.board.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	
	private final BoardRepository boardRepository;
	
	@Transactional
	public Long save(BoardRequestDto boardSaveDto) {
		return boardRepository.save(boardSaveDto.toEntity()).getId();
	}
	
	
	
	
	@Transactional(readOnly=true)
	public HashMap <String,Object> findAll(Integer page, Integer size){
		
		HashMap <String , Object > resultMap = new HashMap <String , Object>();
		
		
		Page<Board> list = boardRepository.findAll(PageRequest.of(page, size));
		
		
		resultMap.put("list",list.stream().map(BoardResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging",list.getPageable());
		resultMap.put("totalCnt",list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());
		
		return resultMap;
		
				
		
			
		
		
	}
	

	
	
	@Transactional(readOnly=true)
	public List<BoardResponseDto> findAll(){
		return boardRepository.findAll().stream().map(BoardResponseDto::new).collect(Collectors.toList());
	}
	
	
	
	public BoardResponseDto findById(Long id) {
		
		return new BoardResponseDto(boardRepository.findById(id).get());
		
	}
	
	public int updateBoard(BoardRequestDto boardRequestDto) {
		return boardRepository.updateBoard(boardRequestDto);
	}
	
	
	
	public void deleteById(Long id) {
		boardRepository.deleteById(id);
	}
	
	

}