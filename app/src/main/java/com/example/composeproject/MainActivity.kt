package com.example.composeproject

import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.composeproject.ui.screens.community.AllReviewMenuHeader
import com.example.composeproject.ui.theme.ComposeProjectTheme
import com.example.composeproject.ui.utils.InfiniteListHandler
import com.example.composeproject.ui.utils.dpToSp
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MainViewModel: ViewModel() {
    val mutex = Mutex()

    private val _sectionState = mutableStateListOf<String>("1", "2", "3")
    val sectionState: List<String> = _sectionState

    private val _liveDatas = MutableLiveData<String>("")
    val liveData: LiveData<String> = _liveDatas

    fun loadMoreData() {

        CoroutineScope(Dispatchers.Default).launch {
            mutex.withLock {
                var array: ArrayList<String> = arrayListOf()
                for (index in 1..3) {
                    array.add("${_sectionState.size + index}")
                }
                _sectionState.addAll(array)
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeProjectTheme {
                StickyList(sections = viewModel.sectionState)
            }
        }
    }
}

@Composable
fun ReviewHeader() {
    Column(
        Modifier
            .fillMaxWidth()
    ) {
        // Sub & Main Title
        Text(
            modifier = Modifier
                .padding(18.dp, 0.dp, 18.dp, 0.dp),
            text = "Sub Title",
            fontSize = dpToSp(dp = 14.dp)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .padding(18.dp, 0.dp, 18.dp, 0.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                text = "Main Title",
                fontSize = dpToSp(dp = 20.dp))
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                text = "more view",
                fontSize = dpToSp(dp = 12.dp))
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))
        // Promotion Info & State icon
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier
                .width(18.dp))

            Box(modifier = Modifier
                .weight(1f)
                .aspectRatio(339f / 358f)) {

                Box {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .aspectRatio(1f / 1f)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier
                        .aspectRatio(1f / 1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0x29222222)))

                    Column(modifier = Modifier.align(Alignment.BottomStart)) {
                        // Brand
                        Text(modifier = Modifier.fillMaxWidth().padding(12.dp, 0.dp, 105.dp, 3.dp), text = "Brand", fontSize = dpToSp(dp = 14.dp), color = Color.White)
                        // Title
                        Text(modifier = Modifier.fillMaxWidth().padding(12.dp, 3.dp, 105.dp, 3.dp), text = "Title", fontSize = dpToSp(dp = 20.dp), color = Color.White)
                        // Like
                        Text(modifier = Modifier.fillMaxWidth().padding(12.dp, 12.dp, 105.dp, 12.dp), text = "Like", fontSize = dpToSp(dp = 12.dp), color = Color.White)
                    }
                }
                
                Image(
                    painter = rememberImagePainter(data = null),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 12.dp, 0.dp)
                        .align(Alignment.BottomEnd)
                        .width(64.dp)
                        .height(64.dp)
                        .clipToBounds()
                        .drawBehind {
                            drawCircle(
                                color = Color(0xFF71E1CF)
                            )
                        }
                        .clip(RoundedCornerShape(32.dp)))
            }

            Spacer(modifier = Modifier
                .width(18.dp))
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(5.dp))
        // Timer
        Box(modifier = Modifier.fillMaxWidth() , contentAlignment = Alignment.Center) {
            Box(modifier = Modifier.width(IntrinsicSize.Max)) {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color(0x4D56CBD5)))
                Row(modifier = Modifier.background(Color.Transparent), verticalAlignment = Alignment.Bottom) {
                    Text(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .padding(0.dp, 6.dp),
                        text = "remain",
                        fontSize = dpToSp(dp = 10.dp))
                    Text(
                        modifier = Modifier.background(Color.Transparent),
                        text = "D-04 / 18:22:30",
                        fontSize = dpToSp(dp = 25.dp))
                }
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp))

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(Color(0xFFF5F5F5)))
    }
}

@Composable
fun ReviewItem(index: Int) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(Color.LightGray)) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Item $index"
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun StickyList(sections: List<String>, viewModel: MainViewModel = viewModel()) {
    // A surface container using the 'background' color from the theme
    val listState = rememberLazyListState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(1.dp),
        state = listState
    ) {
        item {
            ReviewHeader()
        }
        stickyHeader {
            AllReviewMenuHeader()
        }
        sections.forEach { section ->
            items(5) {
                ReviewItem(it)
            }
        }
    }

    InfiniteListHandler(listState = listState) {
        viewModel.loadMoreData()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val viewModel: MainViewModel = viewModel()

    @OptIn(ExperimentalFoundationApi::class)
    ComposeProjectTheme {
        StickyList(sections = viewModel.sectionState)
    }
}