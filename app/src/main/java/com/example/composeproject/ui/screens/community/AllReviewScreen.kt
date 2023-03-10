package com.example.composeproject.ui.screens.community

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.composeproject.ui.theme.BorderLine
import com.example.composeproject.ui.theme.ComposeProjectTheme
import com.example.composeproject.ui.utils.dpToSp
import com.example.composeproject.ui.utils.noRippleClickable

@Composable
fun AllReviewMenuHeader() {
    val selectedTag = rememberSaveable { mutableStateOf("") }
    val selectedFilter = rememberSaveable { mutableStateOf("") }
    val onClickTag:((String) -> Unit) = {
        if (selectedTag.value == it) {
            selectedTag.value = ""
        } else {
            selectedTag.value = it
        }
    }
    val onClickFilter: ((String) -> Unit) = {
        if (selectedFilter.value == it) {
            selectedFilter.value = ""
        } else {
            selectedFilter.value = it
        }
    }

    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(165.dp)) {

        Column() {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp))
            // Tag Title
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(18.dp, 0.dp)) {
                Image(
                    painter = rememberImagePainter(data = null),
                    contentDescription = null,
                    modifier = Modifier
                        .width(28.dp)
                        .aspectRatio(1f / 1f)
                        .background(Color.Green))
                Text(text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color(0xFF3CA3BE),
                            fontSize = dpToSp(dp = 14.dp),
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("SpanStyle")
                    }
                    append(" ")
                    append("append")
                    append(" ")
                    append("text")
                })
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(12.dp))
            // Tag List
            Row(modifier = Modifier
                .padding(18.dp, 0.dp)
                .fillMaxWidth()
                .height(34.dp)) {
                TagToggleButton("Tag1", isSelected = "Tag1" == selectedTag.value, onClick = onClickTag)
                Spacer(modifier = Modifier.width(8.dp))
                TagToggleButton("Tag2", isSelected = "Tag2" == selectedTag.value, onClick = onClickTag)
                Spacer(modifier = Modifier.width(8.dp))
                TagToggleButton("Tag3", isSelected = "Tag3" == selectedTag.value, onClick = onClickTag)
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(14.dp))
            // Filter Menus
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(BorderLine))

            Box(modifier = Modifier.background(Color(0xFFF9FAFB))){
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(0.dp, 12.dp)
                        .fillMaxWidth()
                        .height(58.dp)) {

                    Spacer(modifier = Modifier.width(18.dp))
                    Image(painter = rememberImagePainter(data = null), contentDescription = null,
                        modifier = Modifier
                            .width(32.dp)
                            .aspectRatio(1f / 1f)
                            .clip(RoundedCornerShape(16.dp))
                            .border(1.dp, BorderLine, RoundedCornerShape(16.dp))
                            .background(Color.Gray))

                    ReviewFilterButton(title = "Test1", isSelected = "Test1" == selectedFilter.value, onClick = onClickFilter)
                    ReviewFilterButton(title = "Test2", isSelected = "Test2" == selectedFilter.value, onClick = onClickFilter)
                    ReviewFilterButton(title = "Test3", isSelected = "Test3" == selectedFilter.value, onClick = onClickFilter)
                    ReviewFilterButton(title = "Rating", isSelected = "Rating" == selectedFilter.value, onClick = onClickFilter)
                }
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(BorderLine))
        }
    }
}

@Composable
fun TagToggleButton(title: String, isSelected: Boolean = false, onClick: ((String) -> Unit)? = null) {
    val roundShape = RoundedCornerShape(32.dp)
    val bgColor = if (isSelected) Color(0xFF53B6D0) else Color.White
    val borderColor = if (isSelected) Color(0xFF53B6D0) else BorderLine
    val textColor = if (isSelected) Color.White else Color(0xFF535353)

    Box(modifier = Modifier
        .border(1.dp, borderColor, roundShape)
        .background(bgColor, roundShape)
        .noRippleClickable { onClick?.let { it(title) } } ) {

        Text(text = "#$title", fontSize = dpToSp(dp = 13.dp), color = textColor,
                modifier = Modifier
                    .padding(12.dp, 8.dp, 12.dp, 9.dp)
                    .background(Color.Transparent))
    }
}

@Composable
fun ReviewFilterButton(isSelected: Boolean = false, title: String, onClick: ((String) -> Unit)? = null) {
    val roundShape = RoundedCornerShape(6.dp)
    val bgColor = if (isSelected) Color(0xFF4D5158) else Color.White
    val borderColor = if (isSelected) Color(0xFF4D5158) else BorderLine
    val textColor = if (isSelected) Color.White else Color(0xFF535353)

    Box(modifier = Modifier
        .padding(8.dp, 0.dp, 0.dp, 0.dp)
        .border(1.dp, borderColor, roundShape)
        .background(bgColor, roundShape)
        .noRippleClickable { onClick?.let { it(title) } }) {

        Text(text = title, fontSize = dpToSp(dp = 13.dp), color = textColor,
            modifier = Modifier
                .padding(12.dp, 8.dp, 12.dp, 9.dp)
                .background(Color.Transparent)
                .align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultTagPreview() {
    (ComposeProjectTheme {
        AllReviewMenuHeader()
    })
}