package com.dankanq.aston_intensiv_1

sealed class Action {
    data class ShowMessage(val message: String): Action()
}
