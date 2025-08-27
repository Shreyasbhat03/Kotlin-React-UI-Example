package presentation.context

import domain.CounterAction
import domain.CounterState
import react.FC
import react.PropsWithChildren
import react.useReducer

// Reducer function (how state changes with actions)
fun counterReducer(state: CounterState, action: CounterAction): CounterState =
    when (action) {
        CounterAction.Increment -> state.copy(count = state.count + 1)
        CounterAction.Decrement -> state.copy(count = state.count - 1)
        CounterAction.Reset -> CounterState()
    }

// Provider component that makes state/dispatch available globally
val CounterProvider = FC<PropsWithChildren> { props ->
    val (state, dispatch) = useReducer(::counterReducer, CounterState())

    CounterContext.Provider(state to dispatch) {
        +props.children
    }
}
