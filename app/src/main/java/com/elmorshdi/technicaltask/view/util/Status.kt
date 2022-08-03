package com.elmorshdi.technicaltask.view.util

sealed class Status {
    object SUCCESS : Status()
    object ERROR : Status()
    object LOADING : Status()
    object Empty :Status()
}
