package org.fullstack4.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class BaseEntity {
    @CreatedDate
    @Column(name = "reg_date", updatable = false)
//    필드와 컬럼명이 동일한 경우에는 안적어도 괜찮지만, 다르게 하는 경우에는 변경 가능
    private LocalDateTime reg_date;
    @LastModifiedDate
    @Column(name = "modify_date", nullable = true, insertable = false, updatable = true)
    private LocalDateTime modify_date;

    public void setModify_date(LocalDateTime modify_date) {
        this.modify_date = LocalDateTime.now();

    }



}
