package com.pr7.jetpackpaging.data.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pr7.jetpackpaging.data.model.Results
import com.pr7.jetpackpaging.data.network.RetroService


class ResultPagingSource constructor(
    val retroService: RetroService
): PagingSource<Int, Results>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val nextPage: Int = params.key ?: 1
            val response = retroService.getDataFromApi(nextPage)

            var nextPageNumber: Int? = null
            if(response?.info?.next != null) {
                val uri = Uri.parse(response?.info?.next!!)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            var prevPageNumber: Int? = null
            if(response?.info?.prev != null) {
                val uri = Uri.parse(response?.info?.prev!!)
                val prevPageQuery = uri.getQueryParameter("page")

                prevPageNumber = prevPageQuery?.toInt()
            }

            LoadResult.Page(
                data = response.results,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber
            )
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
        //return state.anchorPosition
    }


}