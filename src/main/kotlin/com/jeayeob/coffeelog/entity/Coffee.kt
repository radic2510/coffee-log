package com.jeayeob.coffeelog.entity

import com.jeayeob.coffeelog.constant.Processing
import com.jeayeob.coffeelog.entity.base.AuditingAt
import jakarta.persistence.*

@Entity
@Table(name = "coffee", indexes = [
    Index(name = "PRIMARY", columnList = "coffee_id", unique = true),
    Index(name = "idx_coffee_coffee_name", columnList = "coffee_name", unique = true)
])
class Coffee (
    @Column(name = "coffee_name", nullable = false, length = 255)
    var name: String,

    @Column(name = "coffee_country", length = 255)
    var country: String? = null,

    @Column(name = "coffee_address", length = 255)
    var address: String? = null,

    @Column(name = "coffee_variety", length = 255)
    var variety: String? = null,

    @Column(name = "coffee_processing", length = 20)
    var processing: Processing? = null,

) : AuditingAt() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coffee_id", nullable = false)
    var id: Long = 0


    fun updateName(name: String) {
        this.name = name
    }

    fun updateCountry(country: String) {
        this.country = country
    }

    fun updateAddress(address: String) {
        this.address = address
    }

    fun updateVariety(variety: String) {
        this.variety = variety
    }

    fun updateProcessing(processing: Processing) {
        this.processing = processing
    }
}