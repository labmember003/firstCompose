package com.falcon.firstcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falcon.firstcompose.ui.NotificationScreen
import com.falcon.firstcompose.ui.listItemPreview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            listItemPreview()
//            CircularImage()
//            Recomposable()
            NotificationScreen()
        }
    }
}

@Composable
fun SayCheezy(name: String) {
    Text(text = "hello $name")
}

@Composable
fun PreviewFunction() {
    Text(
        text = "cat",
        fontSize = 60.sp,
        color = Color.Magenta,
        modifier = Modifier
            .background(Color.Blue)
            .size(200.dp)
            .padding(16.dp)
            .border(4.dp, Color.Red)
            .clip(CircleShape)
//                // Clip is basically crop to
//                // baaki portion ht jayega
            .background(Color.Yellow)
            .clickable {

            }
            .fillMaxSize()
    )
}

@Composable
fun TextInput(input: String) {
    val value = remember{mutableStateOf(input)}
    TextField(
        value = value.value,
        onValueChange = {
            value.value = it
        },
        label = {
            Text(text = "Enter Message")
        }
    )
}

//@Preview(showBackground = true, name = "hello message", showSystemUi = true)
@Composable
private fun PreviewSayCheezy() {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "A", fontSize = 32.sp)
        Text(text = "B", fontSize = 32.sp)
    }
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "A", fontSize = 32.sp)
        Text(text = "B", fontSize = 32.sp)
    }
}

@Preview(showBackground = true, name = "hello message", showSystemUi = true)
@Composable
private fun PreviewSayCheezy2() {
    Column() {
        listViewItem(R.drawable.iconbird, "Cat","Cat Doctor", Modifier.background(Color.Blue))
        listViewItem(R.drawable.iconbird, "Dog","Dog Doctor", Modifier.size(200.dp))
        listViewItem(R.drawable.iconbird, "Cat","Cat Doctor", Modifier.size(200.dp))
        listViewItem(R.drawable.iconbird, "Cat","Cat Doctor", Modifier.size(200.dp))
    }
}

@Composable
private fun listViewItem(imageId: Int, name: String, description: String, modifier: Modifier = Modifier) {
    Row (modifier = modifier.border(5.dp, Color.Red)) {
        Image(painter = painterResource(id = imageId), contentDescription = "", modifier = Modifier.size(40.dp))
        Column {
            Text(text = name, fontWeight = FontWeight.ExtraBold)
            Text(text = description, fontWeight = FontWeight.Thin)
        }
    }
}

@Composable
fun CircularImage() {
    Image (
        painter = painterResource(id = R.drawable.notesappicon),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(Color.Yellow)
            .border(4.dp, Color.Red, CircleShape)
    //shape is necessary if image composable was made circular
    )
}



@Composable
fun Cat(data: String) {
    Text(text = data)
}
// when it will be recalled

// composable mei long running operation nhi rkhna, background thread mei krlena
// background thread?? courotines?? couroutine composable ke ander se call krna hoga?


// likhe sequence mei hai mtlb ye NHI hai ki sequence mei composable execute honge, first ki cheez second mei use kr rrhe ho aisa nhi krna

// compose ke ander koi bahar ki cheez nhi set krni hoti like database update
// tho krna kaise hoga? mtlb onClick mei tho data set get tho krenge he na...nhi??
@Preview
@Composable
fun Catt(data: String = "Cat") {
    Button(onClick = {

    }, ) {
        Text(text = "SEX")
    }
}

// state change hui, recomposition ho rrha tha but state tb he change ho gyi,
// so voh current recomposition cancel ho jayega and then dubara recomposition chalu ho jaata hai

@Composable
fun Recomposable() {
    val state = remember { mutableStateOf(0.0) }
    Log.d("TAGGED","Logged during Initial Composition")
    Button (onClick = {
        state.value = Math.random()
        Log.d("TAGGED", "Logged during both Composition & Recomposition")
    }) {

        Text(text = state.value.toString())
    }
}
// jaaha state read/write hui hai voh recompose hoga....state ki value change hone per Button
// Composable bhi recompose hoga