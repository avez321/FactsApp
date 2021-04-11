package com.example.factsapp.ui.facts

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.factsapp.Constants
import com.example.factsapp.R
import com.example.factsapp.database.FactsRepository
import com.example.factsapp.model.*
import com.example.factsapp.repository.RepositoryImp
import kotlinx.coroutines.*

import com.example.factsapp.network.ResultWrapper
import com.example.factsapp.shared_preference.SharedPreferenceRepository
import com.example.factsapp.util.isInternetAvailable
import javax.inject.Inject

class FactsViewModel @Inject constructor(private val factsRepository: FactsRepository,
                                         private val sharedPreferenceRepository: SharedPreferenceRepository,
                                         private val repositoryImp: RepositoryImp, application: Application) : AndroidViewModel(application) {


    private val factsDataObservableField: ObservableField<List<Row>?> =
            ObservableField()

    private val progressVisibilityObservableField: ObservableField<Int> = ObservableField()

    private val titleMutableLiveData: MutableLiveData<String> = MutableLiveData()


    private val swipeRefreshMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getFactsData(false)
    }

    fun getFactsData(isSwipeRefresh: Boolean) {
        if (!isSwipeRefresh) progressVisibilityObservableField.set(View.VISIBLE)
        val context = getApplication<Application>().applicationContext
        if (context.isInternetAvailable()) {
            viewModelScope.launch {
                val resultWarraper = repositoryImp.getFactsFeeds()
                withContext(Dispatchers.Main) {
                    when (resultWarraper) {
                        is ResultWrapper.NetworkError -> showNetworkError()
                        is ResultWrapper.GenericError -> showGenericError(resultWarraper)
                        is ResultWrapper.Success ->
                            showSuccess(resultWarraper.value, isSwipeRefresh)
                    }
                }
            }
        } else {
            GlobalScope.launch(Dispatchers.IO) {
                val facts = factsRepository.getFactsData()
                val title = sharedPreferenceRepository.getString(Constants.FACT_TITLE)
                GlobalScope.launch(Dispatchers.Main) {
                    if (!isSwipeRefresh) progressVisibilityObservableField.set(View.GONE)
                    titleMutableLiveData.value = title
                    factsDataObservableField.set(facts)
                }
            }
        }
    }

    fun getFactsDataObservableField() = factsDataObservableField

    fun getProgressVisibilityObservableField() = progressVisibilityObservableField

    fun getTitleMutableLiveData() = titleMutableLiveData

    fun getSwipeRefreshMutableLiveData() = swipeRefreshMutableLiveData

    private fun showSuccess(fact: Fact, isSwipeRefresh: Boolean) {
        if (!isSwipeRefresh) {
            progressVisibilityObservableField.set(View.GONE)
        } else {
            swipeRefreshMutableLiveData.value = false
        }

        factsDataObservableField.set(fact.rows)

        GlobalScope.launch(Dispatchers.IO) {
            sharedPreferenceRepository.saveString(Constants.FACT_TITLE, fact.title!!)
            fact.rows?.let { factsRepository.insertAllFacts(it) }
        }

        fact.title?.let {
            titleMutableLiveData.value = it
        }
    }

    private fun showGenericError(redditResponse: Any) {
        Toast.makeText(getApplication(), redditResponse.toString(), Toast.LENGTH_SHORT).show()

    }

    private fun showNetworkError() {
        Toast.makeText(getApplication(), "network error", Toast.LENGTH_SHORT).show()
    }

    fun onFactsRefresh(isRefreshing: Boolean) {
        if (!getApplicationContext().isInternetAvailable()) {
            Toast.makeText(getApplicationContext(), R.string.internet_not_avaiable_to_pulldata, Toast.LENGTH_SHORT).show()
            swipeRefreshMutableLiveData.value = false
            return
        }

        if (isRefreshing) {
            getFactsData(true)
        }

    }

    fun getApplicationContext(): Context {
        return getApplication<Application>().applicationContext
    }
}

