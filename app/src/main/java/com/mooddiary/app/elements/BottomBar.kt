package com.mooddiary.app.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mooddiary.app.model.BottomBarItem
import com.mooddiary.app.model.bottomBarItems

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    item: BottomBarItem,
    changeState: (BottomBarItem) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 60.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        bottomBarItems.forEach { barItem ->
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(
                        color = if(barItem == item) MaterialTheme.colorScheme.primary else Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .clickable(
                        onClick = { changeState(barItem) }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(36.dp),
                    painter = painterResource(barItem.iconID),
                    contentDescription = stringResource(barItem.descr),
                    tint = if(barItem == item) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}
