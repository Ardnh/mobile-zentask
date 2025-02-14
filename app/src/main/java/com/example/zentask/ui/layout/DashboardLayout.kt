
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zentask.R
import com.example.zentask.ui.main.DashboardView
import com.example.zentask.ui.main.ProjectView
import com.example.zentask.ui.main.UnknownView
import com.example.zentask.ui.theme.NunitoBold
import com.example.zentask.ui.theme.NunitoSemiBold
import com.example.zentask.ui.theme.PrimaryColor
import com.example.zentask.ui.theme.SubtitleFontSize
import com.example.zentask.ui.theme.TitleFontSize
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardLayout(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedView by remember { mutableStateOf(Pair("dashboardview", "Dashboard")) }

    // ModalNavigationDrawer dibungkus di luar Scaffold
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            DrawerContent(
                onItemClick = { selected ->
                    selectedView = selected // Update selected view
                    scope.launch { drawerState.close() } // Tutup drawer setelah klik
                },
                selectedView = selectedView.first
            )
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(
                        selectedView.second,
                        fontFamily = NunitoBold ,
                        color = PrimaryColor,
                        fontSize = SubtitleFontSize
                    ) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = PrimaryColor)
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Settings, contentDescription = "Menu", tint = PrimaryColor)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White
                    )
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(color = Color.White)
                ) {
                    when (selectedView.first) {
                        "dashboardview" -> DashboardView(navController)
                        "projectview" -> ProjectView(navController)
                        else -> UnknownView(navController)
                    }
                }
            }
        )
    }
}

@Composable
fun DrawerContent(onItemClick: (Pair<String, String>) -> Unit, selectedView: String) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val drawerWidth = screenWidth * 0.8f

    Box(modifier = Modifier
        .fillMaxHeight()
        .width(drawerWidth)
        .background(color = Color.White)
        .padding(horizontal = 20.dp, vertical = 20.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
        ) {

            Text(
                text = "Zen Task",
                fontSize = TitleFontSize,
                fontFamily = NunitoBold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Hello, Rover",
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = NunitoSemiBold,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Item navigasi untuk Home
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(Pair("dashboardview", "Dashboard")) }
            ) {
                ListItem(
                    colors = ListItemDefaults.colors(
                        containerColor = if (selectedView == "dashboardview") Color(0xFFF9FAFB) else Color.White
                    ),
                    headlineContent = { Text("Dashboard") },
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.dashboard_icons),
                            contentDescription = "Dashboard",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
            }

            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(Pair("projectview", "Projects")) }
            ) {
                ListItem(
                    colors = ListItemDefaults.colors(
                        containerColor = if (selectedView == "projectview") Color(0xFFF9FAFB) else Color.White
                    ),
                    headlineContent = { Text("Projects") },
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.projects_icons),
                            contentDescription = "Projects",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
            }

            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(Pair("categoryview", "Projects")) }
            ) {
                ListItem(
                    colors = ListItemDefaults.colors(
                        containerColor = if (selectedView == "categoryview") Color(0xFFF9FAFB) else Color.White
                    ),
                    headlineContent = { Text("Category") },
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.category_icons),
                            contentDescription = "Projects",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
            }

        }
    }
}