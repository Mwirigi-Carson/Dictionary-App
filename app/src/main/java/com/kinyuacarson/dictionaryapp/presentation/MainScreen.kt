package com.kinyuacarson.dictionaryapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kinyuacarson.dictionaryapp.domain.models.Meaning
import com.kinyuacarson.dictionaryapp.domain.models.SearchResponseItem

@Composable
fun MainScreen(
    viewmodel: MainViewmodel,
    mainUIState: MainUIState
) {
    Scaffold (
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            OutlinedTextField(
                value = mainUIState.searchWord,
                onValueChange = {
                    viewmodel.onEvent(MainUIEvents.OnSearchWordChange(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 60.dp, end = 16.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp
                ),
                label = {
                    Text(
                        text = "Search a word",
                        fontSize = 15.sp,
                        modifier = Modifier.alpha(0.7f)
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                viewmodel.onEvent(MainUIEvents.OnSearchClick)
                            }
                        ,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            )
        }
    ) { innerPadding ->

        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            Column (
                modifier = Modifier
                    .height(120.dp)
                    .padding(16.dp)
            ){
                mainUIState.searchResponseItem?.let {
                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = it.word,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = it.phonetic,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal
                    )

                    Spacer(Modifier.height(8.dp))

                }
            }

            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 110.dp)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(
                        MaterialTheme.colorScheme.secondaryContainer.copy(0.7f)
                    )
            ){
                if (mainUIState.isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                } else {
                    mainUIState.searchResponseItem?.let { responseItem ->
                        SearchResponse(responseItem)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchResponse(searchResponseItem: SearchResponseItem) {
    LazyColumn (
        contentPadding = PaddingValues(vertical = 30.dp)
    ) {
        items(searchResponseItem.meanings.size) { index ->
            Meaning(
                index = index,
                meaning = searchResponseItem.meanings[index]
            )
        }
    }
}

@Composable
fun Meaning(
    index : Int,
    meaning: Meaning
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Text(
            text = "${index + 1}. ${meaning.partOfSpeech}",
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primary.copy(0.4f),
                            Color.Transparent
                        )
                    )
                )
                .padding(
                    top = 2.dp, bottom = 4.dp,
                    start = 12.dp, end = 12.dp
                )
        )

        if (meaning.definition.definition.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            ) {

                Text(
                    text = "Definition",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 19.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = meaning.definition.definition,
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )

            }
        }

        if (meaning.definition.example.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            ) {

                Text(
                    text = "Definition",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 19.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = meaning.definition.example,
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )

            }
        }

    }
}