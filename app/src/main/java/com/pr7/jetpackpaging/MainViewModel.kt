package com.pr7.jetpackpaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pr7.jetpackpaging.data.model.Results
import com.pr7.jetpackpaging.data.network.RetroService
import com.pr7.jetpackpaging.data.network.Retroinstance
import com.pr7.jetpackpaging.data.paging.ResultPagingSource
import kotlinx.coroutines.flow.Flow

class MainViewModel constructor(

): ViewModel() {
    var retroService: RetroService = Retroinstance.getRetrofitInstance().create(RetroService::class.java)

    fun getListData(): Flow<PagingData<Results>> {
        return Pager (config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { ResultPagingSource(retroService) }).flow.cachedIn(viewModelScope)
    }
}