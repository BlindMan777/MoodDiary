package com.mooddiary.app.screens.advices

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mooddiary.app.LocalNavController
import com.mooddiary.app.elements.TopBar
import com.mooddiary.app.model.Advice
import com.mooddiary.app.model.advicesList
import com.mooddiary.app.model.advicesRoutesList
import com.mooddiary.app.ui.theme.greateVibesFontFamily

@Composable
fun AdvicesScreen(
    viewModel: AdvicesScreenViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(advicesList) { index, item ->
                AdviceItem(
                    item = item,
                    onClick = {
                        navController.navigate(advicesRoutesList[index])
                    }
                )
            }
        }
    }
}

@Composable
fun AdviceItem(
    modifier: Modifier = Modifier,
    item: Advice,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(500.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                onClick = onClick
            )
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(item.imdID),
            contentDescription = "advice_img",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(10.dp),
                text = item.title,
                fontSize = 20.sp,
                fontFamily = greateVibesFontFamily,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun AdviceItemOverview(
    modifier: Modifier = Modifier,
    item: Advice
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(title = "Поради для тебе", withBackHandler = true)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Text(
                text = item.title,
                fontSize = 28.sp,
                fontFamily = greateVibesFontFamily,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = item.descr,
                fontSize = 20.sp,
                fontFamily = greateVibesFontFamily,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
