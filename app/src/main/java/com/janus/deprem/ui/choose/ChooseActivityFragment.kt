package com.janus.deprem.ui.choose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.janus.deprem.R
import com.janus.deprem.base.BaseFragment
import com.janus.deprem.data.LocationType
import com.janus.deprem.databinding.FragmentChooseBinding
import com.janus.deprem.util.addMarkersAndMoveCamera
import com.janus.deprem.util.setDefaults
import com.janus.deprem.vm.ChooseVM


class ChooseActivityFragment : BaseFragment<ChooseVM, FragmentChooseBinding>(), OnMapReadyCallback {
    override val getLayoutId: Int = R.layout.fragment_choose
    override val viewModelClass = ChooseVM::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.setDefaults()
        val cameraUpdate = googleMap.addMarkersAndMoveCamera(context!!, viewModel.vehicles.value!!)
        googleMap.setOnMarkerClickListener {
            when (it.tag as LocationType) {
                LocationType.HELP -> viewModel.setSelectedMarker(it)
            }
            return@setOnMarkerClickListener false
        }
        googleMap.setOnMapClickListener {
            viewModel.setSelectedMarker(null)
            googleMap.animateCamera(cameraUpdate)
        }
    }
}


