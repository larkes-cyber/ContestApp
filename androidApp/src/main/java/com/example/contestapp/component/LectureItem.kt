package com.example.contestapp.component

import AppTheme
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contestapp.domain.model.Lecture

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LectureItem(
    modifier: Modifier = Modifier,
    lecture: Lecture,
    getLectureImage:(String, MutableState<ImageBitmap?>) -> Unit,
    onClick:() -> Unit
) {


    var showFullCard by remember {
        mutableStateOf(false)
    }

    val image = remember {
        mutableStateOf<ImageBitmap?>(null)
    }

    LaunchedEffect(Unit){
        getLectureImage(lecture.id, image)
    }


    Card(
        modifier = modifier.combinedClickable(
            onClick = {
                      if(showFullCard){
                          showFullCard = false
                      }
            },
            onLongClick = {
                if(showFullCard.not()) showFullCard = true
            },
        ),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = AppTheme.colors.background,
        elevation = 5.dp
    ) {
        if(showFullCard){
            Column() {
                if(image.value != null) {
                    Image(
                        image.value!!,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(118.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        contentScale = ContentScale.Crop
                    )
                }else{
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(72.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp, end = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(11.dp))
                    Text(
                        text = "Лекция: ${lecture.title}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = AppTheme.colors.title
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = lecture.shortDescr,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = AppTheme.colors.title
                    )
                    Box(
                        modifier = Modifier.padding(top = 15.dp, bottom = 11.dp)
                    ) {
                        Divider(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "ред. ${lecture.time}, ${lecture.author}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = AppTheme.colors.thirdTitle
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(7.dp)
                        ) {
                            Icon(
                                imageVector = if(lecture.isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "",
                                modifier = Modifier.size(22.dp),
                                tint = Color(0xFFE64646),
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }else{
            Row(
                horizontalArrangement = Arrangement.spacedBy(22.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .height(90.dp)
            ) {
                if(image.value != null) {
                    Image(
                        image.value!!,
                        contentDescription = "",
                        modifier = Modifier
                            .size(78.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        contentScale = ContentScale.Crop
                    )
                }else{
                    Box(
                        modifier = Modifier
                            .size(72.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                Column(
                ) {
                    Text(
                        text = "Лекция: ${lecture.title}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = AppTheme.colors.title
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text =if(lecture.shortDescr.length > 25) lecture.shortDescr.substring(0,25) + "..." else lecture.shortDescr,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = AppTheme.colors.thirdTitle
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${lecture.subject} · ${lecture.time}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = AppTheme.colors.thirdTitle
                    )
                }
            }
        }

    }

}