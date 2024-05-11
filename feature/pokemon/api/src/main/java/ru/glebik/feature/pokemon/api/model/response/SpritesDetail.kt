package ru.glebik.feature.pokemon.api.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpritesDetail(
    @SerialName("back_default")
    val backDefault: String?,
    @SerialName("back_shiny")
    val backShiny: String?,
    @SerialName("front_default")
    val frontDefault: String?,
    @SerialName("front_shiny")
    val frontShiny: String?,
    @SerialName("other")
    val otherSprites: OtherSprites?
) {
    fun getImageList(): List<String> {
        val resultList = arrayListOf<String>()
        otherSprites?.officialArtwork?.frontDefault?.let { resultList.add(it) }
        otherSprites?.officialArtwork?.frontShiny?.let { resultList.add(it) }
        frontDefault?.let { resultList.add(it) }
        frontShiny?.let { resultList.add(it) }
        backShiny?.let { resultList.add(it) }
        backDefault?.let { resultList.add(it) }
        otherSprites?.home?.frontDefault?.let { resultList.add(it) }
        otherSprites?.home?.frontShiny?.let { resultList.add(it) }
        return resultList
    }
}

@Serializable
data class OtherSprites(
    @SerialName("home")
    val home: Home?,
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtwork?,
)

@Serializable
data class OfficialArtwork(
    @SerialName("front_default")
    val frontDefault: String?,
    @SerialName("front_shiny")
    val frontShiny: String?
)

@Serializable
data class Home(
    @SerialName("front_default")
    val frontDefault: String?,
    @SerialName("front_shiny")
    val frontShiny: String?,
)