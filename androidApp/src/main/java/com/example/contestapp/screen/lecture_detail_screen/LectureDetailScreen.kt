package com.example.contestapp.screen.lecture_detail_screen

import AppTheme
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contestapp.component.OutlinedAppPrimaryButton
import com.example.contestapp.navigation.screen.Screen
import com.example.contestapp.resource.Constants

@Composable
fun LectureDetailScreen(
    navController: NavController,
    viewModel:LectureDetailViewModel,
    id:String
) {

    val lectureDetailUIState by viewModel.lectureDetailUIState.collectAsState()
    val scrollState = rememberScrollState()



    LaunchedEffect(Unit){
        viewModel.init(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .verticalScroll(scrollState)) {
        Spacer(modifier = Modifier.height(25.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                IconButton(onClick = {
                    navController.navigate(Screen.LecturesScreen.route)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier
                            .width(28.dp)
                            .height(24.dp),
                        tint = AppTheme.colors.title
                    )
                }
            }
            if(lectureDetailUIState.lecture != null) {
                Text(
                    text = lectureDetailUIState.lecture!!.title,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppTheme.colors.title
                )
            }else{
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
                    tint = AppTheme.colors.title
                )
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
                    tint = AppTheme.colors.title
                )
            }
        }
        Spacer(modifier = Modifier.height(37.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            if(lectureDetailUIState.image != null) {
                Image(
                    lectureDetailUIState.image!!,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(224.dp)
                        .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)),
                    contentScale = ContentScale.Crop
                )
            }else{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(224.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            Spacer(modifier = Modifier.height(26.dp))
            OutlinedAppPrimaryButton(
                text = "Глоссарий",
                isIconActive = false,
                modifier = Modifier.fillMaxWidth()
            ) {
                
            }
            Spacer(modifier = Modifier.height(16.dp))
            if(lectureDetailUIState.lecture != null){
                lectureDetailUIState.lecture!!.descr.forEach { 
                    Text(
                        text = it[0],
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppTheme.colors.title
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = it[1],
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = AppTheme.colors.title
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
                
            }else{
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            
        }
    }
}