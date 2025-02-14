package com.example.zentask.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.zentask.ui.theme.NunitoBold
import com.example.zentask.ui.theme.PrimaryColor
import com.example.zentask.viewmodel.DashboardViewModel

@Composable
fun DashboardView(navController: NavController){

    // Instance
    val dashboardViewModel : DashboardViewModel = hiltViewModel()

    // State

    Box(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Icon(
                    Icons.Default.AcUnit,
                    modifier = Modifier.size(18.dp),
                    contentDescription = "Forecast",
                    tint = PrimaryColor
                )
                Box(modifier = Modifier.width(10.dp))
                Text(text = "Tues, 10 Okt", color = PrimaryColor, fontFamily = NunitoBold)
            }
            Box(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "Projects metrics",
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor,
                    fontSize = 15.sp
                )
                Box(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(16.dp))
                            .height(90.dp)
                            .background(color = Color(0xFFf3f4f6))
                    ) {
                        Text(
                            text = "Projects",
                            modifier = Modifier.padding(top = 6.dp),
                            color = Color.DarkGray,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "15",
                            fontFamily = NunitoBold,
                            color = Color.DarkGray,
                            style = MaterialTheme.typography.displayMedium
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(16.dp))
                            .height(90.dp)
                            .background(color = Color.LightGray)
                    ) {
                        Text(
                            text = "Done",
                            modifier = Modifier.padding(top = 6.dp),
                            color = Color.DarkGray,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "6",
                            fontFamily = NunitoBold,
                            color = Color.DarkGray,
                            style = MaterialTheme.typography.displayMedium
                        )
                    }
                }
            }
        }
    }
}
