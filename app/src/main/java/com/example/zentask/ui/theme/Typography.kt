package com.example.zentask.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zentask.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

// Font Size
val TitleFontSize = 24.sp
val SubtitleFontSize = 20.sp
val BodyFontSize = 16.sp
val CaptionFontSize = 12.sp
val ButtonFontSize = 14.sp

// Font Weight
val BoldFontWeight = FontWeight.Bold
val MediumFontWeight = FontWeight.Medium
val NormalFontWeight = FontWeight.Normal
val LightFontWeight = FontWeight.Light

// Border Radius
val ButtonBorderRadius = 8.dp
val CardBorderRadius = 12.dp
val ImageBorderRadius = 16.dp
val SmallBorderRadius = 4.dp
val LargeBorderRadius = 24.dp

// Font Family
val NunitoRegular = FontFamily(Font(R.font.nunito_regular))
val NunitoSemiBold = FontFamily(Font(R.font.nunito_semibold))
val NunitoBold = FontFamily(Font(R.font.nunito_bold, FontWeight.Bold))
val NunitoExtraBold = FontFamily(Font(R.font.nunito_extrabold, FontWeight.ExtraBold))
