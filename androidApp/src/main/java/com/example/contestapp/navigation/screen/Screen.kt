package com.example.contestapp.navigation.screen

sealed class Screen(val route:String) {
    object LecturesScreen: Screen("lectures_screen")
    object AddLectureScreen: Screen("add_lecture_screen")
    object LectureDetailScreen: Screen("detail_lecture_screen")
    object GlossariyScreen:Screen("glossariy_screen")
    fun withArgs(vararg args: String):String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}