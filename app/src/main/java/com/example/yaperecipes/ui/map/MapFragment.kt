package com.example.yaperecipes.ui.map

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.yaperecipes.R
import com.example.yaperecipes.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment(R.layout.fragment_map) {

    private val args: MapFragmentArgs by navArgs()

    private lateinit var mapView: MapView
    private var googleMap: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMapBinding.bind(view)

        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)

        //Set the name of the toolbar to the recipe name
        (activity as AppCompatActivity).supportActionBar?.title = args.toolbarTitle

        if (!isInternetAvailable(requireContext())) {
            binding.noInternetMessage.visibility = View.VISIBLE
            binding.mapView.visibility = View.GONE
        } else {
            binding.mapView.visibility = View.VISIBLE
            mapView.getMapAsync { map ->
                binding.noInternetMessage.visibility = View.GONE
                googleMap = map
                args.location?.let { location ->
                    val latLng = LatLng(location.latitude, location.longitude)
                    map.addMarker(MarkerOptions().position(latLng).title("Recipe Origin"))
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
                }
            }
        }


    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities != null &&
                (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }


    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onStop() {
        mapView.onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        mapView.onDestroy()
        super.onDestroyView()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}