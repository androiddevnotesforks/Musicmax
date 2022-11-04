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

package com.maximillianleonov.musicmax.sync.work.status

import android.content.Context
import androidx.lifecycle.Transformations
import androidx.lifecycle.asFlow
import androidx.work.WorkInfo
import androidx.work.WorkInfo.State
import androidx.work.WorkManager
import com.maximillianleonov.musicmax.core.domain.sync.SyncStatusMonitor
import com.maximillianleonov.musicmax.sync.work.initializers.SyncWorkName
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import javax.inject.Inject

/**
 * [SyncStatusMonitor] backed by [WorkInfo] from [WorkManager].
 */
class WorkManagerSyncStatusMonitor @Inject constructor(
    @ApplicationContext context: Context
) : SyncStatusMonitor {
    override val isSyncing: Flow<Boolean> = Transformations.map(
        WorkManager.getInstance(context).getWorkInfosForUniqueWorkLiveData(SyncWorkName),
        MutableList<WorkInfo>::anyRunning
    ).asFlow().conflate()
}

private val List<WorkInfo>.anyRunning get() = any { it.state == State.RUNNING }
