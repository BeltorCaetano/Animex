package co.joebirch.composetv

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.Carousel
import androidx.tv.material3.CarouselState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import beltor.caetano.animextestemcasa.ui.theme.AnimexTestEmCasaTheme
import co.joebirch.composetv.DataFactory.makeCarouselItem
import coil.compose.AsyncImage

@ExperimentalAnimationApi
@ExperimentalTvMaterial3Api
@Composable
fun HomeCarousel(
    modifier: Modifier = Modifier
) {
    val items = makeCarouselItem()
    val state = remember {
        CarouselState()
    }
    Carousel(
        modifier = modifier,
        carouselState = state,
        autoScrollDurationMillis = 7500,
        slideCount = items.count(),
        content = { index ->
            val transform = ContentTransform(
                targetContentEnter = fadeIn(tween(durationMillis = 1000)),
                initialContentExit = fadeOut(tween(durationMillis = 1000))
            )
            items[index].also { item ->
                CarouselItem(
                    contentTransformForward = transform,
                    contentTransformBackward = transform,
                    background = {
                        AsyncImage(
                            item.image, null, contentScale = ContentScale.Crop
                        )
                    },
                    content = {
                        HomeItemBody(item)
                    }
                )
            }
        })
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalTvMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun GreetingPreview6() {
    AnimexTestEmCasaTheme {
        HomeCarousel(
            modifier = Modifier.fillMaxSize()
        )
    }
}