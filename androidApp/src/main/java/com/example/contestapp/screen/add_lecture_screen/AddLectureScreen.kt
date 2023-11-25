package com.example.contestapp.screen.add_lecture_screen

import AppTheme
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.Divider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toFile
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.contestapp.android.R
import com.example.contestapp.component.AppForm
import com.example.contestapp.component.AppPrimaryButton
import com.example.contestapp.component.OutlinedAppPrimaryButton
import com.example.contestapp.navigation.screen.Screen
import com.example.contestapp.resource.Constants
import java.io.File

@Composable
fun AddLectureScreen(
    navController: NavController,
    viewModel: AddLectureViewModel
) {

    val addLectureUIState by viewModel.addLectureUIState.collectAsState()


    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if(uri != null) {
            viewModel.uploadFile(uri)
        }
    }

    val audioLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if(uri != null) {
            viewModel.uploadAudio(uri)
        }
    }

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    LaunchedEffect(addLectureUIState.hasBeenDone){
        if(addLectureUIState.hasBeenDone){
            navController.navigate(Screen.LecturesScreen.route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .verticalScroll(scrollState)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                IconButton(onClick = {  }) {
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
                text = Constants.ADD_LECTURE_TITLE,
                fontSize = 23.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Center)
            )

        }


        Spacer(modifier = Modifier.height(36.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(100))
                    .background(AppTheme.colors.secondBackground),
                contentAlignment = Alignment.Center
            ) {
                if(addLectureUIState.uploadedFile == null){
                    Icon(
                        painter = painterResource(id = R.drawable.file_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .width(35.dp)
                            .height(28.dp)
                    )
                }else{
                    AsyncImage(
                        model = addLectureUIState.uploadedFile,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.TopCenter),
                        contentScale = ContentScale.Crop
                    )

                }
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 26.dp, bottom = 46.dp)
                .padding(horizontal = 16.dp)
        ) {
            AppPrimaryButton(
                text = "Загрузить фото",
                isIconActive = true,
                modifier = Modifier.height(42.dp)
            ) {
                galleryLauncher.launch("image/*")
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AppForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                label = "ФИО",
                text = addLectureUIState.author
            ){
                viewModel.changeAuthor(it)
            }
            AppForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                label = "Название лекции",
                text = addLectureUIState.title
            ){
                viewModel.changeTitle(it)

            }
            AppForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                label = "Тема",
                text = addLectureUIState.subject
            ){
                viewModel.changeSubject(it)
            }
        }

        Box(
            modifier = Modifier.padding(
                top = 17.dp,
                start = 17.dp
            )
        ) {
            OutlinedAppPrimaryButton(
                text = "Выбрать Файл",
                isIconActive = true,
                modifier = Modifier.height(40.dp)
            ) {
                audioLauncher.launch("*/*")
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        if(addLectureUIState.uploadedAudio != null){
            val text = context.getFileName(addLectureUIState.uploadedAudio!!)
            Text(
                text = "$text.mp3",
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = AppTheme.colors.formTitle,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            AppPrimaryButton(
                text = "Готово",
                isIconActive = false,
                modifier = Modifier.height(42.dp)
            ) {
                viewModel.onDone(context)
            }
        }



    }
}

private fun Context.getContentFileName(uri: Uri): String? = runCatching {
    contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        cursor.moveToFirst()
        return@use cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME).let(cursor::getString)
    }
}.getOrNull()
fun Context.getFileName(uri: Uri): String? = when(uri.scheme) {
    ContentResolver.SCHEME_CONTENT -> getContentFileName(uri)
    else -> uri.path?.let(::File)?.name
}
