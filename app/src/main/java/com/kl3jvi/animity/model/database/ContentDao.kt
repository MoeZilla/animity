package com.kl3jvi.animity.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kl3jvi.animity.model.entities.Content

import kotlinx.coroutines.flow.Flow

@Dao
interface ContentDao {

    @Insert
    suspend fun insertContent(content: Content)

    @Update
    suspend fun updateTimeWatched(content: Content)

    @Query("SELECT * FROM CONTENT_TABLE WHERE episode_url = :episodeUrl")
    fun fetchContent(episodeUrl: String): Flow<List<Content>>

    @Query("SELECT EXISTS(SELECT * FROM content_table WHERE episode_url = :episodeUrl)")
    suspend fun isEpisodeOnDatabase(episodeUrl: String): Boolean

}