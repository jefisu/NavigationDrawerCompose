package com.jefisu.navigationdrawercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.DefaultTintColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.navigationdrawercompose.ui.theme.NavigationDrawerComposeTheme

import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerComposeTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val items = listOf(
                    MenuItem("Home", Icons.Default.Home),
                    MenuItem("Profile", Icons.Default.Person),
                    MenuItem("Help", Icons.Default.Info)
                )
                var selected by remember { mutableStateOf(0) }
                NavigationDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = drawerState.isOpen,
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = items,
                            selected = selected,
                            onSelectClick = { itemIndex ->
                                selected = itemIndex
                            }
                        )
                    }
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SmallTopAppBar(
                            title = {
                                Text(text = stringResource(R.string.app_name))
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer
                            ),
                            navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu, contentDescription = null
                                    )
                                }
                            }
                        )
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = items[selected].title,
                                style = MaterialTheme.typography.displayMedium
                            )
                        }
                    }
                }
            }
        }
    }
}