package menu.core

class MutableObservable<T>(
    initialValue: T
) : Observable<T> {

    override var value: T = initialValue
        set(newValue) {
            field = newValue
            notifyObservers(newValue)
        }

    private var observers = listOf<Observer<T>>()

    override fun observe(observer: Observer<T>) {
        observers = observers + observer
    }

    private fun notifyObservers(value: T) {
        observers.forEach {
            it.invoke(value)
        }
    }
}