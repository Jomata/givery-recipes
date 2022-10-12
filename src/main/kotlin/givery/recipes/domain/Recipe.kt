package givery.recipes.domain

import java.time.OffsetDateTime

data class Recipe(
    val id:Int,
    val title: String,
    val makingTime : String,
    val serves: String,
    val ingredients: String,
    val cost: String,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
) {
    companion object {
        fun sample(id: Int) = Recipe(
            id,
            "Test Title",
            "Test Time",
            "Test Serves",
            "Test Ingredients",
            "Test Cost",
            OffsetDateTime.now(),
            OffsetDateTime.now(),
        )
    }
}
