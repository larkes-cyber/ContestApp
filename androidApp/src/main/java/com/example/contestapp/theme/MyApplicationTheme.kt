import android.provider.CalendarContract
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Colors(
    val background:Color,
    val secondBackground:Color,
    val primary:Color,
    val title:Color,
    val formTitle:Color,
    val secondTitle:Color,
    val opacitySub:Color,
    val thirdTitle:Color,
    val secondOpacitySub:Color
)

private val DarkColorPalette = Colors(
    background = Color(0xFFF2F2F2),
    secondBackground = Color(0xFFEBEBF2),
    primary = Color(0xFF8D5EF2),
    title = Color(0xFF000000),
    formTitle = Color(0xFF797E8C),
    secondTitle = Color(0xFFFFFFFF),
    opacitySub = Color(0x80000000),
    thirdTitle = Color(0xFF818C99),
    secondOpacitySub = Color(0x1A000000)

)

private val LightColorPalette = Colors(
    background = Color(0xFFF2F2F2),
    secondBackground = Color(0xFFEBEBF2),
    primary = Color(0xFF8D5EF2),
    title = Color(0xFF000000),
    formTitle = Color(0xFF797E8C),
    secondTitle = Color(0xFFFFFFFF),
    opacitySub = Color(0x80000000),
    thirdTitle = Color(0xFF818C99),
    secondOpacitySub = Color(0x1A000000)
)

object AppTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorProvider.current
}

val LocalColorProvider = staticCompositionLocalOf<Colors> {
    error("fddfdd")
}

@Composable
fun ContestAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalColorProvider provides LightColorPalette
    ) {
        content()
    }
}