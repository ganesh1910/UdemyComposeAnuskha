package com.gk.udemycomposeanuskha

import java.io.Serializable


data class TvShow(
    val name: String,
    val desc: String,
    val TDMB: Int,
    val year: String,
    val image: Int
): Serializable
