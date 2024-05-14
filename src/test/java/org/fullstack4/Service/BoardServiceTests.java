package org.fullstack4.Service;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.dto.BoardDTO;
import org.fullstack4.dto.PageRequestDTO;
import org.fullstack4.dto.PageResponseDTO;
import org.fullstack4.service.BoardServiceIf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class BoardServiceTests {
    @Autowired
    private BoardServiceIf boardService;

    @Test
    public void testRegist() {
        log.info("=====================================");
        log.info("BoardServiceTests >> testRegist START");
        log.info("boardService.getClass().getName() : {}", boardService.getClass().getName() );

        BoardDTO boardDTO = BoardDTO.builder()
                .user_id("작성자")
                .title("wpahr")
                .content("내용테스트")
                .display_date("2024-01-01")
                .build();
        int result = boardService.regist(boardDTO);
        log.info("BoardServiceTests >> testRegist END");
        log.info("BoardDTO >> testRegist END");
        log.info("=====================================");
        log.info("=====================================");
    }
    @Test
    public void testModify() {
        log.info("=====================================");
        log.info("BoardServiceTests >> testModify START");
        BoardDTO boardDTO = BoardDTO.builder()
                .idx(84)
                .user_id("slfjsldjflsdj")
                .title("tnwjdtnwd")
                .content("tnkenktnk")
                .display_date("2024-05-12")
                .build();
        boardService.modify(boardDTO);
        log.info("BoardServiceTests >> testModifyEND");
        log.info("=====================================");
    }
    @Test
    public void testView() {
        int idx = 84;
        BoardDTO boardDTO = boardService.view(idx);
        log.info("=====================================");
        log.info("BoardServiceTests >> testView START");
        log.info("boardDTO : "+boardDTO);
        log.info("BoardServiceTests >> testView END");
        log.info("=====================================");
    }

    @Test
    public void testDelete() {
        int idx = 11;
        log.info("=====================================");
        log.info("BoardServiceTests >> testModify START");
        boardService.delete(idx);
        log.info("BoardServiceTests >> testModifyEND");
        log.info("=====================================");
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .page_size(10)
//                .search_type("t")
//                .search_word("test")
                .build();
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        log.info("pageRequestDTO : {}", pageRequestDTO);
        log.info("responseDTO : {}", responseDTO);
        log.info("BoardServiceTests >> testList end");
        log.info("=====================================");
    }
}
