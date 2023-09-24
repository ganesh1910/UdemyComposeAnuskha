package com.gk.udemycomposeanuskha

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class InfoActivity : ComponentActivity() {

    companion object {
        private val TV_SHOW_ID = "tvshowId"
        fun intent(context: Context, tvShow: TvShow) = Intent(
            context, InfoActivity::class.java
        ).apply {
            putExtra(TV_SHOW_ID, tvShow)
        }
    }

    val tvShow: TvShow by lazy {
        intent?.getSerializableExtra(TV_SHOW_ID) as TvShow
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfoCard(tvShow = tvShow)
        }
    }
}

@Composable
fun InfoCard(tvShow: TvShow) {
    val scrollState = rememberScrollState()
    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .scrollable(state = scrollState, orientation = Orientation.Vertical),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 15.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Info(tvShow = tvShow)
    }
}

@Composable
fun Info(tvShow: TvShow) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Image(
            painter = painterResource(id = tvShow.image),
            contentDescription = stringResource(id = R.string.app_name),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = tvShow.name,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = tvShow.desc,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "TDBM : ${tvShow.TDMB}",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Year : ${tvShow.year}",
            style = MaterialTheme.typography.titleLarge
        )
    }
}