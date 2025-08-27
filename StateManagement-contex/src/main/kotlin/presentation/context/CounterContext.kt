package presentation.context

import domain.CounterAction
import domain.CounterState
import react.createContext

// Context holds state + dispatch function
val CounterContext = createContext<Pair<CounterState, (CounterAction) -> Unit>>()
