package com.kl3jvi.animity.model.database

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentRepository @Inject constructor(private val dao: ContentDao) {
    fun getContent(episodeUrl: String) = dao.fetchContent(episodeUrl)
}