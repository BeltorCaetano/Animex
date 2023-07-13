package com.example.animex.practice

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import beltor.caetano.animextestemcasa.ui.theme.AnimexTestEmCasaTheme

class Test : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimexTestEmCasaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun CollapsingTopAppBar() {
    var isCollapsed by remember { mutableStateOf(false) }
    var selectedTabIndex by remember { mutableStateOf(0) }
    var visibleItemIndex by remember { mutableStateOf(0) }

    // Animation values for collapsing behavior
    val appBarHeight = if (isCollapsed) 56.dp else 200.dp
    val elevation = if (isCollapsed) 4.dp else 0.dp

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = elevation,
                modifier = Modifier
                    .height(appBarHeight)
                    .background(Color.Blue)
            ) {
                Text(
                    text = "Collapsing TopAppBar",
                    color = Color.White
                )
            }
        },
        content = {
            TabLayout(selectedTabIndex, isCollapsed) {
                // Add your tabs here
                Tab(text = "Tab 1", visibleItemIndex = visibleItemIndex)
                Tab(text = "Tab 2", visibleItemIndex = visibleItemIndex)
                Tab(text = "Tab 3", visibleItemIndex = visibleItemIndex)
            }
        }
    )
}

@Composable
fun TabLayout(selectedTabIndex: Int, isCollapsed: Boolean, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .heightIn(if (isCollapsed) 56.dp else androidx.compose.ui.unit.Dp.Infinity)
    ) {
        ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
            content()
        }
    }
}

@Composable
fun Tab(text: String, visibleItemIndex: Int) {
    val items = (1..50).toList()

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(text = text)
        if (items.firstOrNull() == visibleItemIndex) {
            Text(
                text = "Switched to $text",
                modifier = Modifier
                    .background(Color(0xFFFFA500))
                    .padding(8.dp)
                    .align(Alignment.BottomStart)
            )
        }
        LazyColumn {
            items(items) { item ->
                Text(text = "Item $item")
            }

        }
    }
}
@Preview
@Composable
fun testPreview(){
    CollapsingTopAppBar()
}