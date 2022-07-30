package com.example.opgg.utils.viewmodel

open class ViewModelState

object LoadingState : ViewModelState()

open class ErrorState(val error: Throwable) : ViewModelState()