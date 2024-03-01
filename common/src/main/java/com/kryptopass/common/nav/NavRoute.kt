package com.kryptopass.common.nav

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

const val ROUTE_LOCATIONS = "LOCATIONS"
const val ROUTE_LOCATION = "LOCATIONS/%s"
const val ARG_LOCATION_CITY_CODE = "cityCode"

sealed class NavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {

    data object Locations : NavRoutes(ROUTE_LOCATIONS)

    data object Location : NavRoutes(
        route = String.format(ROUTE_LOCATION, "{$ARG_LOCATION_CITY_CODE}"),
        arguments = listOf(
            navArgument(ARG_LOCATION_CITY_CODE) { type = NavType.StringType }
        )
    ) {

        fun routeForLocation(input: LocationInput) =
            String.format(ROUTE_LOCATION, input.cityAndCountryCode)

        fun fromEntry(entry: NavBackStackEntry): LocationInput {
            return LocationInput(
                entry.arguments?.getString(ARG_LOCATION_CITY_CODE) ?: ""
            )
        }
    }
}