package ru.rubt.data.remote.status

enum class PageStatus(private val status: String) {

    LAST_PAGE("last_page"),
    NO_LAST_PAGE("no_last_page"),
    NO_EXISTS("no_exists");

    override fun toString(): String {
        return status
    }

}