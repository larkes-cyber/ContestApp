package com.example.contestapp.screen.glossary_screen

import AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contestapp.resource.Constants
import com.example.contestapp.screen.lecture_detail_screen.LectureDetailViewModel

@Composable
fun GlossariyScreen(
    navController: NavController,
    viewModel: GlossariyViewModel,
    id:String
) {

    val termUIState by viewModel.termsUIState.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit){
        viewModel.init(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
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
            Text(
                text = "Глоссарий",
                fontSize = 23.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Center),
                color = AppTheme.colors.title
            )

        }
        Spacer(modifier = Modifier.height(36.dp))
        if(termUIState.isLoading){
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        if(termUIState.terms.isNotEmpty()){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                termUIState.terms.forEach {item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = AppTheme.colors.background,
                        elevation = 5.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                text = item.title,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = AppTheme.colors.title
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = item.explanation,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Normal,
                                color = AppTheme.colors.thirdTitle
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }

    }


}