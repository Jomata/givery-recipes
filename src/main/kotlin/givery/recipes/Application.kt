package givery.recipes

import com.fasterxml.jackson.databind.SerializationFeature
import givery.recipes.controller.recipesController
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args:Array<String>):Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") //application.conf
fun Application.module() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(Locations) {
    }

    routing {
        get("/") {
            call.respond("OK!")
        }
        recipesController()
    }
}