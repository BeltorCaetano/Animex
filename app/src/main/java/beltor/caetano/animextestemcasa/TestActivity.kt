package beltor.caetano.animextestemcasa

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.R
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import beltor.caetano.animextestemcasa.ui.theme.AnimexTestEmCasaTheme
import com.example.animex.ScrollableBar.Book
import com.example.animex.ScrollableBar.BookModel
import com.example.animex.ScrollableBar.COLLAPSED_TOP_BAR_HEIGHT
import com.example.animex.ScrollableBar.DEFAULT_BOOKS
import com.example.animex.ScrollableBar.EXPANDED_TOP_BAR_HEIGHT
import com.example.animex.ScrollableBar.listStateTest
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class TestActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimexTestEmCasaTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}
/*
@Composable
fun BoxLibrary( books: List<BookModel> = DEFAULT_BOOKS) {
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
                    painter = painterResource(beltor.caetano.animextestemcasa.R.drawable.vennassa),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Image(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(beltor.caetano.animextestemcasa.R.drawable.vennassa),
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
            painter = painterResource(beltor.caetano.animextestemcasa.R.drawable.nature),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column() {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = beltor.caetano.animextestemcasa.R.drawable.baseline_arrow_back_24),
                    tint = Color.White,
                    modifier = Modifier.padding(16.dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(160.dp))
                Icon(
                    painter = painterResource(id = beltor.caetano.animextestemcasa.R.drawable.baseline_store_24),
                    tint = Color.White,
                    modifier = Modifier.padding(16.dp),
                    contentDescription = null
                )
                Icon(
                    painter = painterResource(id = beltor.caetano.animextestemcasa.R.drawable.baseline_notifications_24),
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
                        painter = painterResource(id = beltor.caetano.animextestemcasa.R.drawable.profile_picture),
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
@OptIn( ExperimentalFoundationApi::class)
@Composable
fun Greeting() {
    val pagerState = rememberPagerState()
    val pagerPage = remember { mutableStateOf(0) }
    
    val collectionTabs = arrayListOf("Home","About","tab3","Tab4","Tab5","Tab7")
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = pagerState.currentPage) {
        pagerPage.value = pagerState.currentPage
    }
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            MyTopAppBar()
            ScrollableTabRow(
                containerColor = Color.Transparent,
                edgePadding = 0.dp,
                selectedTabIndex = minOf(collectionTabs.count(), pagerPage.value),
                tabs = {
                    collectionTabs.forEachIndexed { index, tabName ->
                        Tab(
                            onClick = { pagerPage.value = index
                                scope.launch { pagerState.animateScrollToPage(index) }
                            },
                            selected = index == pagerState.currentPage,
                            text ={Text(tabName)}
                        )
                    }
                }
            )

            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Top,
                pageCount = collectionTabs.count(),
                state = pagerState,
                userScrollEnabled = true
            ) {tabIndex ->
                when (tabIndex) {
                    0 -> MyCommunity()
                    1 -> AboutScreen()
                }
            }
        }
}
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun GreetingPreview2() {
    AnimexTestEmCasaTheme {
     LazyColumn( modifier = Modifier
         .background(color = Color.White)
         .fillMaxSize()){
         item {  }
         item { Greeting() }
     }
    }
}
@Composable
fun HomeScreen() {
    Text(text = "HomeSCreen")
}
@Composable
fun AboutScreen() {
    Text(text = "AboutSCreen")
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PagerScreen() {
    // Tabs for pager
    val tabData = listOf(
        "Tab 1",
        "Tab 2",
    )

    // Pager state
    val pagerState = rememberPagerState()

    // Coroutine scope for scroll pager
    val coroutineScope = rememberCoroutineScope()

    // Scroll behavior for TopAppBar
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(text = "Top app bar")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.surface
                ),
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                content = {
                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
                        tabs = {
                            tabData.forEachIndexed { index, title ->
                                Tab(
                                    text = { Text(title) },
                                    selected = pagerState.currentPage == index,
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    },
                                )
                            }
                        }
                    )

                    HorizontalPager(
                        modifier = Modifier.fillMaxSize(),
                        pageCount = tabData.size,
                        state = pagerState,
                    ) { tabId ->
                        when (tabId) {
                            0 -> Tab1(scrollBehavior = scrollBehavior)
                            1 -> Tab2(scrollBehavior = scrollBehavior)
                        }
                    }
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tab1(scrollBehavior: TopAppBarScrollBehavior) {
    // List items
    val listItems = listOf(
        "test 1 tab 1",
        "test 2 tab 1",
        "test 3 tab 1",
        "test 4 tab 1",
        "test 5 tab 1",
        "test 6 tab 1",
        "test 7 tab 1",
        "test 8 tab 1",
        "test 9 tab 1",
        "test 10 tab 1",
        "test 11 tab 1",
        "test 12 tab 1",
    )

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        state = listState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(items = listItems) { item ->
                Card(
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth(),

                    content = { Text(text = item) }
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tab2(scrollBehavior: TopAppBarScrollBehavior) {
    // List items
    val listItems = listOf(
        "test 1 tab 2",
        "test 2 tab 2",
        "test 3 tab 2",
        "test 4 tab 2",
        "test 5 tab 2",
        "test 6 tab 2",
        "test 7 tab 2",
        "test 8 tab 2",
        "test 9 tab 2",
        "test 10 tab 2",
        "test 11 tab 2",
        "test 12 tab 2",
    )

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        state = listState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(items = listItems) { item ->
                Card(
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth(),

                    content = { Text(text = item) }
                )
            }
        }
    )
}*/