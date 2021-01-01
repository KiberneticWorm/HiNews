package ru.rubt.data.remote.states

sealed class UpdateState

object UpdatedHiNewsState: UpdateState()
object FailedUpdatedHiNewsState: UpdateState()
object EmptyHiNewsState: UpdateState()

//data class UpdatedHiNewsState(
////        val updatedHiNews: List<HiNewsEntity>
//): UpdateState()
//
//data class NoUpdatedHiNewsState(
////        val noUpdatedHiNews: List<HiNewsEntity>
//): UpdateState()


