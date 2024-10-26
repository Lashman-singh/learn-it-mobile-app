package np.com.lashman.learnit.util

import androidx.compose.ui.graphics.Color
import np.com.lashman.learnit.presentation.theme.Green

enum class Priority(val title: String, val color: Color, val value: Int) {
    LOW(title = "Low", color = Green, value = 0),
    MEDIUM(title = "Medium", color = Color.Yellow, value = 1),
    HIGH(title = "High", color = Color.Red, value = 2);

    companion object {
        fun fromInt(value: Int) = values().firstOrNull { it.value == value } ?: MEDIUM
    }
}