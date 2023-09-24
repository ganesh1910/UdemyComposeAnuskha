package com.gk.udemycomposeanuskha

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvShows {
                Toast.makeText(this, it.name, Toast.LENGTH_LONG).show()
                startActivity(InfoActivity.intent(this, it))
            }
        }
    }
}

@Composable
fun TvShows(selectedTvShow: (TvShow) -> Unit) {
    val tvShow = remember {
        TvShows.listTvShow
    }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = tvShow,
            itemContent = {
                TvShowListItem(tvShow = it, selectedTvShow = selectedTvShow)
            }
        )
    }
}

@Composable
fun TvShowListItem(tvShow: TvShow, selectedTvShow: (TvShow) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )

    ) {
        TvShowItem(tvShow = tvShow, selectedTvShow = selectedTvShow)
    }
}


@Composable
fun TvShowItem(tvShow: TvShow, selectedTvShow: (TvShow) -> Unit) {
    Row(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable { selectedTvShow(tvShow) }
    ) {
        Image(
            painter = painterResource(
                id = tvShow.image
            ),
            contentDescription = stringResource(id = R.string.app_name),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .height(140.dp)
                .width(100.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = tvShow.name,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = tvShow.desc,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = tvShow.year,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Black
                )
                Text(
                    text = "${tvShow.TDMB}",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Black
                )
            }
        }
    }

}
