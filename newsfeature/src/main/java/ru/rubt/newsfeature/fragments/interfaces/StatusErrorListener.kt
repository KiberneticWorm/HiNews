package ru.rubt.newsfeature.fragments.interfaces

import ru.rubt.newsfeature.fragments.status.StatusError

interface StatusErrorListener {
    fun showStatus(statusError: StatusError)
}