package com.mahibul.phmarcymanagement.ui.DueLIst.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.DueListModel

class DueModelFactory(private val model : DueListModel): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DueViewModel::class.java)){
            return DueViewModel(model) as T
        }
        throw IllegalArgumentException("Unknown View Model Exception")
    }

}