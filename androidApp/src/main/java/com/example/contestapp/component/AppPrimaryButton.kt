package com.example.contestapp.component

import AppTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppPrimaryButton(
    modifier: Modifier = Modifier,
    text:String,
    isIconActive:Boolean,
    onClick:() -> Unit
) {

    Button(
        onClick = {
                  onClick()
        },
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.primary)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row {
                if(isIconActive){
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        tint = AppTheme.colors.secondTitle,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
               
                Text(
                    text = text,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.secondTitle
                )
            }
        }
    }

}