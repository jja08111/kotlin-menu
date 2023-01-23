package menu.core

interface Observable<T> {
    val value: T
    fun observe(observer: Observer<T>)
}