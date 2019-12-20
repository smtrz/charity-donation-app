package com.tahir.omiseassignment.Enums

enum class payment_status_codes(private val value: Int) {
    successful(200), failed(200), IMPORT(5);

    companion object {
        fun valueOf(value: Int) = payment_status_codes.values().find { it.value == value }
    }
}
