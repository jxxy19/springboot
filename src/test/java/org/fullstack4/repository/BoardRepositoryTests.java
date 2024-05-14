package org.fullstack4.repository;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.domain.BoardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testGetNow() {
        String now = boardRepository.getNow();
        log.info("========================================");
        log.info("now : "+ now);
        log.info("=========================================");
    }

    @Test
    public void testRegist() {
        log.info("========================================");
        log.info("boardRepositoryTests >> testregist");
        IntStream.rangeClosed(0, 10)
                .forEach(i->{
                    BoardEntity bbs = BoardEntity.builder()
                            .user_id("test")
                            .title("테스트 제목 "+i)
                            .content("테스트 내용 "+i)
                            .display_date(new SimpleDateFormat("yyyy-MM-dd").format(
                                    new Date(2024-1900, 4, (i%10 == 0 ? 1: i%10))).toString())
                            .build();

                    BoardEntity bbsResult = boardRepository.save(bbs);
                });
        log.info("========================================");
        log.info("boardRepositoryTests >> testregist end");
    }

    @Test
    public void testView () {
        int idx = 1;
        Optional<BoardEntity> result = boardRepository.findById(idx);
        BoardEntity bbs = result.orElse(null);
        //없으면 null 값으로 리턴해줘!
//        result.get(); // 값이 없으면 NOSuchElementException 발생
//        if(result.isPresent()) {throw new IllegalArgumentException();}
//        result.orElseThrow(IllegalArgumentException::new); // 직접 에러를 줄 수 있음,,,,,?
//        result.orElseThrow(()-> new IllegalArgumentException("no find data"));
//        result.orElseGet(BoardEntity::new);
//        result.ifPresent(b->{log.info("result:{}", b)});
//        트랜잭션이 걸릴 떄, 예외를 발생시키지 않으면 트랜잭션에서 빠지게 되고, 롤백이 되지않음.

        log.info("================================================");
        log.info("BoardRepositoryTests >> testView Start");
        log.info("bbs : " + bbs);
        log.info("BoardRepositoryTests >> testView End");
        log.info("================================================");

    }
//    @Test
//    public void testModify() {
//        int idx = 1;
//        Optional<BoardEntity> result = boardRepository.findById(idx);
//        BoardEntity bbs = result.orElse(
//                BoardEntity.builder()
//                        .idx(idx)
//                        .user_id("누구누구누구누구")
//                        .title("내용 수정!!!1111")
//                        .content("여기가 진짜 수정")
//                        .display_date(
//                                new SimpleDateFormat("yyyy-MM-dd")
//                                        .format(new Date().toString())
//                        )
//                        .build()
//
//        );
//        bbs = BoardEntity.builder()
//                .idx(idx)
//                        .user_id("누구누구누구누구")
//                        .title("내용 수정!!!1111")
//                        .content("여기가 진짜 수정")
//                        .display_date(
//                                new SimpleDateFormat("yyyy-MM-dd")
//                                        .format(new Date().toString())
//                        )
//                        .build();
//
//
//        boardRepository.save(bbs);
//        log.info("================================================");
//        log.info("BoardRepositoryTests >> testView Start");
//        log.info("bbs : " + bbs);
//        log.info("BoardRepositoryTests >> testView End");
//        log.info("================================================");
//    }


    @Test
    public void testModify() {
//        int idx = 2;
//        Optional<BoardEntity> result = boardRepository.findById(idx);
//        BoardEntity bbs = result.orElse(
//                BoardEntity.builder()
//                        .idx(idx)
//                        .user_id("test")
//                        .title("제목 수정 1") // 내용 똑같으면 업데이트 일어나지 않음
//                        .content("내용 수정 1")
//                        .display_date(
//                                new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString()
//                        )
//                        .build()
//        );
//
//        bbs = BoardEntity.builder()
//                .idx(idx) // key가 되는 컬럼이 들어오면 update로, 그렇지 않으면 insert로 처리함.
//                .user_id("test")
//                .title("제목 수정 1")
//                .content("내용 수정 1")
//                .display_date(
//                        new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString()
//                )
//                .build();
////        bbs.modify("test","wpahrtnwj","dkskdfksf",);
//        boardRepository.save(bbs);
//
//        log.info("============================");
//        log.info("BoardRepositoryTest >> testModify START");
//        log.info("bbs : " + bbs);
//        log.info("BoardRepositoryTest >> testModify END");
//        log.info("============================");
    }

    @Test
    public void testDelete() {
        int idx = 1;
        boardRepository.deleteById(idx);
    }

    @Test
    public void testSearch () {
        log.info("============================");
        log.info("BoardRepositoryTest >> testSearch START"); //q 보드 테이블 만들어줌
        PageRequest pageable = PageRequest.of(0,10, Sort.by("idx").descending());
//        리미트에 들어가는 쿼리 세팅과 리스트 보여주는 함수,,?
//
//        boardRepository.search(pageable);
        Page<BoardEntity> result = boardRepository.search(pageable);

        log.info("result : {}", result);
        log.info("BoardRepositoryTest >> testSearch END");
        log.info("============================");
    }

    @Test
    public void testSearch2 () {
        log.info("============================");
        log.info("BoardRepositoryTest >> testSearch START"); //q 보드 테이블 만들어줌
        PageRequest pageable = PageRequest.of(0,10, Sort.by("idx").descending());
//        리미트에 들어가는 쿼리 세팅과 리스트 보여주는 함수,,?
        String[] types = {"t", "c", "w"};
        String search_keyword = "test";
        boardRepository.search2(pageable, types, search_keyword );
        log.info("BoardRepositoryTest >> testSearch END");
        log.info("============================");
    }
}
