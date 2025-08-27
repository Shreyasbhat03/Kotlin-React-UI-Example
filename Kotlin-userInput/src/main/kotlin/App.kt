import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.p
import web.html.HTMLInputElement
import web.cssom.ClassName

val App = FC<Props> {
    var name by useState("")

    div {
        className = ClassName(
            // Full screen, flexbox center
            "flex items-center justify-center min-h-screen bg-gradient-to-br from-blue-100 to-purple-200"
        )

        // Card container
        div {
            className = ClassName(
                "bg-white p-8 rounded-2xl shadow-2xl w-96 text-center"
            )

            // Heading
            h1 {
                className = ClassName("text-2xl font-bold text-gray-800 mb-6")
                +"✨ Welcome to Kotlin + Tailwind ✨"
            }

            // Input field
            input {
                className = ClassName(
                    "border border-gray-300 rounded-lg px-4 py-2 mb-4 " +
                            "focus:outline-none focus:ring-2 focus:ring-blue-500 " +
                            "w-full shadow-sm"
                )
                value = name
                onChange = { event ->
                    name = (event.target as HTMLInputElement).value
                }
                placeholder = "Enter your name..."
            }

            // Greeting
            if (name.trim().isEmpty()) {
                p {
                    className = ClassName("text-gray-500 italic")
                    +"Type your name above to see the greeting! ✨"
                }
            } else {
                p {
                    className = ClassName("mt-4 text-xl font-semibold text-blue-600 animate-fade-in")
                    +"Hello, ${name.trim()}! 🎉"
                }
                p {
                    className = ClassName("text-lg text-green-600 animate-bounce")
                    +"Nice to meet you!"
                }
            }
        }
    }
}
