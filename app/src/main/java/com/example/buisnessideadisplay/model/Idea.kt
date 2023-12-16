package com.example.buisnessideadisplay.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Tip(
    @StringRes val day : Int,
    @StringRes val title : Int,
    @DrawableRes val imageRes : Int,
    @StringRes val description : Int
)
