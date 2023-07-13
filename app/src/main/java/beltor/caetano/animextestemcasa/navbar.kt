package com.example.animex

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import beltor.caetano.animextestemcasa.ui.theme.AnimexTestEmCasaTheme

class navbar : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimexTestEmCasaTheme {

            }
        }
    }
}


@Composable
fun CustomAppBar(
    title: String,
    scrollOffset: Dp
) {
    Surface(
        color = Color.Red,
        elevation = if (scrollOffset > 0.dp) 4.dp else 0.dp,
        modifier = Modifier.height(150.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Custom app bar content
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier.clickable { /* Handle menu click */ }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ScrollWithCustomAppBar() {
    val scrollState = rememberLazyListState()
    val transition = updateTransition(targetState = scrollState.firstVisibleItemIndex)

    val appBarHeight by transition.animateDp { firstVisibleItemIndex ->
        if (firstVisibleItemIndex == 0) 150.dp else 50.dp
    }

    LaunchedEffect(scrollState.firstVisibleItemIndex) {
        val firstVisibleItemIndex = scrollState.firstVisibleItemIndex
        if (firstVisibleItemIndex == 0) {
            // Handle the case when the first item is visible
            // For example, update the app bar title or perform some action
            // when the first item becomes visible
            // You can replace the comment with your desired code
            println("First item is visible")
        } else {
            // Handle the case when scrolling through other items
            // For example, update the app bar title or perform some action
            // based on the scrolling position
            // You can replace the comment with your desired code
            println("Scrolling through other items")
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        CustomAppBar("Title", appBarHeight)
        LazyColumn(
            state = scrollState
        ) {
            items(100) { index ->
                Text("Item $index")
            }
        }
    }
}






