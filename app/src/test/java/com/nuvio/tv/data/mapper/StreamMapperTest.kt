package com.nuvio.tv.data.mapper

import com.nuvio.tv.data.remote.dto.StreamDto
import org.junit.Assert.assertEquals
import org.junit.Test

class StreamMapperTest {
    @Test
    fun `stream title is forwarded as filename when addon omits behavior hint`() {
        val stream = StreamDto(
            title = "Movie.2024.1080p.WEB-DL.mkv",
            url = "https://example.test/movie"
        ).toDomain("Addon", null)

        assertEquals("Movie.2024.1080p.WEB-DL.mkv", stream.behaviorHints?.filename)
    }

    @Test
    fun `explicit behavior hint filename takes priority over title`() {
        val stream = StreamDto(
            title = "Display title",
            behaviorHints = com.nuvio.tv.data.remote.dto.BehaviorHintsDto(
                filename = "Release.Name.mkv"
            )
        ).toDomain("Addon", null)

        assertEquals("Release.Name.mkv", stream.behaviorHints?.filename)
    }
}
