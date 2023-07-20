package com.pr7.jetpackpaging

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.pr7.jetpackpaging.ui.theme.JetpackPagingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            pr777()
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun pr777() {

    val viewModel = hiltViewModel<MainViewModel>()

    val articles = viewModel.getListData().collectAsLazyPagingItems()

    LazyColumn {
        itemsIndexed(
            items = articles,

            ) { index, item ->
            Text(
                modifier = Modifier
                    .height(75.dp),
                text = item?.name ?: "",
            )

            Divider()
        }
    }

}







