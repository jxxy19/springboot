package org.fullstack4.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

//VO객체와 동일
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_board")
@SpringBootApplication
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = true)
    private int idx;
    @Column(length = 20, nullable = false)
    private String user_id;
    @Column(length= 100, nullable = false)
    private String title;
    @Column(length= 2000, nullable = false)
    private String content;
    @Column(length= 10, nullable = true)
    private String display_date;

    public void modify(String user_id, String title, String content, String display_date) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.display_date = display_date;
        super.setModify_date(LocalDateTime.now());
    }

}
