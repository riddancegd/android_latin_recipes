package com.example.yaperecipes.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class NetworkUtilsTest {

    private lateinit var networkUtils: NetworkUtils
    private val context = Mockito.mock(Context::class.java)
    private val connectivityManager = Mockito.mock(ConnectivityManager::class.java)
    private val network = Mockito.mock(Network::class.java)
    private val networkCapabilities = Mockito.mock(NetworkCapabilities::class.java)

    @Before
    fun setup() {
        Mockito.`when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
        Mockito.`when`(connectivityManager.activeNetwork).thenReturn(network)
        Mockito.`when`(connectivityManager.getNetworkCapabilities(network)).thenReturn(networkCapabilities)

        networkUtils = NetworkUtils()
    }

    @Test
    fun `returns true when WIFI network is available`() {
        Mockito.`when`(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)).thenReturn(true)

        assertTrue(networkUtils.isInternetAvailable(context))
    }

    @Test
    fun `returns false when WIFI network is not available`() {
        Mockito.`when`(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)).thenReturn(false)

        assertFalse(networkUtils.isInternetAvailable(context))
    }

    @Test
    fun `returns true when cellular network is available`() {
        Mockito.`when`(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)).thenReturn(true)

        assertTrue(networkUtils.isInternetAvailable(context))
    }

    @Test
    fun `returns false when cellular network is not available`() {
        Mockito.`when`(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)).thenReturn(false)

        assertFalse(networkUtils.isInternetAvailable(context))
    }



}