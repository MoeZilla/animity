package com.kl3jvi.animity.model.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "content_table")
data class Content(
    @ColumnInfo var url: String? = "",
    @ColumnInfo var animeName: String = "",
    @ColumnInfo var episodeName: String? = "",
    @PrimaryKey @ColumnInfo(name = "episode_url") var episodeUrl: String = "",
    @ColumnInfo var nextEpisodeUrl: String? = null,
    @ColumnInfo var previousEpisodeUrl: String? = null,
    @ColumnInfo var watchedDuration: Long = 0,
    @ColumnInfo var duration: Long = 0,
    @ColumnInfo var insertionTime: Long = 0
) : Parcelable

