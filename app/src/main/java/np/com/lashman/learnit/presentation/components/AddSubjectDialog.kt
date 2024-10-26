package np.com.lashman.learnit.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AddSubjectDialog(
    isOpen: Boolean,
    title: String = "Add Subject",
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit
){
    if(isOpen) {
        AlertDialog(
            onDismissRequest = { /* Handle dialog dismiss */ },
            title = { Text(text = title) },
            text = {
                Text(text = "Dialog Body")
            },
            dismissButton = {
                TextButton(onClick = { onDismissRequest() }) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                TextButton(onClick = { onConfirmButtonClick() }) {
                    Text(text = "Save")
                }
            }
        )
    }
}