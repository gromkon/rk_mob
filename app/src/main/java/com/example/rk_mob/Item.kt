package com.example.rk_mob

class Item (val date: String?, val value: String?, val valuateName: String?,
            val low: String?, val open: String?, val close: String?) {
    override fun toString(): String {
        return "$date $value $valuateName"
    }
}