package org.ktb.springpost.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
/*해당 클래스가 엔티티가 아니라 다른 엔티티 클래스들이 상속받을 수 있는 매핑 정보만 제공한다는 것을 나타낸다.*/
@EntityListeners(AuditingEntityListener.class) //이거 왜 씀?
public abstract class BaseEntity {

    //엔티티가 생성된 시간과 마지막으로 수정된 시간을 자동으로 관리
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
