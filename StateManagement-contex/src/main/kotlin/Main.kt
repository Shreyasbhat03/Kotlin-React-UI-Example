import react.create
import react.dom.client.createRoot
import kotlinx.browser.document
import web.dom.Element

fun main() {
    console.log("Main function is running") // Debug line
    val container = document.getElementById("root") as Element?
    console.log("Container found:", container) // Debug line

    if (container != null) {
        val root = createRoot(container)
        console.log("Root created, rendering App") // Debug line
        root.render(App.create())
    } else {
        console.error("Root element not found!")
    }
}
