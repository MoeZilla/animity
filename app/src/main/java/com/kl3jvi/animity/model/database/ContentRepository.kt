package com.kl3jvi.animity.model.database

import com.kl3jvi.animity.model.entities.Content
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentRepository @Inject constructor(private val dao: ContentDao) {

    fun getContent(episodeUrl: String) = dao.fetchContent(episodeUrl)

    suspend fun insertContentToDatabase(content: Content) = dao.insertContent(content)

    suspend fun updateTimeWatched(content: Content) = dao.updateTimeWatched(content)

    suspend fun episodeExists(episodeUrl: String) =
        dao.isEpisodeOnDatabase(episodeUrl)
}