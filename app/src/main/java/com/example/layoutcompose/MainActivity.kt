package com.example.layoutcompose

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.layoutcompose.ui.theme.LayoutComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutComposeTheme {
                ListView()
            }
        }
    }
}
@Composable
 fun ListView(){
    val listSize=1000
    val state= rememberLazyListState()
    val continueScope= rememberCoroutineScope()
   Column {
       Row {
         Button(onClick = {
             continueScope.launch {
                 state.animateScrollToItem(0)
             }
         }) {
            Text("Move on Top")
         }
           Button(onClick = { continueScope.launch {
               state.animateScrollToItem(listSize-1)
           } }) {
               Text("Move on the last")
           }
       }
   }
    LazyColumn(state = state,modifier = Modifier.padding(50.dp)) {
        items(1000){
            ImageAdder(it)
        }
    }
}
@Composable
fun ImageAdder(index: Int){
    Row(verticalAlignment = Alignment.CenterVertically){
        Image( painter = rememberImagePainter(data ="https://developer.android.com/images/brand/Android_Robot.png")
            ,contentDescription = "Android Image"
            ,modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text(text = "Item index #$index number",style = MaterialTheme.typography.subtitle1)
    }
}
@Composable
fun LayoutCompose(){
   Scaffold(
       topBar ={
           TopAppBar(
           title = {
               Text(text = "Top App Bar")}
            ,actions = {
               IconButton(onClick = { /*TODO*/ }) {
                   Icon(Icons.Filled.Favorite, contentDescription = null )
               }   
             }
           )
       }
   ){

   }
}
@Composable
fun BodyContent(modifier: Modifier=Modifier){
    Column(modifier = modifier.padding(8.dp)) {
        Text(text = "Hii I am Here")
        Text(text = "Hey I loves This Material Style Nice")
    }
}

@Composable
fun PhotoGraphCard(modifier: Modifier = Modifier){
    Row(
        modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = { })
            .padding(16.dp)) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {

        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text("Dr. A.P.J Abdul Kalam", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 Min Ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LayoutComposeTheme{
        ListView()
    }
}