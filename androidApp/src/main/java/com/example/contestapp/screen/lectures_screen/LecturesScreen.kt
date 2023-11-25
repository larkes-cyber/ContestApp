package com.example.contestapp.screen.lectures_screen

import AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contestapp.component.AppPrimaryButton
import com.example.contestapp.component.AppSearchBar
import com.example.contestapp.component.LectureItem
import com.example.contestapp.navigation.screen.Screen
import com.example.contestapp.resource.Constants

@Composable
fun LecturesScreen(
    navController: NavController,
    viewModel: LecturesViewModel
) {

    val lecturesUIState by viewModel.lecturesUIStare.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getLectures()
    }

    val scrollableState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollableState)

    ) {
        Spacer(modifier = Modifier.height(39.dp))
        Text(
            text = Constants.LECTURE_TITLE,
            fontWeight = FontWeight.SemiBold,
            fontSize = 23.sp,
            color = AppTheme.colors.title,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(29.dp))
        AppSearchBar(
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            text = "Поиск",
            onText = {

            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        AppPrimaryButton(
            text = "Добавить",
            isIconActive = true,
            modifier = Modifier.height(42.dp)
        ) {
            navController.navigate(Screen.AddLectureScreen.route)
            
        }

        Spacer(modifier = Modifier.height(19.dp))
        lecturesUIState.lectures.forEach {item ->
            LectureItem(lecture = item, getLectureImage = {id, state ->
                viewModel.getLectureImage(id, state)
            }, modifier = Modifier
                .fillMaxWidth()){

            }
            Spacer(modifier = Modifier.height(6.dp))
        }
        Spacer(modifier = Modifier.height(50.dp))

    }

}