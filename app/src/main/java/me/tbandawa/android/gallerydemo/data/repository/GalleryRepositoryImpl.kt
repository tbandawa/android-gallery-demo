package me.tbandawa.android.gallerydemo.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.tbandawa.android.gallerydemo.data.api.GalleryApi
import me.tbandawa.android.gallerydemo.data.model.NetworkResult
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val galleryApi: GalleryApi
): ApiCallHelper(), GalleryRepository {

    override suspend fun fetchGalleries() = flow {
        emit(NetworkResult.Loading)
        emit(safeApiCall {
            galleryApi.fetchGalleries()
        })
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchGallery(id: Long) = flow {
        emit(NetworkResult.Loading)
        emit(safeApiCall {
            galleryApi.fetchGallery(id)
        })
    }.flowOn(Dispatchers.IO)

}