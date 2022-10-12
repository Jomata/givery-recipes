@file:OptIn(KtorExperimentalLocationsAPI::class)
package givery.recipes.controller

import givery.recipes.application_service.RecipeService
import givery.recipes.domain.Recipe
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

@Location("/recipes/{id}") class RecipeLocation(val id:Int)
@Location("/recipes") class RecipesLocation

fun Route.recipesController() {
    //Create a recipe
    post<RecipesLocation> {
        val requestBody = call.receive<RecipePostBody>()
    }

    //List all recipes
    get<RecipesLocation> {
        val recipes = RecipeService.listAll()
        call.respond(
            GetAllRecipesResponse(
                recipes = recipes.map{BasicRecipeData.of(it) }
            )
        )
    }

    //Return a single recipe by ID
    get<RecipeLocation> { location ->
        val recipe = RecipeService.searchById(location.id)
        if(recipe == null) {
            call.respond(HttpStatusCode.NotFound)
        } else {
            call.respond(SingleRecipeResponse(
                message = "Recipe details by id",
                recipe = BasicRecipeData.of(recipe),
            ))
        }
    }

    //Update a recipe
    patch<RecipeLocation> { location ->
        val recipeId = location.id
        val requestBody = call.receive<RecipePatchBody>()
    }

    //Delete a recipe
    delete<RecipeLocation> {location ->
        call.respond("Delete recipe ${location.id}")
    }
}

data class BasicRecipeData(
    val id: Int,
    val title:String,
    val making_time: String,
    val serves: String,
    val ingredients: String,
    val cost: String,
) {
    companion object {
        fun of(recipe:Recipe) = BasicRecipeData(
            recipe.id,
            recipe.title,
            recipe.makingTime,
            recipe.serves,
            recipe.ingredients,
            recipe.cost,
        )
    }
}

data class RecipePostBody(
    val title:String,
    val making_time: String,
    val serves: String,
    val ingredients: String,
    val cost: String,
)

data class RecipePatchBody(
    val title:String,
    val making_time: String,
    val serves: String,
    val ingredients: String,
    val cost: String,
)

data class MessageResponse(
    val message: String,
)

data class SingleRecipeResponse(
    val message: String,
    val recipe: BasicRecipeData,
)

data class GetAllRecipesResponse(
    val recipes: List<BasicRecipeData>
)

data class PostErrorResponse(
    val message: String,
    val required: String,
)