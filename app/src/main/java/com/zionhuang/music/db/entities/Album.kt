package com.zionhuang.music.db.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class Album(
    @Embedded
    val album: AlbumEntity,
    @Relation(
        entity = ArtistEntity::class,
        entityColumn = "id",
        parentColumn = "id",
        associateBy = Junction(
            value = AlbumArtistMap::class,
            parentColumn = "albumId",
            entityColumn = "artistId"
        )
    )
    val artists: List<ArtistEntity>,
) : LocalItem() {
    override val id: String
        get() = album.id
}
