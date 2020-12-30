package ru.rubt.newsfeature.fragments.status

sealed class StatusError(val message: Int)
data class EmptyHiNewsStatus(val msg: Int): StatusError(msg)
data class NetworkErrorStatus(val msg: Int): StatusError(msg)