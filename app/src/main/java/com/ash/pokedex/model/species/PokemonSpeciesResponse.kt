package com.ash.pokedex.model.species


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PokemonSpeciesResponse(
    @SerializedName("base_happiness")
    val baseHappiness: Int? = 0,
    @SerializedName("capture_rate")
    val captureRate: Int? = 0,
    @SerializedName("color")
    val color: Color? = Color(),
    @SerializedName("egg_groups")
    val eggGroups: List<EggGroup>? = listOf(),
    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChain? = EvolutionChain(),
    @SerializedName("evolves_from_species")
    val evolvesFromSpecies: Any? = Any(),
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>? = listOf(),
    @SerializedName("form_descriptions")
    val formDescriptions: List<Any>? = listOf(),
    @SerializedName("forms_switchable")
    val formsSwitchable: Boolean? = false,
    @SerializedName("gender_rate")
    val genderRate: Int? = 0,
    @SerializedName("genera")
    val genera: List<Genera>? = listOf(),
    @SerializedName("generation")
    val generation: Generation? = Generation(),
    @SerializedName("growth_rate")
    val growthRate: GrowthRate? = GrowthRate(),
    @SerializedName("habitat")
    val habitat: Habitat? = Habitat(),
    @SerializedName("has_gender_differences")
    val hasGenderDifferences: Boolean? = false,
    @SerializedName("hatch_counter")
    val hatchCounter: Int? = 0,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("is_baby")
    val isBaby: Boolean? = false,
    @SerializedName("is_legendary")
    val isLegendary: Boolean? = false,
    @SerializedName("is_mythical")
    val isMythical: Boolean? = false,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("names")
    val names: List<Name>? = listOf(),
    @SerializedName("order")
    val order: Int? = 0,
    @SerializedName("pal_park_encounters")
    val palParkEncounters: List<PalParkEncounter>? = listOf(),
    @SerializedName("pokedex_numbers")
    val pokedexNumbers: List<PokedexNumber>? = listOf(),
    @SerializedName("shape")
    val shape: Shape? = Shape(),
    @SerializedName("varieties")
    val varieties: List<Variety>? = listOf()
)