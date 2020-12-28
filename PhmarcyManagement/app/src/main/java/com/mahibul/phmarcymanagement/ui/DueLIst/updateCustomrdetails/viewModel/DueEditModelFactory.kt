package com.mahibul.phmarcymanagement.ui.DueLIst.updateCustomrdetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.DueListModel

class DueEditModelFactory(val model :DueListModel ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DueEditViewModel::class.java)){
            return DueEditViewModel(model) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class name")
    }
}