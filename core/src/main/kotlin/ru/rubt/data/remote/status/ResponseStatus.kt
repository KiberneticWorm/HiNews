package ru.rubt.data.remote.status

enum class ResponseStatus(private val status: String) {

    SUCCESS("success"), ERROR("error");

    override fun toString(): String {
        return status
    }
}