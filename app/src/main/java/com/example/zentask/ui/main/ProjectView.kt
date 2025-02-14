package com.example.zentask.ui.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.zentask.ui.theme.NunitoRegular
import com.example.zentask.viewmodel.ProjectViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProjectView(navController: NavController) {

    // Instance
    val projectViewModel: ProjectViewModel = hiltViewModel()
    val localContext = LocalContext.current

    // State
    val interactionProjectSource = remember { MutableInteractionSource() }
    val projectSearch by projectViewModel.searchProjectRequest.collectAsState()

    LaunchedEffect(Unit) {
        projectViewModel.searchProjectRequest.collectLatest { projectSearch ->
            Log.d("project", projectSearch)

//            val currentParams = projectViewModel.projectQueryParams.value
//            val params = currentParams.copy(
//                projectName = projectSearch
//            )
//            projectViewModel.setProjectQueryParams(params)
//            projectViewModel.getProject()
        }
    }




    // Text Search
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        BasicTextField(
            value = projectSearch,
            interactionSource = interactionProjectSource,
            singleLine = true,
            onValueChange = {
                projectViewModel.updateSearchField(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .border(1.5.dp, Color(0xFFE2E8F0), RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(vertical = 5.dp, horizontal = 15.dp),
            textStyle = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                fontFamily = NunitoRegular
            ),
            cursorBrush = SolidColor(Color.Gray),
            decorationBox = { innerText ->

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    if (projectSearch.isEmpty()) {
                        Text(
                            text = "Project",
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Light,
                                fontFamily = NunitoRegular
                            )
                        )
                    }
                    innerText()
                }

            },
        )
    }

    // Category

    // List Project

}
