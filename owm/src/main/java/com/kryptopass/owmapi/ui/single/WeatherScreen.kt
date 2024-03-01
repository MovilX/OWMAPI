package com.kryptopass.owmapi.ui.single

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.kryptopass.common.nav.LocationInput
import com.kryptopass.common.state.CommonScreen

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel,
    input: LocationInput,
    context: Context
) {
    viewModel.uiStateFlow.collectAsState().value.let { result ->
        CommonScreen(result) { model ->
            Weather(model, context)
        }
    }
    LaunchedEffect(input.cityAndCountryCode) {
        viewModel.submitAction(WeatherUiAction.Load(input.cityAndCountryCode))
    }
}

@Composable
fun Weather(
    model: WeatherModel,
    context: Context
) {
    var showMap by remember { mutableStateOf(false) }
    var location by remember { mutableStateOf(LatLng(model.coordinate?.lat ?: 0.0, model.coordinate?.lon ?: 0.0)) }
    var mapProperties by remember { mutableStateOf(MapProperties()) }
    var changeIcon by remember { mutableStateOf(false) }
    var lineType by remember {
        mutableStateOf<LineType?>(null)
    }

    getCurrentLocation(context) {
        location = location
        showMap = true
    }

    if (showMap) {
        WeatherMap(
            context = context,
            latLng = location,
            mapProperties = mapProperties,
            lineType = lineType,
            changeIcon = changeIcon,
            onChangeMarkerIcon = {
                changeIcon = !changeIcon
            },
            onChangeMapType = {
                mapProperties = mapProperties.copy(mapType = it)
            }, onChangeLineType = {
                lineType = it
            })
    } else {
        Text(text = "Loading Map...")
    }
}