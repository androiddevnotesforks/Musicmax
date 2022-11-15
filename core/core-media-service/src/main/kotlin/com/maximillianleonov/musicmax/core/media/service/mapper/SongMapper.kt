/*
 * Copyright 2022 Maximillian Leonov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.maximillianleonov.musicmax.core.media.service.mapper

import androidx.core.net.toUri
import com.maximillianleonov.musicmax.core.domain.model.SongModel
import com.maximillianleonov.musicmax.core.media.service.util.buildPlayableMediaItem
import com.maximillianleonov.musicmax.core.model.Song

internal fun SongModel.asMediaItem() = buildPlayableMediaItem(
    mediaId = mediaId,
    mediaUri = mediaUri.toUri(),
    artworkUri = artworkUri.toUri(),
    title = title,
    artist = artist
)

internal fun Song.asMediaItem() = buildPlayableMediaItem(
    mediaId = mediaId,
    mediaUri = mediaUri,
    artworkUri = artworkUri,
    title = title,
    artist = artist
)