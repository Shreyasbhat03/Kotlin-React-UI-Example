import react.FC
import react.dom.html.ReactHTML.div
import web.cssom.ClassName

val App = FC {
    console.log("App component is rendering") // Debug line
    div {
        className = ClassName("bg-blue-900 text-green p-4 rounded")
        +"Hello World with Kotlin React Wrap"
    }
}
