package com.kl3jvi.animity.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kl3jvi.animity.model.entities.Content

import kotlinx.coroutines.flow.Flow

@Dao
interface ContentDao {

    @Insert
    suspend fun insertContent(content: Content)

    @Query("SELECT * FROM CONTENT_TABLE WHERE episode_url = :episodeUrl")
    fun fetchContent(episodeUrl: String): Flow<List<Content>>
}