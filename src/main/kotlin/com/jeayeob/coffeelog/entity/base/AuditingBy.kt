package com.jeayeob.coffeelog.entity.base

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditingBy {
    @CreatedBy
    @Column(name = "created_by", insertable = true, updatable = false)
    var createdBy: Long? = null

    @LastModifiedBy
    @Column(name = "last_updated_by", insertable = true, updatable = true)
    var lastUpdatedBy: Long? = null
}