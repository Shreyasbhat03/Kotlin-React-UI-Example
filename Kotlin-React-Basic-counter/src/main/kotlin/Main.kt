import react.create
import react.dom.client.createRoot
import kotlinx.browser.document
import web.dom.Element
fun main(){
    val container = document.getElementById("root") as Element
    val root = createRoot(container)
    root.render(App.create())
}