package com.aura.test.vromanovich.presentation.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aura.test.vromanovich.domain.usecase.boots.last.get.GetLastBootTimestampUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLastBootTimestampUseCase: GetLastBootTimestampUseCase,
) : ViewModel() {

    private val simpleDateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())

    private val _lastBootTime: MutableStateFlow<String> = MutableStateFlow("")
    val lastBootTime: StateFlow<String> = _lastBootTime

    init {
        //TODO: Implement logic of calculating boots and composing UI
        //TODO: Move this from init block
        viewModelScope.launch(Dispatchers.IO) {
            val timestamp = getLastBootTimestampUseCase()
            val date = convertTimestamp(timestamp)
            if (date != null) {
                _lastBootTime.tryEmit(date)
            } else {
                //TODO: Get string from resources
                _lastBootTime.tryEmit("No boots detected")
            }
        }
    }

    private fun convertTimestamp(timestamp: Long): String? {
        if (timestamp == -1L) return null

        return try {
            simpleDateFormat.format(timestamp)
        } catch (ex: Exception) {
            //TODO: Handle error
            null
        }
    }
}