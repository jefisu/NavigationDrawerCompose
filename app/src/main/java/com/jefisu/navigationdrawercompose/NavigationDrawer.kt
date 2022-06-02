package com.jefisu.navigationdrawercompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.DefaultTintColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Header", fontSize = 28.sp)
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    selected: Int,
    onSelectClick: (Int) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        itemsIndexed(items) { index, item ->
            DrawerItem(
                item = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onSelectClick(index) }
                    .background(if (index == selected) Color.LightGray else DefaultTintColor)
                    .padding(vertical = 12.dp, horizontal = 8.dp)

            )
        }
    }
}

@Composable
fun DrawerItem(
    item: MenuItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon, contentDescription = null
        )
        Text(text = item.title)
    }
}