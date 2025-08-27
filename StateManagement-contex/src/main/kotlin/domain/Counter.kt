package domain

// State of our counter
data class CounterState(val count: Int = 0)

// Actions (sealed interface → type-safe)
sealed interface CounterAction {
    object Increment : CounterAction
    object Decrement : CounterAction
    object Reset : CounterAction
}
