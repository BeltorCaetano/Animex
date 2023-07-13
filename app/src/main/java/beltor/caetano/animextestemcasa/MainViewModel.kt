package com.example.animex

import android.app.Application
import androidx.compose.foundation.gestures.DraggableState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.util.Log

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _tabIndex: MutableLiveData<Int> = MutableLiveData(0)
    //Log.d("Tag",_tabIndex);
    val tabIndex: LiveData<Int> = _tabIndex
    val tabs = listOf("Home", "About", "Settings" , "MoreScreen" , "SomethingScreen", "EverythingScreen" )

    var isSwipeToTheLeft: Boolean = false
    private val draggableState = DraggableState { delta ->
        isSwipeToTheLeft= delta > 0
    }

    private val _dragState = MutableLiveData<DraggableState>(draggableState)
    val dragState: LiveData<DraggableState> = _dragState

    fun updateTabIndexBasedOnSwipe() {
        _tabIndex.value = when (isSwipeToTheLeft) {
            true -> Math.floorMod(_tabIndex.value!!.plus(1), tabs.size)
            false -> Math.floorMod(_tabIndex.value!!.minus(1), tabs.size)
        }
    }

    fun updateTabIndex(i: Int) {
        _tabIndex.value = i
    }

}