package com.kl3jvi.animity.viewmodels

import androidx.lifecycle.*
import com.google.android.exoplayer2.SimpleExoPlayer
import com.kl3jvi.animity.domain.GetEpisodeInfoUseCase
import com.kl3jvi.animity.model.database.ContentRepository
import com.kl3jvi.animity.model.entities.Content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getEpisodeInfoUseCase: GetEpisodeInfoUseCase,
    private val contentRepository: ContentRepository
) : ViewModel() {

    private var _vidUrl = MutableLiveData<String>()
    fun updateEpisodeUrl(vidUrl: String) {
        _vidUrl.value = vidUrl
    }


    @ExperimentalCoroutinesApi
    val videoUrlLiveData = Transformations.switchMap(_vidUrl) { url ->
        getEpisodeInfoUseCase.fetchEpisodeMediaUrl(url).flatMapLatest { episodeInfo ->
            getEpisodeInfoUseCase.fetchM3U8(episodeInfo.data?.vidCdnUrl)
        }.asLiveData()
    }


    fun audioProgress(exoPlayer: SimpleExoPlayer?) = flow {
        exoPlayer?.currentPosition?.let {
            while (it < 300000) {
                emit(exoPlayer.currentPosition)
                delay(1000)
            }
        }
    }.flowOn(Dispatchers.Main).asLiveData()


    fun insertEpisodesToDatabase(episode: Content) = viewModelScope.launch {
        contentRepository.insertContentToDatabase(content = episode)
    }

    fun updateTimeWatched(episode: Content) = viewModelScope.launch {
        contentRepository.updateTimeWatched(content = episode)
    }

    val isOnDatabase = Transformations.switchMap(_vidUrl) {
        getEpisodeInfoUseCase.checkIfExists(it).asLiveData()
    }
}
