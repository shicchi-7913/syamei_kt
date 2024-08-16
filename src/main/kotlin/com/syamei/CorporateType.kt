package com.syamei

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import java.nio.file.Path
import java.nio.file.Files

@Serializable
data class CorporateType(
    val full: String,
    val kanjiShort: String?,
    val kanaShort: String,
    val special: String?,
)

@Serializable
private data class CorporateTypesWrapper(
    val corporateTypes: List<CorporateType>
)

fun loadCorporateTypes(): List<CorporateType> {
    val yamlContent = Files.readString(Path.of("src/main/resources/data/corporate_types.yml"))
    val corporateTypesWrapper = Yaml.default.decodeFromString(CorporateTypesWrapper.serializer(), yamlContent)

    return corporateTypesWrapper.corporateTypes
}
