package com.example.animex

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import beltor.caetano.animextestemcasa.R
import beltor.caetano.animextestemcasa.ui.theme.AnimexTestEmCasaTheme
import com.example.animex.ScrollableBar.Book
import com.example.animex.ScrollableBar.BookModel
import com.example.animex.ScrollableBar.COLLAPSED_TOP_BAR_HEIGHT
import com.example.animex.ScrollableBar.DEFAULT_BOOKS
import com.example.animex.ScrollableBar.EXPANDED_TOP_BAR_HEIGHT
import com.example.animex.ScrollableBar.listStateTest
import java.text.NumberFormat
import java.util.Locale

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimexTestEmCasaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyScreen()
                }
            }
        }
    }
}

@Composable
fun BoxLibrary(books: List<BookModel> = DEFAULT_BOOKS ) {
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
        Text(text = listStateTest.firstVisibleItemIndex.toString())

        LazyColumn(state = listState) {
            item { ExpandedTopBar(name = "Genshin Impact", members = NumberFormat.getNumberInstance(
                Locale.US).format(87668)) }
            items(items = books) { book ->
                Book(model = book)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

}

@Composable
fun CollapsedTopBar(modifier: Modifier = Modifier, isCollapsed: Boolean) {
    val color: Color by animateColorAsState(
        if (isCollapsed) androidx.compose.material.MaterialTheme.colors.background else Color.Transparent
    )
    Box(
        modifier = modifier
            .background(color)
            .fillMaxWidth()
            .height(COLLAPSED_TOP_BAR_HEIGHT),
        contentAlignment = Alignment.BottomStart
    ) {
        AnimatedVisibility(visible = isCollapsed) {

            Row() {

                //Spacer(modifier = Modifier.width(10.dp))
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(R.drawable.plant),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Image(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(R.drawable.plant),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                //Spacer(modifier = Modifier.width(10.dp))
                //androidx.compose.material.Text( text = "Genshin Impact", style = androidx.compose.material.MaterialTheme.typography.h6)
            }
        }
    }
}

@Composable
fun ExpandedTopBar(name: String, members: String) {

    Box(
        modifier = Modifier
            .background(androidx.compose.material.MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
            .height(EXPANDED_TOP_BAR_HEIGHT),
        contentAlignment = Alignment.BottomStart
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.nature),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column() {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    tint = Color.White,
                    modifier = Modifier.padding(16.dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(160.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_store_24),
                    tint = Color.White,
                    modifier = Modifier.padding(16.dp),
                    contentDescription = null
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_notifications_24),
                    tint = Color.White,
                    modifier = Modifier.padding(16.dp),
                    contentDescription = null
                )
            }

            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.size(width = 80.dp, height = 80.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_picture),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,

                        )

                    Spacer(modifier = Modifier.height(4.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text =  members,
                            fontSize =  16.sp,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text =  "Members",
                            fontSize =  14.sp,
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )


                    }
                }
            }
        }
    }
}
/*
@Preview
@Composable
private fun CollapsedTopBarPreview() {
    Column {
        CollapsedTopBar(isCollapsed = true)
        //Spacer(Modifier.height(16.dp))
        //CollapsedTopBar(isCollapsed = false)
    }
}


@Preview
@Composable
private fun ExpandedTopBarPreview2() {
    BoxLibrary()
}

@Preview
@Composable
private fun ExpandedTopBarPreview() {
    ExpandedTopBar(name = "Genshin Impact", members = NumberFormat.getNumberInstance(Locale.US).format(87668))
}

 */
//fim de App Bar



@Composable
fun TabLayout(viewModel: MainViewModel) {
    val tabIndex = viewModel.tabIndex.observeAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        ScrollableTabRow(selectedTabIndex = tabIndex.value!!) {
            viewModel.tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex.value!! == index,
                    onClick = { viewModel.updateTabIndex(index) }
                )
            }
        }

        when (tabIndex.value) {
            0 -> HomeScreen(viewModel = viewModel)
            1 -> AboutScreen(viewModel = viewModel)
            2 -> SettingsScreen(viewModel = viewModel)
            3 -> MoreScreen(viewModel = viewModel)
            4 -> SomethingScreen(viewModel = viewModel)
            5 -> EverythingScreen(viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize()){
        item { BoxLibrary() }
        item { TabLayout(MainViewModel(Application())) }
    }


}


@Composable
fun CardElevation(name: String,members: String) {
    Box(
        modifier = with (Modifier){

            fillMaxWidth()
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.nature),
                    contentScale = ContentScale.FillBounds
                )
                .height(20.dp)

        }) {
        Column() {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    tint = Color.White,
                    modifier = Modifier.padding(16.dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(160.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_store_24),
                    tint = Color.White,
                    modifier = Modifier.padding(16.dp),
                    contentDescription = null
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_notifications_24),
                    tint = Color.White,
                    modifier = Modifier.padding(16.dp),
                    contentDescription = null
                )
            }

            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.size(width = 80.dp, height = 80.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_picture),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,

                        )

                    Spacer(modifier = Modifier.height(4.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text =  members,
                            fontSize =  16.sp,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text =  "Members",
                            fontSize =  14.sp,
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConversation() {
    AnimexTestEmCasaTheme {
        MyScreen()
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewConversation2() {
    AnimexTestEmCasaTheme {
        CardElevation(name="test name", members = "123456")
    }
}


//test from here