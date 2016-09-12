package com.pawegio.blackcat.presenter

/**
 * @author pawegio
 */
interface BasePresenter<T> {

    var view: T?

    fun takeView(view: T) {
        this.view = view
    }

    fun dropView() {
        view = null
    }
}