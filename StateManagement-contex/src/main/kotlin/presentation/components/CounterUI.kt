package presentation.components

import domain.CounterAction
import presentation.context.CounterContext
import react.FC
import react.Props
import react.useRequiredContext
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.p
import web.cssom.ClassName
import web.html.ButtonType

// Simple UI with Tailwind CSS
val CounterUI = FC<Props> {
    val (state, dispatch) = useRequiredContext(CounterContext)

    div {
        className = ClassName("min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 flex items-center justify-center")

        div {
            className = ClassName("bg-white rounded-xl shadow-lg p-8 text-center max-w-md w-full mx-4")

            h1 {
                className = ClassName("text-3xl font-bold text-gray-800 mb-2")
                +"Counter App"
            }

            p {
                className = ClassName("text-gray-600 mb-8")
                +"A simple counter built with Kotlin React (Context + Reducer)"
            }

            div {
                className = ClassName("bg-gray-50 rounded-lg p-6 mb-8")

                h2 {
                    className = ClassName("text-lg font-semibold text-gray-700 mb-2")
                    +"Current Count"
                }

                p {
                    className = ClassName("text-4xl font-bold text-indigo-600")
                    +"${state.count}"
                }
            }

            div {
                className = ClassName("flex gap-4 justify-center")

                button {
                    type = ButtonType.button
                    className = ClassName("bg-red-500 hover:bg-red-600 text-white font-semibold py-3 px-6 rounded-lg transition-colors duration-200 shadow-md hover:shadow-lg")
                    onClick = { dispatch(CounterAction.Decrement) }
                    +"Decrement"
                }

                button {
                    type = ButtonType.button
                    className = ClassName("bg-green-500 hover:bg-green-600 text-white font-semibold py-3 px-6 rounded-lg transition-colors duration-200 shadow-md hover:shadow-lg")
                    onClick = { dispatch(CounterAction.Increment) }
                    +"Increment"
                }
            }

            button {
                type = ButtonType.button
                className = ClassName("mt-4 bg-gray-500 hover:bg-gray-600 text-white font-semibold py-2 px-4 rounded-lg transition-colors duration-200")
                onClick = { dispatch(CounterAction.Reset) }
                +"Reset"
            }
        }
    }
}
