package np.com.lashman.learnit.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.* // Import necessary layout components
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.* // Import Material3 components
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import np.com.lashman.learnit.R
import np.com.lashman.learnit.domain.model.Subject
import np.com.lashman.learnit.presentation.components.CountCard
import np.com.lashman.learnit.presentation.components.SubjectCard

@Composable
fun DashboardScreen() {
    // Hardcoded subjects
    val subjects = listOf(
        Subject(name = "Python", goalHours = 10f, colors = Subject.subjectCardColors[0]),
        Subject(name = "Web Dev", goalHours = 10f, colors = Subject.subjectCardColors[1]),
        Subject(name = "DBMS", goalHours = 10f, colors = Subject.subjectCardColors[2]),
        Subject(name = "CX/UX", goalHours = 10f, colors = Subject.subjectCardColors[3]),
        Subject(name = "AWS", goalHours = 10f, colors = Subject.subjectCardColors[4])
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
                    subjectCount = subjects.size, // Update here to use hardcoded subjects
                    studiedHours = 10,
                    studyHoursGoal = 15
                )
            }
            item {
                SubjectCardsSection(
                    modifier = Modifier.fillMaxWidth(),
                    subjectList = subjects // Use hardcoded subjects
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
    emptyListText: String = "You don't have any subjects. \nClick the + button to add new subjects."
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
            IconButton(onClick = { /* Handle add subject click */ }) {
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
