package np.com.lashman.learnit.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import np.com.lashman.learnit.R
import np.com.lashman.learnit.domain.model.Session
import np.com.lashman.learnit.domain.model.Subject
import np.com.lashman.learnit.domain.model.Task
import np.com.lashman.learnit.presentation.components.AddSubjectDialog
import np.com.lashman.learnit.presentation.components.CountCard
import np.com.lashman.learnit.presentation.components.DeleteDialog
import np.com.lashman.learnit.presentation.components.SubjectCard
import np.com.lashman.learnit.presentation.components.TaskList
import np.com.lashman.learnit.presentation.components.studySessionsList

@Composable
fun DashboardScreen() {
    // Hardcoded subjects and tasks
    val subjects = listOf(
        Subject(name = "Python", goalHours = 10f, colors = Subject.subjectCardColors[0], subjectId = 1),
        Subject(name = "Web Dev", goalHours = 10f, colors = Subject.subjectCardColors[1], subjectId = 2),
        Subject(name = "DBMS", goalHours = 10f, colors = Subject.subjectCardColors[2], subjectId = 3),
        Subject(name = "CX/UX", goalHours = 10f, colors = Subject.subjectCardColors[3], subjectId = 4),
        Subject(name = "AWS", goalHours = 10f, colors = Subject.subjectCardColors[4], subjectId = 5)
    )

    val tasks = listOf(
        Task(title = "Read notes", description = "", dueDate = 0L, priority = 0, relatedToSubject = "Python", isCompleted = true, taskId = 0, taskSubjectId = 1),
        Task(title = "Speak notes", description = "", dueDate = 1696200000000L, priority = 1, relatedToSubject = "Python", isCompleted = false, taskId = 1, taskSubjectId = 2),
        Task(title = "Write notes", description = "", dueDate = 1696800000000L, priority = 2, relatedToSubject = "Python", isCompleted = true, taskId = 2, taskSubjectId = 1),
    )

    val session = listOf(
        Session(sessionSubjectId = 0, relatedToSubject = "Python", date = 0L, duration = 2, sessionId = 0),
        Session(sessionSubjectId = 1, relatedToSubject = "AWS", date = 0L, duration = 12, sessionId = 1),
        Session(sessionSubjectId = 2, relatedToSubject = "Python", date = 1L, duration = 5, sessionId = 3),
        Session(sessionSubjectId = 0, relatedToSubject = "Web Dev", date = 0L, duration = 2, sessionId = 2),

        )

    var isAddSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteDialogOpen by rememberSaveable { mutableStateOf(false) }

    var subjectName by remember {mutableStateOf("")}
    var goalHours by remember {mutableStateOf("")}
    var selectedColor by remember {mutableStateOf(Subject.subjectCardColors.random())}

    AddSubjectDialog(
        isOpen = isAddSubjectDialogOpen,
        subjectName = subjectName,
        goalHours = goalHours,
        selectedColors = selectedColor,
        onColorChange = {selectedColor = it},
        onSubjectNameChange = { subjectName = it },
        onGoalHoursChange = { goalHours = it },
        onDismissRequest = {isAddSubjectDialogOpen = false},
        onConfirmButtonClick = {isAddSubjectDialogOpen = false}
    )

    DeleteDialog(
        isOpen = false,
        title = "Delete Subject",
        bodyText = "Are you sure you want to delete this session?",
        onDismissRequest = { isDeleteDialogOpen = false },
        onConfirmButtonClick = {
            isDeleteDialogOpen = false
        }
    )
    Scaffold(
        topBar = { DashboardScreenTopBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                CountCardSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    subjectCount = subjects.size,
                    studiedHours = 10,
                    studyHoursGoal = 15
                )
            }
            item {
                SubjectCardsSection(
                    modifier = Modifier.fillMaxWidth(),
                    subjectList = subjects,
                    onAddIconClicked = {
                        isAddSubjectDialogOpen = true
                    }
                )
            }
            item {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 20.dp)
                ) {
                    Text(text = "Start Study Session")
                }
            }
            item {
                TaskList(
                    sectionTitle = "UPCOMING TASKS",
                    emptyListText = "You don't have any tasks.\nClick the + button to add new tasks.",
                    tasks = tasks,
                    onTasksCardClick = { /* Handle task card click */ },
                    onCheckBoxClick = { /* Handle checkbox click */ }
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                studySessionsList(
                    sectionTitle = "STUDY SESSIONS",
                    emptyListText = "You don't have any study sessions.\nClick the + Start a study session to begin recording your progress.",
                    sessions = session,
                    onDeleteIconClick = { selectedSession ->
                        isDeleteDialogOpen = true
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardScreenTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "LearnIT",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}

@Composable
private fun CountCardSection(
    modifier: Modifier = Modifier,
    subjectCount: Int,
    studiedHours: Int,
    studyHoursGoal: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Subject Count",
            count = subjectCount.toString()
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Studied Hours",
            count = studiedHours.toString()
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Study Hours Goal",
            count = studyHoursGoal.toString()
        )
    }
}

@Composable
private fun SubjectCardsSection(
    modifier: Modifier = Modifier,
    subjectList: List<Subject>,
    emptyListText: String = "You don't have any subjects. \nClick the + button to add new subjects.",
    onAddIconClicked: () -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Subjects",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 12.dp)
            )
            IconButton(onClick = { onAddIconClicked() }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Subjects"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (subjectList.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(R.drawable.books),
                    contentDescription = emptyListText
                )
                Text(
                    text = emptyListText,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
            ) {
                items(subjectList) { subject ->
                    SubjectCard(
                        modifier = Modifier.padding(8.dp),
                        subjectName = subject.name,
                        gradientColors = subject.colors,
                        onClick = { /* Handle subject card click */ }
                    )
                }
            }
        }
    }
}
