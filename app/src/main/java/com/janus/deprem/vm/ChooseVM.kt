package com.janus.deprem.vm

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.janus.deprem.base.BaseViewModel
import com.janus.deprem.data.Location
import com.janus.deprem.remote.DataHolder
import com.janus.deprem.usecase.LocationUseCase
import com.janus.deprem.util.startNavigation
import javax.inject.Inject


class ChooseVM @Inject constructor(locationUseCase: LocationUseCase) : BaseViewModel() {
    val position = MutableLiveData<LatLng>()
    val vehicles = MutableLiveData<List<Location>>()

    init {
        vehicles.value = locationUseCase.getLocations()
    }

    fun setSelectedMarker(marker: Marker?) {
        position.postValue(marker?.position)
    }


    fun performRoute(view: View) {
        view.context!!.startNavigation(position.value!!)
    }
}
