package np.com.lashman.learnit.presentation.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import np.com.lashman.learnit.presentation.components.CountCard

@Composable
fun DashboardScreen() {
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
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    subjectCount = 5,
                    studiedHours = 10,
                    studyHoursGoal = 15
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
            .padding(16.dp) // Optional: Add padding to the Row for better spacing
    ) {
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Subject Count",
            count = subjectCount.toString() // Convert to String
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Studied Hours",
            count = studiedHours.toString() // Convert to String
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Study Hours Goal",
            count = studyHoursGoal.toString() // Convert to String
        )
    }
}
