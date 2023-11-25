package com.example.contestapp.component

import AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contestapp.android.R

@Composable
fun AppSearchBar(
    modifier:Modifier = Modifier,
    text:String,
    onText:(String) -> Unit
) {

    Row(
        modifier = modifier
            .background(AppTheme.colors.secondBackground)
            .padding(start = 12.dp, end = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = AppTheme.colors.formTitle
            )
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = text,
                onValueChange = {value ->
                    onText(value)
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = AppTheme.colors.formTitle
                )
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(26.dp)) {
            Icon(
                painterResource(id = R.drawable.baseline_filter_list_24),
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = AppTheme.colors.formTitle
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = AppTheme.colors.formTitle
            )
        }

    }

}