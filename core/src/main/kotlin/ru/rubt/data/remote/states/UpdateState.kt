package ru.rubt.data.remote.states

import ru.rubt.data.db.entities.HiNewsEntity

sealed class UpdateState

data class UpdatedHiNewsState(
        val updatedHiNews: List<HiNewsEntity>
): UpdateState()

data class NoUpdatedHiNewsState(
        val noUpdatedHiNews: List<HiNewsEntity>
): UpdateState()

object EmptyHiNewsState: UpdateState()
