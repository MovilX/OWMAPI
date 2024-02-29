package com.kryptopass.owmapi.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kryptopass.common.state.CommonScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WeatherListScreen(
    viewModel: WeatherListViewModel,
    navController: NavController
) {
    // NOTE: only execute once, keep from recomposing/re-executing same block
    // can also be used to ensure that we do not trigger multiple data loads
    LaunchedEffect(Unit) {
        viewModel.submitAction(WeatherListUiAction.Load)
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                //WeatherScreenHeader()
                WeatherList(it) { item ->
                    viewModel.submitAction(
                        WeatherListUiAction.OnItemClick(
                            item.coordinate?.lat, item.coordinate?.lon
                        )
                    )
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is WeatherListUiSingleEvent.OpenWeatherScreen -> {
                    navController.navigate(it.navRoute)
                }
            }
        }
    }
}

@Composable
fun WeatherList(
    model: WeatherListModel,
    onRowClick: (WeatherItemModel) -> Unit,
) {
    Column {
        //LaunchTitleHeader()
        LazyColumn(modifier = Modifier.padding(start = 4.dp, end = 4.dp, bottom = 4.dp)) {
            itemsIndexed(model.items) { index, item ->
                Column(modifier = Modifier
                    .clickable {
                        onRowClick(item)
                    }
                ) {
                    WeatherItem(item, index)
                }
            }
        }
    }
}

@Composable
fun WeatherItem(
    item: WeatherItemModel,
    index: Int
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (index % 2 == 0) Color(0xFFF0F8FF) else Color(0xFF020035),
            contentColor = if (index % 2 == 0) Color(0xFF020035) else Color(0xFFF0F8FF)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        WeatherItemRow(item)
    }
}

@Composable
fun WeatherItemRow(item: WeatherItemModel) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //val flightDetails = launchItemHelper(item)
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(item.links?.missionPatch)
//                .crossfade(true)
//                .size(Size(120, 120))
//                .scale(Scale.FIT)
//                .precision(Precision.EXACT)
//                .build(),
//            contentDescription = null
//        )
        Column {
            Text(
                text = item.name ?: "No Name",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = item.clouds?.all.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = item.coordinate?.lat.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = item.coordinate?.lon.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}