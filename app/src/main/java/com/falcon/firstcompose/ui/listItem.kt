package com.falcon.firstcompose.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.falcon.firstcompose.R


@Preview(heightDp = 300)
@Composable
fun listItemPreview() {
    val data = getDemoData()
//    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//        data.forEach {
//            ListItem(it.imageId, it.title, it.description)
//        }
//    }
    LazyColumn(content = {
        items(getDemoData()) {
            ListItem(it.imageId, it.title, it.description)
        }
    })

}

@Composable
fun ListItem(imageId: Int, title: String, description: String) {
    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "",
                modifier = Modifier.weight(0.2f)
            )
            ItemDescription(title, description, Modifier.weight(0.8f))
        }
    }
}

@Composable
fun ItemDescription(title: String, description: String, modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(text = title, fontWeight = FontWeight.ExtraBold)
        Text(text = description, fontWeight = FontWeight.Thin)
    }
}

data class ListData(val imageId: Int, val title: String, val description: String)

fun getDemoData(): List<ListData> {
    val list = mutableListOf<ListData>()
    list.add(ListData(R.drawable.iconbird, "First Cat", "First cat description"))
    list.add(ListData(R.drawable.iconbird, "Second Cat", "First cat description"))
    list.add(ListData(R.drawable.iconbird, "third Cat", "First cat description"))
    list.add(ListData(R.drawable.iconbird, "fourth Cat", "First cat description"))
    list.add(ListData(R.drawable.iconbird, "First Cat", "First cat description"))
    list.add(ListData(R.drawable.iconbird, "Second Cat", "First cat description"))
    list.add(ListData(R.drawable.iconbird, "third Cat", "First cat description"))
    list.add(ListData(R.drawable.iconbird, "fourth Cat", "First cat description"))
    return list
}


// STATE DEFINED HAI ISS COMPOSABLE MEI SO ITS CALLED STATE FULL COMPOSABLE
@Composable
fun NotificationScreen() {
    val count = rememberSaveable{mutableStateOf(0)}
    // state is maintained in view models
    // view model give data to our top level composable and then they go down
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier. fillMaxSize(1f)
    ) {

        NotificationCounter(count.value) { count.value++ }
        MessageBar(count.value)
    }
}


// STATE LESS COMPOSABLE
@Composable
fun NotificationCounter(count: Int, increament: () -> Int) {
//    val count = remember{mutableStateOf(0)}
//    val count = rememberSaveable{mutableStateOf(0)}
    // Count ek State ka object hai
    // To be exact MutableState ka

    // on configuration changes
    // activity dubara se bni hai so new compositon bnegi
    // fresh se start kiya hai composable, old value bhi hatt gyi
    // uske use remember with bundle ->

    // similar to view model in view system activity destroy hone se pehle values save kr leta tha
    Column(verticalArrangement = Arrangement.Center) {
        Text(text = "You have sent $count notifications")
        Button(onClick = {
//            count += 1
            increament()
            Log.d("CODERSTAG", "Button Clicked")
        }) {
            Text(text = "Send Notification")
        }

    }
}

// STATE less composable
@Composable
fun MessageBar(count: Int) {
    Card(
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = Icons.Outlined.Favorite,
                // In build icons, many, Icons. etc etc
                contentDescription = "",
                modifier = Modifier.padding(8.dp)
            )
            Text(text = "Messaages Sent So Far - $count")
        }
    }
}
// moving state to where funciton was called this is called - state hoisting
// so we moved state where MessageBar and NotificationCounter was called
// because both needed it
// aur kuch incrementation etc kuch krna ho tho, jaaha state define krri wahi se ek lambda pass krdena to alter state value


//9:10
@Composable
fun MessageBar2() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
        // Make card in centre
    ) {
        Card(elevation = 10.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    imageVector = Icons.Rounded.Call,
                    // In build icons, many, Icons. etc etc
                    contentDescription = "",
                    modifier = Modifier.padding(8.dp)
                )
                Text(text = "Messaages Sent To Far")
            }
        }
    }
}

// moving state to where funciton was called this is called - state hoisting


// Unidirectional flow - data tumhara top level composable se bottom level composable tk jayewga
// aur Similarly events upar ki traf jaate hai (parent composable ko bola handling tu kr liyo)


// multiple data setters hote tho that would be very confusing, ek jgeh data set hoga and dusri jgeh se krna hai tho uske lambda passs krlenge