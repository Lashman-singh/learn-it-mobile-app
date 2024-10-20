package np.com.lashman.learnit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import np.com.lashman.learnit.presentation.dashboard.DashboardScreen
import np.com.lashman.learnit.presentation.theme.LearnITTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnITTheme {
                DashboardScreen()
            }
        }
    }
}
