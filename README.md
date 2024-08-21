# Syamei Kotlin Library
The Syamei Kotlin library is designed to handle company names with Japanese corporate entity types. It provides functionalities to check if a company name contains a corporate entity and to remove the corporate entity from the company name.

# Features
Check if a company name contains a corporate entity type
Remove the corporate entity type from a company name

# Usage

```kotlin
// Check if a company name contains a corporate entity type
Syamei.containsCorporateType("株式会社hoge") // true
Syamei.containsCorporateType("（株）hoge") // true
Syamei.containsCorporateType("hoge") // false

// Remove the corporate entity type from a company name
Syamei.removeCorporateType("株式会社hoge") // hoge
Syamei.removeCorporateType("（株）hoge") // hoge
Syamei.removeCorporateType("hoge") // hoge
```

# Configuration
1. Load Corporate Types: Corporate entity types are defined in a YAML file.

corporate_types.yaml

```yaml
corporate_types:
- full: 株式会社
  kanji_short: （株）
  kana_short: カ
  special: "㈱"
- full: 一般財団法人
  kanji_short: （一財）
  kana_short: シ[ヤャ]
  special:
```

2. Implement Methods:
- containsCorporateType(companyName: String): Boolean: Checks if the given company name contains any corporate entity type.
- removeCorporateType(companyName: String): String: Removes the corporate entity type from the given company name.

# Implementation Details
- Handling Corporate Entity Types: The library supports multiple types of corporate entities, including full names, shortened kanji, kana, and special symbols.
- Handling Kana Short Types: The library checks if kana short types are enclosed in parentheses and handles cases where these might appear at the start or end of the string.
