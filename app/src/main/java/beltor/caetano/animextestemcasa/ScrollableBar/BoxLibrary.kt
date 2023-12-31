package com.example.animex.ScrollableBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun BoxLibrary(books: List<BookModel> = DEFAULT_BOOKS) {
    val listState = rememberLazyListState()

    val overlapHeightPx = with(LocalDensity.current) {
        EXPANDED_TOP_BAR_HEIGHT.toPx() - COLLAPSED_TOP_BAR_HEIGHT.toPx()
    }

    val isCollapsed: Boolean by remember {
        derivedStateOf {
            val isFirstItemHidden = listState.firstVisibleItemScrollOffset > overlapHeightPx
            isFirstItemHidden || listState.firstVisibleItemIndex > 0
        }
    }

    Box {
        CollapsedTopBar(modifier = Modifier.zIndex(2f), isCollapsed = isCollapsed)
        LazyColumn(state = listState) {
            item { ExpandedTopBar() }
            items(items = books) { book ->
                Book(model = book)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun CollapsedTopBar(modifier: Modifier = Modifier, isCollapsed: Boolean) {
    val color: Color by animateColorAsState(
        if (isCollapsed) MaterialTheme.colors.background else Color.Red
    )
    Box(
        modifier = modifier
            .background(color)
            .fillMaxWidth()
            .height(COLLAPSED_TOP_BAR_HEIGHT)
            .padding(10.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        AnimatedVisibility(visible = isCollapsed) {
            Row() {
                /*Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),


                    contentDescription = null
                )*/
                Spacer(modifier = Modifier.width(10.dp))
               /* Image(
                    modifier = Modifier
                        .width(50.dp)

                        ,

                    painter = painterResource(R.drawable.library),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )*/
                Spacer(modifier = Modifier.width(10.dp))
                Text( text = "Genshin Impact", style = MaterialTheme.typography.h6)

            }

        }
    }
}

@Composable
fun ExpandedTopBar() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
            .height(EXPANDED_TOP_BAR_HEIGHT),
        contentAlignment = Alignment.BottomStart
    ) {
        /*Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )*/
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Library",
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h3,
        )
    }
}

@Preview
@Composable
private fun CollapsedTopBarPreview() {
    Column {
        CollapsedTopBar(isCollapsed = true)
        Spacer(Modifier.height(16.dp))
        CollapsedTopBar(isCollapsed = false)
    }
}

@Preview
@Composable
private fun ExpandedTopBarPreview() {
    BoxLibrary()
}
