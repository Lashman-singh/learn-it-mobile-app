import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TaskCheckBox(
    isComplete: Boolean,
    onCheckBoxClick: () -> Unit,
    borderColor: Color
) {
    // Simple Checkbox implementation
    Row(
        modifier = Modifier
            .clickable(onClick = onCheckBoxClick)
            .size(24.dp) // Set a fixed size to prevent infinity issues
    ) {
        Checkbox(
            checked = isComplete,
            onCheckedChange = { onCheckBoxClick() },
            colors = CheckboxDefaults.colors(checkmarkColor = Color.White)
        )
    }
}