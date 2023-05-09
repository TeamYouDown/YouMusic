package com.zionhuang.innertube.models.response

import com.zionhuang.innertube.models.*
import kotlinx.serialization.Serializable

@Serializable
data class BrowseResponse(
    val contents: Contents?,
    val continuationContents: ContinuationContents?,
    val header: Header?,
    val microformat: Microformat?,
    val responseContext: ResponseContext,
) {
    fun toBrowseResult(): BrowseResult = when {
        continuationContents != null -> when {
            continuationContents.sectionListContinuation != null -> BrowseResult(
                items = continuationContents.sectionListContinuation.contents.flatMap { it.toBaseItems() },
                urlCanonical = microformat?.microformatDataRenderer?.urlCanonical,
                continuations = continuationContents.sectionListContinuation.continuations?.getContinuations()
            )
            continuationContents.musicPlaylistShelfContinuation != null -> BrowseResult(
                items = continuationContents.musicPlaylistShelfContinuation.contents.mapNotNull { it.toItem() },
                urlCanonical = microformat?.microformatDataRenderer?.urlCanonical,
                continuations = continuationContents.musicPlaylistShelfContinuation.continuations?.getContinuations()
            )
            else -> throw UnsupportedOperationException("Unknown continuation type")
        }
        contents != null -> BrowseResult(
            items = contents.singleColumnBrowseResultsRenderer?.tabs?.firstOrNull()?.tabRenderer?.content?.sectionListRenderer?.contents?.flatMap { it.toBaseItems() }.orEmpty(),
            lyrics = contents.sectionListRenderer?.contents?.firstOrNull()?.musicDescriptionShelfRenderer?.description?.runs?.firstOrNull()?.text,
            urlCanonical = microformat?.microformatDataRenderer?.urlCanonical,
            continuations = contents.singleColumnBrowseResultsRenderer?.tabs?.firstOrNull()?.tabRenderer?.content?.sectionListRenderer?.contents?.firstOrNull()?.musicPlaylistShelfRenderer?.continuations?.getContinuations().orEmpty()
                    + contents.singleColumnBrowseResultsRenderer?.tabs?.firstOrNull()?.tabRenderer?.content?.sectionListRenderer?.continuations?.getContinuations().orEmpty()
        )
        else -> BrowseResult(
            items = emptyList(),
            urlCanonical = null,
            continuations = null
        )
    }.addHeader(header?.toHeader())

    @Serializable
    data class Contents(
        val singleColumnBrowseResultsRenderer: Tabs?,
        val sectionListRenderer: SectionListRenderer?,
    )

    @Serializable
    data class ContinuationContents(
        val sectionListContinuation: SectionListContinuation?,
        val musicPlaylistShelfContinuation: MusicPlaylistShelfContinuation?,
    ) {
        @Serializable
        data class SectionListContinuation(
            val contents: List<SectionListRenderer.Content>,
            val continuations: List<Continuation>?,
        )

        @Serializable
        data class MusicPlaylistShelfContinuation(
            val contents: List<MusicShelfRenderer.Content>,
            val continuations: List<Continuation>?,
        )
    }

    @Serializable
    data class Header(
        val musicImmersiveHeaderRenderer: MusicImmersiveHeaderRenderer?,
        val musicDetailHeaderRenderer: MusicDetailHeaderRenderer?,
    ) {
        fun toHeader(): YTBaseItem? = when {
            musicImmersiveHeaderRenderer != null -> ArtistHeader(
                id = musicImmersiveHeaderRenderer.title.toString(),
                name = musicImmersiveHeaderRenderer.title.toString(),
                description = musicImmersiveHeaderRenderer.description?.toString(),
                bannerThumbnails = musicImmersiveHeaderRenderer.thumbnail?.getThumbnails(),
                shuffleEndpoint = musicImmersiveHeaderRenderer.playButton?.buttonRenderer?.navigationEndpoint,
                radioEndpoint = musicImmersiveHeaderRenderer.startRadioButton?.buttonRenderer?.navigationEndpoint,
            )
            musicDetailHeaderRenderer != null -> {
                val subtitle = musicDetailHeaderRenderer.subtitle.runs.splitBySeparator()
                val menu = musicDetailHeaderRenderer.menu.toItemMenu()
                AlbumOrPlaylistHeader(
                    id = menu.playNextEndpoint?.queueAddEndpoint?.queueTarget?.playlistId
                        ?: menu.addToQueueEndpoint?.queueAddEndpoint?.queueTarget?.playlistId!!,
                    name = musicDetailHeaderRenderer.title.toString().replace("YouTube", "YouDown"),
                    subtitle = musicDetailHeaderRenderer.subtitle.runs.drop(2).asString().replace("YouTube Music", "YouDown"),
                    secondSubtitle = musicDetailHeaderRenderer.secondSubtitle.toString().replace("YouTube", "YouDown"),
                    description = musicDetailHeaderRenderer.description?.toString()?.replace("YouTube", "YouDown"),
                    artists = subtitle.getOrNull(1)?.oddElements(),
                    year = subtitle.getOrNull(2)?.firstOrNull()?.text?.toIntOrNull(),
                    thumbnails = musicDetailHeaderRenderer.thumbnail.getThumbnails(),
                    menu = musicDetailHeaderRenderer.menu.toItemMenu()
                )
            }
            else -> null
        }

        @Serializable
        data class MusicImmersiveHeaderRenderer(
            val title: Runs,
            val description: Runs?,
            val thumbnail: ThumbnailRenderer?,
            val playButton: Button?,
            val startRadioButton: Button?,
            val menu: Menu,
        )

        @Serializable
        data class MusicDetailHeaderRenderer(
            val title: Runs,
            val subtitle: Runs,
            val secondSubtitle: Runs,
            val description: Runs?,
            val thumbnail: ThumbnailRenderer,
            val menu: Menu,
        )
    }

    @Serializable
    data class Microformat(
        val microformatDataRenderer: MicroformatDataRenderer?,
    ) {
        @Serializable
        data class MicroformatDataRenderer(
            val urlCanonical: String?,
        )
    }
}
