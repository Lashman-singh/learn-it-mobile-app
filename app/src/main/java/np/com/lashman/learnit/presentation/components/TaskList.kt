package np.com.lashman.learnit.presentation.components

import TaskCheckBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import np.com.lashman.learnit.R
import np.com.lashman.learnit.domain.model.Task
import np.com.lashman.learnit.util.Priority

@Composable
fun TaskList(
    sectionTitle: String,
    tasks: List<Task>,
    emptyListText: String,
    onTasksCardClick: (Int?) -> Unit,
    onCheckBoxClick: (Task) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        // Header as the first item in Column
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(12.dp)
        )

        // Check if tasks list is empty and show appropriate message
        if (tasks.isEmpty()) {
            EmptyTaskList(emptyListText = emptyListText)
        } else {
            // Show tasks in a Column instead of LazyColumn
            tasks.forEach { task ->
                TaskCard(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    task = task,
                    onCheckBoxClick = { onCheckBoxClick(task)},
                    onClick = { onTasksCardClick(task.taskId) }
                )
            }
        }
    }
}

@Composable
private fun EmptyTaskList(emptyListText: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(R.drawable.tasks),
            contentDescription = emptyListText
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = emptyListText,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun TaskCard(
    modifier: Modifier = Modifier,
    task: Task,
    onCheckBoxClick: () -> Unit,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier.clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TaskCheckBox(
                isComplete = task.isCompleted,
                onCheckBoxClick = onCheckBoxClick,
                borderColor = Priority.fromInt(task.priority).color,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textDecoration = if (task.isCompleted) {
                            TextDecoration.LineThrough
                        } else {
                            TextDecoration.None
                        }
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${task.dueDate}", // Format this date properly
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
