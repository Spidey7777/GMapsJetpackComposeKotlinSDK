package com.example.composeinterop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.example.composeinterop.ui.theme.ComposeInteropTheme
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeInteropTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MapScreen()
                }
            }
        }
    }
}


@Composable
fun MapScreen() {
    val mapView = rememberMapViewWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {
        AndroidView({mapView}) {mapView ->
            CoroutineScope(Dispatchers.Main).launch {
                val map = mapView.awaitMap()
                map.uiSettings.isZoomControlsEnabled = true

                val pickUp = LatLng(-35.016, 143.321)
                val destination = LatLng(-32.491, 147.309)

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 6f))
                val markerOptions = MarkerOptions()
                    .title("Sydney Opera House")
                    .position(pickUp)
                map.addMarker(markerOptions)

                val markerOptionsDestination = MarkerOptions()
                    .title("Restaurant Hubert")
                    .position(destination)
                map.addMarker(markerOptionsDestination)
            }
        }
    }
}




//@Composable
//fun MapScreen() {
//    val mapView = rememberMapViewWithLifecycle()
//    val context = LocalContext.current
//    AndroidView({mapView}) { mapView ->
//        CoroutineScope(Dispatchers.Main).launch {
//            val map =
//        }
//    }
//}

//@Composable
//fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
//    remember {
//        LifecycleEventObserver{_,event ->
//            while(event){
//                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
//                Lifecycle.Event.ON_START -> mapView
//            }
//        }
//    }

//@Composable
//fun MapScreen(
//    map: MapView,
//    location: LatLng
//) {
//    val coroutineScope = rememberCoroutineScope()
//
//    AndroidView({map}) { mapView ->
//        coroutineScope.launch {
//            val googleMap = mapView.awaitMap()
//            val zoom = calculateZoom(cameraPosition {  })
//        }
//    }
//}

//@Composable
//private fun MapViewContainer(
//    map: MapView,
//    location: LatLng
//) {
//    val coroutineScope = rememberCoroutineScope()
//
//    AndroidView({ map }) { mapView ->
//        coroutineScope.launch {
////            val googleMap = mapView.awaitMap()
////            val zoom = calculateZoom(cameraPosition)
////            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoom))
////            googleMap.addMarker { position(cameraPosition) }
//            mMap = googleMap
//
//            // Add a marker in Sydney and move the camera
//            val sydney = com.google.android.gms.maps.model.LatLng(-34.0, 151.0)
//            mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//            mMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLng(sydney))
//        }
//    }
//}

//@Composable
//fun MapScreen() : View{
//    class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
//
//        private lateinit var mMap: GoogleMap
//        private lateinit var binding: ActivityMapsBinding
//
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//
//            binding = ActivityMapsBinding.inflate(layoutInflater)
//            setContentView(binding.root)
//
//            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//            val mapFragment = supportFragmentManager
//                .findFragmentById(R.id.map) as SupportMapFragment
//            mapFragment.getMapAsync(this)
//        }
//
//        override fun onMapReady(googleMap: GoogleMap) {
//            mMap = googleMap
//
//            // Add a marker in Sydney and move the camera
//            val sydney = com.google.android.gms.maps.model.LatLng(-34.0, 151.0)
//            mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//            mMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLng(sydney))
//        }
//    }
//}

//@Composable
//fun GoogleMapSnapshot(location: LatLng) {
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp)
//    ) {
//        val mapView = rememberMapViewWithLifecycle()
//
//        MapViewContainer(
//            map = mapView,
//            location = location
//        )
//    }
//}

//@Composable
//private fun MapViewContainer(
//    map: MapView,
//    location: LatLng
//) {
//    val coroutineScope = rememberCoroutineScope()
//
//    AndroidView({ map }) { mapView ->
//        coroutineScope.launch {
//            val googleMap = mapView.awaitMap()
//            val zoom = calculateZoom(cameraPosition)
//            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoom))
//            googleMap.addMarker { position(cameraPosition) }
//        }
//    }
//}