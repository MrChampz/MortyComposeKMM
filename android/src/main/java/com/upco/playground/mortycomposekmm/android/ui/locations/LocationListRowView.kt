package com.upco.playground.mortycomposekmm.android.ui.locations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.upco.playground.mortycomposekmm.shared.fragment.LocationDetail

@Composable
fun LocationListRowView(
    location: LocationDetail,
    locationSelected: (location: LocationDetail) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
                           .clickable { locationSelected(location) }
                           .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                location.name ?: "",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                Text(
                    "${ location.residents?.size ?: 0 } resident(s)",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
    Divider()
}