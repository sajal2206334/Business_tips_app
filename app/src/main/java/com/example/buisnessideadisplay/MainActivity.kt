@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.buisnessideadisplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buisnessideadisplay.data.Datasource
import com.example.buisnessideadisplay.model.Tip
import com.example.compose.BuisnessIdeaDisplayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuisnessIdeaDisplayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessTipApp()
                }
            }
        }
    }
}

@Composable
fun BusinessTipApp() {
    Scaffold(
        topBar = {
            TopAppBar()
            Spacer(modifier = Modifier.height(12.dp))
        }
    ) { it ->
        LazyColumn(contentPadding = it,modifier = Modifier) {
            items(Datasource.tips) {
                    tip ->
                BusinessTipCard(tips = tip)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

    }
}

@Composable
fun BusinessTipCard(tips : Tip, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(targetValue = if (expanded) Color.Magenta
    else MaterialTheme.colorScheme.primaryContainer)
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),modifier = Modifier
        .fillMaxWidth()
        .animateContentSize()
        .height(if (expanded) 750.dp else 300.dp)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { expanded = !expanded }
        .clip(MaterialTheme.shapes.medium)
        .padding(start = 16.dp, end = 16.dp)) {
        Column(modifier = Modifier) {
            Text(text = stringResource(tips.day),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 4.dp,
                    top = 4.dp,
                    end = 4.dp,
                    bottom = 2.dp))
            Text(
                text = stringResource(tips.title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(start = 4.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(tips.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(height = 200.dp, width = 300.dp)
                        .clip(MaterialTheme.shapes.small),
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(tips.description),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxHeight()
                )
            }
        }
    }
}

@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(title = {
        Row(modifier = Modifier) {
            Text(text = "Business Tips",
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Cursive,
                fontSize = 32.sp)
        }
    })
}

@Preview
@Composable
fun GreetingPreview() {
    BuisnessIdeaDisplayTheme {
        BusinessTipApp()
    }
}