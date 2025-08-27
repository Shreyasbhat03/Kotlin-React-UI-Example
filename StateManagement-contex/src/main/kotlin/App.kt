import presentation.context.CounterProvider
import presentation.components.CounterUI
import react.FC
import react.Props

val App = FC<Props> {
    CounterProvider {
        CounterUI {}
    }
}
