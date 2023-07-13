package beltor.caetano.animextestemcasa

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle

import beltor.caetano.animextestemcasa.ui.theme.AnimexTestEmCasaTheme
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimexTestEmCasaTheme {
                ImageGrid(images = imageList) // Pass the context explicitly

            }
        }
    }
}
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
private fun toogleButtonLike(modifier: Modifier = Modifier, color : Color = Color.Black,toogleModifier: Modifier = Modifier.offset(x = -9.dp, y = 12.dp)) {
    //toggle button

    val checkState = remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = checkState.value, label = "")

    val myTint by transition.animateColor(label = "") { isChecked ->
        if (isChecked) {
            Color.Red
        } else {
            color
        }
    }
    val mySize by transition.animateDp(
        label = "",
        transitionSpec = {
            keyframes {
                durationMillis = 250
                65.dp at 0 with LinearOutSlowInEasing
                60.dp at 30 with FastOutLinearInEasing
            }
        }
    ) {25.dp}
Row( modifier = modifier) {
    IconToggleButton(
        checked = checkState.value,
        onCheckedChange ={
            checkState.value = !checkState.value
        } ) {
        Icon(
            imageVector = if(checkState.value) {
                Icons.Filled.Favorite
            } else {
                Icons.Filled.FavoriteBorder
            } ,
            contentDescription ="",
            modifier = Modifier.size(mySize),
            tint = myTint
        )

    }
    Text("2", color = color, fontWeight = FontWeight.Bold, modifier =  toogleModifier)
}

}
@Composable
private fun NoticeCard() {
    Box(
        modifier = Modifier
            .height(25.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Image(painter = painterResource(id =R.drawable.baseline_push_pin_24 ) , contentDescription ="" ,modifier = Modifier
            .graphicsLayer(alpha = 0.5f))
        Text(text = "notice about something yes",modifier = Modifier.padding(horizontal = 25.dp))
    }
}
@Composable
private fun TitleWithThreeDots(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Light,
    fontSize: TextUnit = 7.sp
) {
    val displayedText = remember(text) {
        if (text.length > 33) {
            text.take(33) + ".."
        } else {
            text
        }
    }
    Text(text = displayedText,fontSize = fontSize,fontWeight = fontWeight, modifier = modifier, color = color,  lineHeight = 17.sp)
}
@Composable
private fun TextWithThreeDots(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Light,
    fontSize: TextUnit = 7.sp,
    lineheight: TextUnit = 21.sp,
) {
    val displayedText = remember(text) {
        if (text.length > 85) {
            text.take(85) + "..."
        } else {
            text
        }
    }
    Text(text = displayedText,fontSize = fontSize,fontWeight = fontWeight, modifier = modifier, color = color, lineHeight = lineheight)
}
@Composable
fun CommentIcon(modifier: Modifier = Modifier, tint: Color = Color.Black) {
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = R.drawable.outline_comment_24),
            contentDescription = null,
            tint = tint
        )
        Text(text = "55", color = Color.White, modifier = Modifier.offset(x = 5.dp))
    }

}
@Composable
private fun PostCard(name: String = "Nothing", modifier: Modifier = Modifier) {
    Box() {
        Column(modifier = Modifier) {
            val list = (1..10).map { it.toString() }
            //  HomeScreen(viewModel = MainViewModel(application = Application()))

            Divider (
                color = Color.Black,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
            Box(
                //shape = MaterialTheme.shapes.medium,
                // modifier = modifier.size(280.dp, 240.dp)
                modifier = Modifier

                    .padding(10.dp, 5.dp, 10.dp, 0.dp)
                    .background(Color.White)
                    .height(320.dp)
                ,
                //set card elevation of the card
            ) {
                Column() {
                    Row(modifier = Modifier.padding(start = 4.dp)) {
                        Image(
                            painter = painterResource(R.drawable.postman),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .border(
                                    1.5.dp,
                                    MaterialTheme.colorScheme.onSecondary,
                                    CircleShape
                                )
                        )
                        Column() {
                            Text(
                                text = "The Mystery",
                                fontSize = 9.sp,
                                modifier = Modifier
                                    .padding(horizontal = 2.dp),
                                //maxLines = 1,
                                //overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleSmall,
                            )
                            Text(
                                text = "11 minutes ago",
                                fontSize = 6.sp,
                                modifier = Modifier
                                    .padding(horizontal = 2.dp),
                                //maxLines = 1,
                                //overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }
                    Text(
                        text = "Title about something irrelevant",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(horizontal = 2.dp),
                        //maxLines = 1,
                        //overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleSmall,
                    )

                    TextWithThreeDots(text = "Hello Compose".repeat(17),
                        fontWeight = FontWeight.Normal,
                        fontSize =10.sp,
                        lineheight = 11.sp)

                    Box(modifier = Modifier.height(232.dp)) {
                        Image(
                            painter = painterResource(R.drawable.vennassa),
                            contentDescription = null, // decorative
                            //contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    Row(modifier = Modifier.height(20.dp),) {
                        toogleButtonLike(toogleModifier =  Modifier.offset(y = -2.dp, x = -5.dp))
                        CommentIcon(modifier = Modifier.size(25.dp))
                    }


                }
            }

        }
    }



    // FeaturedPost()
}
@Composable
private fun FeaturedPost(imageResId: Int, contentScale: ContentScale = ContentScale.Crop, shape: Shape = RoundedCornerShape(8.dp)) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(shape),
        colors = CardDefaults.cardColors(
            containerColor =  Color.Yellow,
        ),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                contentScale = contentScale,
                modifier = Modifier.fillMaxSize()
            )

            Image(
                painter = painterResource(R.drawable.postman),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(35.dp)
                    .padding(start = 10.dp, bottom = 10.dp)
                    .clip(CircleShape)
                    .align(Alignment.BottomStart)
                    //.border(1.5.dp, MaterialTheme.colorScheme.onSecondary, CircleShape)
            )

            TextWithThreeDots("This is a player named Mbappé he is the" +
                    " bestr youngest player alive in the planet earth",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(x = 10.dp, y = -40.dp)
                ,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            toogleButtonLike(modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 35.dp),
                color = Color.White
            )
            CommentIcon(modifier = Modifier
                .size(25.dp)
                .align(Alignment.BottomStart)
                .offset(x = 90.dp, y = -10.dp),
                tint = Color.White
            )

        }

    }
}
@Composable
private fun Gridprofiles() {
    Box() {
        Column(modifier = Modifier
            .padding(top = 30.dp)) {

            val list = (1..10).map { it.toString() }
            //  HomeScreen(viewModel = MainViewModel(application = Application()))

            Image(
                painter = painterResource(R.drawable.profile_picture), // Replace with your image resource
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(80.dp)
                    .padding(9.dp)
                    .clip(CircleShape),
            )

        }
    }

}
@Composable
private fun FeaturedPosts() {
    Box() {
        Column() {

            val list = (1..10).map { it.toString() }
            //  HomeScreen(viewModel = MainViewModel(application = Application()))

            Box(
                modifier = Modifier
                    .width(180.dp)
                    .height(300.dp)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.vennassa),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(shape = RectangleShape)
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .fillMaxWidth()
                    ) {
                        Column() {
                            TitleWithThreeDots(
                                modifier = Modifier
                                    .offset(y = -3.dp),
                                text = "Vennesa",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                            TextWithThreeDots(
                                text = "Hello compose".repeat(10),
                                fontSize = 10.sp,
                                lineheight = 15.sp
                            )
                            Row() {
                                Image(
                                    painter = painterResource(R.drawable.postman),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .size(25.dp)
                                        .offset(y = 10.dp)
                                        .clip(CircleShape)
                                    //.border(1.5.dp, MaterialTheme.colorScheme.onSecondary, CircleShape)
                                )
                                toogleButtonLike(
                                    modifier = Modifier
                                        .padding(start = 5.dp),
                                    color = Color.Black
                                )
                                CommentIcon(
                                    modifier = Modifier
                                        .size(25.dp)
                                        .offset(y = 10.dp),
                                    tint = Color.Black
                                )
                            }
                        }

                    }

                }
            }

        }
    }

}
@Composable
private fun listPosts() {
    Column() {
        Text(text = "More Featured Posts")
        val list = (1..10).map { it.toString() }
        //  HomeScreen(viewModel = MainViewModel(application = Application()))
        LazyVerticalGrid(columns = GridCells.Fixed(1), modifier = Modifier.height(600.dp),
            content = {
                items(4) { index ->
    // put here the post
                }
            })
    }
}
@Composable
private fun MoreFeaturedPosts(imageResId: Int, contentScale: ContentScale = ContentScale.Crop, shape: Shape = RoundedCornerShape(8.dp)) {
    Box() {
        Column(modifier = Modifier
            .padding(top = 30.dp)) {

            val list = (1..10).map { it.toString() }
            //  HomeScreen(viewModel = MainViewModel(application = Application()))

            Card(
                modifier = Modifier
                    .width(130.dp)
                    .height(100.dp)
                    .padding(start = 10.dp)
                    .clip(shape),
                colors = CardDefaults.cardColors(
                    containerColor =  Color.Yellow,
                ),
            ) {

                Box(modifier = Modifier.fillMaxSize()) {

                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = null,
                        contentScale = contentScale,
                        modifier = Modifier.fillMaxSize()
                    )
                    TitleWithThreeDots(
                        "Title about something irrelevant kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk",
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(x = 10.dp, y = -40.dp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    toogleButtonLike(
                        modifier = Modifier
                            .align(Alignment.BottomStart),
                        color = Color.White
                    )
                    //  Text(text = "2")
                    CommentIcon(
                        modifier = Modifier
                            .size(25.dp)
                            .align(Alignment.BottomStart)
                            .offset(x = 55.dp, y = -10.dp),
                        tint = Color.White
                    )

                }
            }


        }
    }

}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyCommunity() {


    LazyColumn(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()// first, set the max size
        //.verticalScroll(rememberScrollState())
    ) {
        item { NoticeCard() }

        item { FeaturedPost(imageResId = R.drawable.mbappe) }

        item {
            LazyRow(horizontalArrangement = Arrangement.Center) {
                items(2) {FeaturedPosts() }
            }
        }

        item {
            LazyRow() {
                items(2) {FeaturedPosts() }
            }
        }
        item {
            Text(text = "More Featured Posts")
            LazyRow(){
                items(10) {
                    MoreFeaturedPosts(imageResId = R.drawable.mbappe)
                }
            }
        }
        item {
            Text(text = "Welcome our new members!")
            LazyRow(){
                items(10) {
                    Gridprofiles()
                }

            }
        }
        items(10){

            PostCard()
        }


        // CustomBottomAppBar()
    }




}

@Composable
 fun myHomeTest() {
Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
    NoticeCard()
    FeaturedPost(imageResId = R.drawable.mbappe)
    LazyRow(horizontalArrangement = Arrangement.Center) {
        items(2) {FeaturedPosts() }
    }
    LazyRow(){
        items(10) {
            MoreFeaturedPosts(imageResId = R.drawable.mbappe)
        }
    }
    LazyRow() {
        items(2) {FeaturedPosts() }
    }
    LazyRow() {
        items(2) {FeaturedPosts() }
    }
    Text(text = "More Featured Posts")
    LazyRow(){
        items(10) {
            MoreFeaturedPosts(imageResId = R.drawable.mbappe)
        }
    }
    Text(text = "Welcome our new members!")
    LazyRow(){
        items(10) {
            Gridprofiles()
        }
    }
    PostCard()
}
}

@Composable
fun GreetingPreview() {
    AnimexTestEmCasaTheme {
        MyCommunity()
    }
}
@Composable
private fun SliderImage(context: Context = LocalContext.current) {
    AndroidView(
        factory = { innerContext ->
            val view = LayoutInflater.from(innerContext).inflate(R.layout.carousel, null, false)
            val imageSlider = view.findViewById<ImageSlider>(R.id.imageSlider)
            val imageList = ArrayList<SlideModel>()
            imageList.add(SlideModel(R.drawable.mbappe))
            imageList.add(SlideModel(R.drawable.vennassa))

            imageSlider.setImageList(imageList, ScaleTypes.FIT)

            view
        },
        update = { view ->
            // Update the view if needed
        }
    )
}
val imageList = listOf(
    R.drawable.postman,
    R.drawable.vennassa,
    R.drawable.casaco,
    // Add more images as needed
)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageGrid(images: List<Int>) {
    //val pagerState = rememberPagerState()
            Image(
                painter = painterResource(R.drawable.profile_picture),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .width(120.dp)
                    .height(120.dp)
                    .clickable { /* Handle image click here */ }
            )
}

