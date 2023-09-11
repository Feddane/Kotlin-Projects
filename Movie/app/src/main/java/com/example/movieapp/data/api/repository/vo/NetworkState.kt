package com.example.movieapp.data.api.repository.vo


enum class status{
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState (val status: status, val msg: String) {

    companion object{
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState

        init {
            LOADED = NetworkState(status.SUCCESS, "Success")

            LOADING = NetworkState(status.RUNNING, "Running")

            ERROR = NetworkState(status.FAILED, "Something went wrong")
        }
    }

}