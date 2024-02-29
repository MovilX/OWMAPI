package com.kryptopass.common.nav

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

const val ROUTE_LOCATIONS = "LOCATIONS"
const val ROUTE_LOCATION = "LOCATIONS/%s"
const val ARG_LOCATION_LAT_ID = "lat"
const val ARG_LOCATION_LON_ID = "lon"

sealed class NavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {

    data object Locations : NavRoutes(ROUTE_LOCATIONS)

    data object Location : NavRoutes(
        route = String.format(ROUTE_LOCATION, "{$ARG_LOCATION_LAT_ID}", "{$ARG_LOCATION_LON_ID}"),
        arguments = listOf(
            navArgument(ARG_LOCATION_LAT_ID) { type = NavType.FloatType },
            navArgument(ARG_LOCATION_LON_ID) { type = NavType.FloatType }
        )
    ) {

        fun routeForLocation(input: LocationInput) = String.format(ROUTE_LOCATION, input.lat, input.lon)

        fun fromEntry(entry: NavBackStackEntry): LocationInput {
            return LocationInput(
                entry.arguments?.getDouble(ARG_LOCATION_LAT_ID) ?: 0.0,     // TODO: current lat
                entry.arguments?.getDouble(ARG_LOCATION_LON_ID) ?: 0.0      // TODO: current lon
            )
        }
    }
}