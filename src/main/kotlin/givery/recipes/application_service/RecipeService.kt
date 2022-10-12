package givery.recipes.application_service

import givery.recipes.domain.Recipe
import java.time.OffsetDateTime

object RecipeService {
    suspend fun registerRecipe() {

    }

    suspend fun listAll():List<Recipe> {
        return listOf(
            Recipe.sample(1)
        )
    }

    suspend fun searchById(id: Int):Recipe? {
        return Recipe.sample(id)
    }

    suspend fun updateRecipe() {

    }

    suspend fun deleteRecipe() {

    }
}