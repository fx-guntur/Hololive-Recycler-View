package com.example.hololiverecyclerview.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    var name: String,
    var region: String,
    var desc: String,
    var photo: String,
    var generation: String,
    var debutDate: String
) : Parcelable
