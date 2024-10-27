package np.com.lashman.learnit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import np.com.lashman.learnit.R
import np.com.lashman.learnit.domain.model.Session

@Composable
fun studySessionsList(
    sectionTitle: String,
    sessions: List<Session>,
    emptyListText: String,
    onDeleteIconClick: (Session) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (sessions.isEmpty()) {
            EmptyTaskList(emptyListText = emptyListText)
        } else {
            sessions.forEach { session ->
                StudySessionCard(
                    modifier = Modifier.padding(vertical = 8.dp),
                    session = session,
                    onDeleteIconClick = { onDeleteIconClick(session) }
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
            modifier = Modifier.size(100.dp),
            painter = painterResource(R.drawable.lamp),
            contentDescription = emptyListText
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = emptyListText,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun StudySessionCard(
    modifier: Modifier = Modifier,
    session: Session,
    onDeleteIconClick: () -> Unit
) {
    Card(
        modifier = modifier.padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = session.relatedToSubject,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.weight(1f)) // Spacer to push duration to the right
            Text(
                text = "${session.duration} hr",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(end = 8.dp) // Padding to separate from the delete icon
            )
            IconButton(
                onClick = { onDeleteIconClick() },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Study Session",
                    tint = Color.Black
                )
            }
        }
    }
}
