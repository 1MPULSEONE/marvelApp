package com.example.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvelapp.model.Hero
import com.example.marvelapp.repository.HeroRepository
import com.example.marvelapp.ui.theme.MarvelAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelAppTheme {
                rememberSystemUiController().setStatusBarColor(Color.DarkGray)
                val heroesList = HeroRepository().getAllHeroes()
                HeroScreen(heroesList)
            }
        }
    }
}

@Composable
@Suppress("Compose function")
fun HeroScreen(placeHolderItems: List<Hero>) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = colorResource(id = R.color.grey_background)
    ) {}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.marvel_logo),
            modifier = Modifier
                .height(70.dp)
                .width(150.dp),
            contentDescription = stringResource(id = R.string.marvel_logo_description)
        )

        Text(
            text = stringResource(id = R.string.choose_your_hero),
            fontSize = 32.sp,
            color = colorResource(id = R.color.white),
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 48.dp, top = 18.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(placeHolderItems) { hero ->
                HeroCard(hero = hero)
            }
        }
    }
}

@Composable
@Suppress("Compose function")
fun HeroCard(hero: Hero) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(18.dp))
        ) {
            Image(
                painter = painterResource(id = hero.imageId),
                modifier = Modifier
                    .fillMaxSize()
                    .width(345.dp)
                    .height(400.dp),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(id = R.string.background_hero_image)
            )
            Text(
                text = hero.name,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 40.dp, start = 12.dp),
                fontSize = 38.sp,
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold,

                )
        }
    }

}

@Preview(showBackground = true)
@Composable
@Suppress("Compose function")
fun PreviewHeroScreen() {
    MarvelAppTheme {
        HeroScreen(HeroRepository().getAllHeroes())
    }
}

@Preview(showBackground = true)
@Composable
@Suppress("Compose function")
fun ShowHeroCard() {
    MarvelAppTheme {
        HeroCard(Hero(0, "Captain America", R.drawable.captain_america_image_test))
    }
}
