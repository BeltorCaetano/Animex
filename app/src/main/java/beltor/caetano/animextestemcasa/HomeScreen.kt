package com.example.animex

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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

class HomeScreen : ComponentActivity() {
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
//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageCard(LargeCardWidth: Dp = 130.dp, LargeCardHeight: Dp = 180.dp, SmallCardSize: Dp = 50.dp, textStart: Dp = 35.dp, textTop: Dp = 120.dp) {
    val context = LocalContext.current;
   var text = "Animex"
    Card(
        modifier = Modifier
            .width(LargeCardWidth)
            .height(LargeCardHeight)
            .padding(5.dp)
            .clickable { text = "Mudou card" }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.nature), // Replace with your image resource
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { text = "modou imagem" },

            )
            Card(  modifier = Modifier
                .width(SmallCardSize)
                .height(SmallCardSize),
            ) {
                Image(
                    painter = painterResource(R.drawable.floaty), // Replace with your image resource
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
Text(text = text, color = Color.White, modifier = Modifier.padding(top = textTop, start =  textStart))
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel) {

    Column(modifier = Modifier
        .fillMaxSize()
        .draggable(
            state = viewModel.dragState.value!!,
            orientation = Orientation.Horizontal,
            onDragStarted = { },
            onDragStopped = {
                viewModel.updateTabIndexBasedOnSwipe()
            }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {

            val list = (1..40).map { it.toString() }
            //  HomeScreen(viewModel = MainViewModel(application = Application()))
            LazyVerticalGrid(columns = GridCells.Fixed(3),
                content = {
                    items(list.size) { index ->
                        ImageCard()
                    }
                })
        }
    }
}
@Composable
private fun HallofFame(paddingValues: PaddingValues) {
    Column(modifier = Modifier
        .padding(paddingValues)
        .padding(top = 30.dp, start = 20.dp)) {
        Box() {
            Row() {
                Icon(painter = painterResource(id = R.drawable.trophy_winner_prize_svgrepo_com), contentDescription = "",tint = Color.Unspecified,modifier = Modifier.size(30.dp))
                Text(text = "Hall of fame",color = Color.White)
                Icon(painter = painterResource(id = R.drawable.trophy_winner_prize_svgrepo_com), contentDescription = "",tint = Color.Unspecified,modifier = Modifier.size(30.dp))
            }
        }
        val list = (1..40).map { it.toString() }
        //  HomeScreen(viewModel = MainViewModel(application = Application()))
        LazyHorizontalGrid(rows = GridCells.Fixed(1), modifier = Modifier.height(160.dp),
            content = {
                items(10) { index ->
                    val context = LocalContext.current;
                    var text = "Animex"
                    Card(
                        modifier = Modifier
                            .width(110.dp)
                            .height(180.dp)
                            .padding(5.dp)
                            .clickable { text = "Mudou card" }
                    ) {
                        Box() {
                            Image(
                                painter = painterResource(R.drawable.nature), // Replace with your image resource
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable { text = "modou imagem" },

                                )
                            Card(  modifier = Modifier
                                .width(40.dp)
                                .height(40.dp),
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.floaty), // Replace with your image resource
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Text(text = text, color = Color.White, modifier = Modifier.padding(top = 110.dp, start =  25.dp))
                        }
                    }
                }
            })

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimexTestEmCasaTheme {
        Scaffold(containerColor = MaterialTheme.colorScheme.onTertiaryContainer,topBar = {
            val context = LocalContext.current
            TopAppBar(title = {
                Text(text = "Search", fontSize = 13.sp)
            }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White),
                navigationIcon = {

                    Image(
                        painter = painterResource(R.drawable.profile_picture),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            // Set image size to 40 dp
                            .size(40.dp)
                            // Clip image to be shaped as a circle
                            .clip(CircleShape)
                    )
                    Icon(
                        imageVector = Icons.Filled.Search,
                        modifier = Modifier.padding(start = 60.dp,top = 10.dp),
                        contentDescription = null,
                        tint = Color.White
                    )
                }, actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                })
        }) {padding->
            Column() {
                HallofFame(padding)
                Text(text = "My Communities",modifier = Modifier.padding(start = 20.dp), color = Color.White)
                LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.padding(top = 3.dp, start = 20.dp),
                    content = {
                        items(6) { index ->
                            ImageCard(LargeCardWidth = 110.dp,LargeCardHeight = 160.dp, SmallCardSize = 40.dp, textStart = 25.dp, textTop = 110.dp)
                        }
                    })
                Button(onClick = { /*TODO*/ },
                    colors =  ButtonDefaults.buttonColors(containerColor =  colorResource(id = R.color.green_black).copy(alpha = 0.4f)),
                    border = BorderStroke(width = 0.5.dp, color =Color.Cyan),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 70.dp, end = 70.dp),shape = RoundedCornerShape(10.dp)) {
                    Text(text = "See All", color =  Color.Cyan)
                }

            }

        }
    }
}

