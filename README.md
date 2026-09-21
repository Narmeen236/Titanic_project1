# Software Testing Documentation (ITCS386 Project Assignment 1)

## Team: Titanic
* Member 1: 6688131 Chayocha Thongprasert
* Member 2: 6688198 Theeratat Kunavoratham
* Member 3: 6688201 Narmeen Masoodi
* Member 4: 6688209 Poschapat Phetcharawut
* Member 5: 6688226 Thanawat Thanasirithip

---

## 1. Unit Test Suite Overview & ISP Characteristics (Requirement 6)

### 1.1 Test Suite Identification
The automated test suite `PokeApiTest` contains 10 unit test cases testing the data retrieval and parsing capabilities of the open-source client wrapper (`com.github.oscar0812.pokeapi.utils.Client`). Each test method represents a distinct test case verifying data models such as `Pokemon`, `EvolutionChain`, `Generation`, `Item`, and `Location`.

---

### 1.2 Characteristics from Input Space Partitioning (ISP)
Following the Input Space Partitioning methodology (Ammann & Offutt, Ch. 6), test requirements are derived from two complementary viewpoints:

#### A. Interface-based Characteristics
Focuses strictly on the syntax, structure, and type of the input identifiers passed into the methods:
* **$C_{int,1}$ (Input Identifier Format / Data Type):**
  * **$b_{int,1}$:** Integer Numeric ID (`int` $\ge 1$)
  * **$b_{int,2}$:** Lowercase Single-word Resource Name (`String`, e.g., `"pikachu"`, `"eevee"`)
  * **$b_{int,3}$:** Hyphenated Multi-word Slug (`String`, e.g., `"rare-candy"`, `"lavender-town"`)

#### B. Functionality-based Characteristics
Focuses on the domain semantics, target resource models, and verification complexity:
* **$C_{func,1}$ (Target Resource Entity Model):**
  * **$b_{func,1}$:** Core Pokemon Entity (`Pokemon`)
  * **$b_{func,2}$:** Evolution & In-Game Items (`EvolutionChain`, `Item`)
  * **$b_{func,3}$:** World, Region & Location Models (`Generation`, `Location`)
* **$C_{func,2}$ (Verification Depth / Assert Target):**
  * **$b_{func,4}$:** Top-level Attributes (Name, ID, Category existence)
  * **$b_{func,5}$:** Nested Collections & Complex Attributes (Move counts, Type arrays, Base stats, Stream URLs)

---

### 1.3 Detailed Description of the 10 Test Cases

1. **`testGetPokemonByValidId`**
   * **Goal:** Verify that querying Pokémon ID 1 returns Bulbasaur with a valid non-null instance and matching name.
   * **Interface-based:** $b_{int,1}$ (Integer Numeric ID)
   * **Functionality-based:** $b_{func,1}$ (Pokemon), $b_{func,4}$ (Top-level Attribute)

2. **`testGetPokemonByNameAndType`**
   * **Goal:** Verify that querying Pokémon by name `"pikachu"` correctly retrieves ID 25 and parses its nested type array to confirm `"electric"`.
   * **Interface-based:** $b_{int,2}$ (Lowercase Name)
   * **Functionality-based:** $b_{func,1}$ (Pokemon), $b_{func,5}$ (Nested Collections)

3. **`testCharmanderToCharmeleonEvolution`**
   * **Goal:** Verify that querying `EvolutionChain` ID 2 accurately resolves the progression from base species Charmander to next stage Charmeleon.
   * **Interface-based:** $b_{int,1}$ (Integer Numeric ID)
   * **Functionality-based:** $b_{func,2}$ (Evolution/Item), $b_{func,5}$ (Nested Collections)

4. **`testRareCandyCategory`**
   * **Goal:** Verify that querying item slug `"rare-candy"` maps to ID 50 and contains a valid, non-null category object.
   * **Interface-based:** $b_{int,3}$ (Hyphenated Slug)
   * **Functionality-based:** $b_{func,2}$ (Evolution/Item), $b_{func,4}$ (Top-level Attribute)

5. **`testGetCharizardMovesCount`**
   * **Goal:** Verify that retrieving Charizard (ID 6) correctly loads the moves list and that the collection size strictly equals 131.
   * **Interface-based:** $b_{int,1}$ (Integer Numeric ID)
   * **Functionality-based:** $b_{func,1}$ (Pokemon), $b_{func,5}$ (Nested Collections)

6. **`testGenerationAndGreninjaIdAssociation`**
   * **Goal:** Verify that querying Generation 6 resolves the main region to Kalos and includes Greninja (species ID 658) in its species collection URL stream.
   * **Interface-based:** $b_{int,1}$ (Integer Numeric ID)
   * **Functionality-based:** $b_{func,3}$ (World/Region/Location), $b_{func,5}$ (Nested Collections)

7. **`testSnorlaxHpStat`**
   * **Goal:** Verify that Snorlax (ID 143) correctly populates index 0 of its stats list with base stat name `"hp"` and value 160.
   * **Interface-based:** $b_{int,1}$ (Integer Numeric ID)
   * **Functionality-based:** $b_{func,1}$ (Pokemon), $b_{func,5}$ (Nested Collections)

8. **`testEeveeAttackStat`**
   * **Goal:** Verify that Eevee (ID 133) correctly populates index 1 of its stats list with base stat name `"attack"` and value 55.
   * **Interface-based:** $b_{int,1}$ (Integer Numeric ID)
   * **Functionality-based:** $b_{func,1}$ (Pokemon), $b_{func,5}$ (Nested Collections)

9. **`testMewtwoDefenseStat`**
   * **Goal:** Verify that Mewtwo (ID 150) resolves top-level name `"mewtwo"` and matches defense base stat 90 at index 2.
   * **Interface-based:** $b_{int,1}$ (Integer Numeric ID)
   * **Functionality-based:** $b_{func,1}$ (Pokemon), $b_{func,4}$ (Top-level & General Verification)

10. **`testLocationLavenderTownDetails`**
    * **Goal:** Verify that location slug `"lavender-town"` maps to region `"kanto"` and contains populated game index entries.
    * **Interface-based:** $b_{int,3}$ (Hyphenated Slug)
    * **Functionality-based:** $b_{func,3}$ (World/Region/Location), $b_{func,4}$ (Top-level Attribute & Non-empty Check)

---

## 2. Input Domain Modelling (IDM) (Requirement 7)

### 2.1 Testable Functions Identification
The testing suite exercises static gateway query methods in `com.github.oscar0812.pokeapi.utils.Client`:
* `Client.getPokemonById(int id)`
* `Client.getPokemonByName(String name)`
* `Client.getEvolutionChainById(int id)`
* `Client.getGenerationById(int id)`
* `Client.getItemByName(String name)`
* `Client.getLocationByName(String name)`

### 2.2 Parameters, Return Types, and Exceptional Behaviour
* **Parameters:**
  * `int id`: Positive integer representing the entity's database index ($id \ge 1$).
  * `String name`: Non-empty lowercase alphabetic or hyphenated slug representing resource keys.
* **Return Types:**
  * Concrete domain entity objects under `com.github.oscar0812.pokeapi.models.*` (`Pokemon`, `EvolutionChain`, `Generation`, `Item`, `Location`).
* **Return Values:**
  * Fully instantiated objects containing mapped primitive fields, nested model references, and collections.
* **Exceptional Behaviour:**
  * If a non-existent ID, negative integer, or unrecognized resource slug is passed, the underlying HTTP client returns a `404 Not Found` or throws a runtime client exception.

---

### 2.3 Partitioning the Input Domain (Partitions & Blocks)

To satisfy the **Disjointness** ($b_i \cap b_j = \emptyset$) and **Completeness** ($\bigcup b_i = D$) properties, the input domain is partitioned into 3 independent partitions:

| Partition | Description | Blocks |
| :--- | :--- | :--- |
| **$P_1$ (Identifier Format)** | Structure and type of the query parameter | **$b_1$:** Integer Numeric ID (`int`)<br>**$b_2$:** Single-word Lowercase String (`String`)<br>**$b_3$:** Multi-word Hyphenated String (`String`) |
| **$P_2$ (Target Resource Model)** | Domain category of the queried entity | **$b_4$:** Pokemon Entity Model (`Pokemon`)<br>**$b_5$:** Evolution & Item Model (`EvolutionChain`, `Item`)<br>**$b_6$:** World & Regional Model (`Generation`, `Location`) |
| **$P_3$ (Verification Scope)** | Structural depth evaluated in assertions | **$b_7$:** Primary Attributes (Name, ID, Category Name)<br>**$b_8$:** Deep Nested Structure / Array / Stream (Moves list, Stats list, Species URLs) |

---

### 2.4 Combinatorial Coverage Criteria & Test Case Derivations

The 5 combinatorial coverage criteria are distributed systematically across the 10 test cases (2 cases per approach):

| Test ID | Method Name | Criterion | Block Tuple | Concrete Inputs | Expected Values / Asserts |
| :---: | :--- | :---: | :---: | :--- | :--- |
| **TC01** | `testGetPokemonByValidId` | **ACoC** (1/2) | $(b_1, b_4, b_7)$ | `id = 1` | `name == "bulbasaur"`, `pokemon != null` |
| **TC02** | `testGetPokemonByNameAndType` | **ACoC** (2/2) | $(b_2, b_4, b_8)$ | `name = "pikachu"` | `id == 25`, `types[0].name == "electric"` |
| **TC03** | `testCharmanderToCharmeleonEvolution` | **ECC** (1/2) | $(b_1, b_5, b_8)$ | `id = 2` | `species == "charmander"`, `evolvesTo == "charmeleon"` |
| **TC04** | `testRareCandyCategory` | **ECC** (2/2) | $(b_3, b_5, b_7)$ | `name = "rare-candy"` | `id == 50`, `category.name != null` |
| **TC05** | `testGetCharizardMovesCount` | **PWC** (1/2) | $(b_1, b_4, b_8)$ | `id = 6` | `name == "charizard"`, `moves.size() == 131` |
| **TC06** | `testGenerationAndGreninjaIdAssociation` | **PWC** (2/2) | $(b_1, b_6, b_8)$ | `id = 6` | `region.name == "kalos"`, `contains ID 658` |
| **TC07** | `testSnorlaxHpStat` | **BCC** (Base 1) | **$(b_1, b_4, b_8)$** | `id = 143` | `stats[0].baseStat == 160`, `stat.name == "hp"` |
| **TC08** | `testEeveeAttackStat` | **BCC** (Var 1) | **$(b_1, b_4, b_8)$** | `id = 133` | `stats[1].baseStat == 55`, `stat.name == "attack"` |
| **TC09** | `testMewtwoDefenseStat` | **MBCC** (Base 2) | **$(b_1, b_4, b_7)$** | `id = 150` | `name == "mewtwo"`, `stats[2].baseStat == 90` |
| **TC10** | `testLocationLavenderTownDetails` | **MBCC** (Var 2) | **$(b_3, b_4, b_7)$** | `name = "lavender-town"` | `region.name == "kanto"`, `gameIndices not empty` |

---
[![Maven Central](https://img.shields.io/maven-central/v/com.github.oscar0812/pokeapi.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.oscar0812%22%20AND%20a:%22pokeapi%22)

# PokéApi V2 Java Wrapper <img heigth=50 width=50 src="https://cdn.bulbagarden.net/upload/thumb/3/36/350Milotic.png/250px-350Milotic.png">
The most robust [PokéApi](https://www.pokeapi.co/) client. Written in Java with caching 🤖 and love 😍.

## Install
Maven:
```xml
<dependency>
  <groupId>com.github.oscar0812</groupId>
  <artifactId>pokeapi</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Examples
A simple example to get information on beautiful milotic

```java
Pokemon milotic = Client.getPokemonByName("milotic");
```

Any client call can be converted to a call to the direct object, for example

```java
// using client to call methods
Pokemon milotic = Client.getPokemonByName("milotic");

// using the Pokemon class directly
Pokemon milotic = Pokemon.getByName("milotic");
```

All method calls return the referenced object, allowing the chaining of method calls

```java
Pokemon milotic = Pokemon.getByName("milotic");
System.out.println(milotic.getSpecies().getColor().setId(2).getId());
```

## Cache

To speed up calls to online resources a local sqlite database is created to cache the responses. If a local copy of the API responses is not wanted, the feature can be disabled

```java
Client.CACHE = false;
// ... etc ...
```
**Cache can be set (or unset) even when using direct objects, and not the Client class**

## API List Limitations
All list methods (getXlist) return **NamedAPIResourceList** or **APIResourceList** 
instances by design. They do not return the information of each element in 
the list, just the name and the url. To overcome this you can fetch the 
API for each object.
```java
NamedAPIResourceList list = Client.getMoveCategoryList(10, 0);
for (NamedAPIResource nar:list.getResults()) {
    // get the object information from API
    MoveCategory mc = MoveCategory.getByName(nar.getName());
    
    // getMoves() returns an ArrayList. The api does not return the complete 
    // information per object, but does set the url. The get function 
    // uses the url and fetches its information
    for(Move move: mc.getMoves()) {
        System.out.println(move.get());
    }
}
```

## Client methods
Below are all possible Client methods

```java
getAPIResourceListByEndpoint(String endpoint);
getNamedAPIResourceListByEndpoint(String endpoint);
getBerryById(int id);
getBerryByName(String name);
getBerryList(int limit, int offset);
getBerryFirmnessById(int id);
getBerryFirmnessByName(String name);
getBerryFirmnessList(int limit, int offset);
getBerryFlavorById(int id);
getBerryFlavorByName(String name);
getBerryFlavorList(int limit, int offset);
getContestTypeById(int id);
getContestTypeByName(String name);
getContestTypeList(int limit, int offset);
getContestEffectById(int id);
getContestEffectList(int limit, int offset);
getSuperContestEffectById(int id);
getSuperContestEffectList(int limit, int offset);
getEncounterMethodById(int id);
getEncounterMethodByName(String name);
getEncounterMethodList(int limit, int offset);
getEncounterConditionById(int id);
getEncounterConditionByName(String name);
getEncounterConditionList(int limit, int offset);
getEncounterConditionValueById(int id);
getEncounterConditionValueByName(String name);
getEncounterConditionValueList(int limit, int offset);
getEvolutionChainById(int id);
getEvolutionChainList(int limit, int offset);
getEvolutionTriggerById(int id);
getEvolutionTriggerByName(String name);
getEvolutionTriggerList(int limit, int offset);
getGenerationById(int id);
getGenerationByName(String name);
getGenerationList(int limit, int offset);
getPokedexById(int id);
getPokedexByName(String name);
getPokedexList(int limit, int offset);
getVersionById(int id);
getVersionByName(String name);
getVersionList(int limit, int offset);
getVersionGroupById(int id);
getVersionGroupByName(String name);
getVersionGroupList(int limit, int offset);
getItemById(int id);
getItemByName(String name);
getItemList(int limit, int offset);
getItemAttributeById(int id);
getItemAttributeByName(String name);
getItemAttributeList(int limit, int offset);
getItemCategoryById(int id);
getItemCategoryByName(String name);
getItemCategoryList(int limit, int offset);
getItemFlingEffectById(int id);
getItemFlingEffectByName(String name);
getItemFlingEffectList(int limit, int offset);
getItemPocketById(int id);
getItemPocketByName(String name);
getItemPocketList(int limit, int offset);
getLocationById(int id);
getLocationByName(String name);
getLocationList(int limit, int offset);
getLocationAreaById(int id);
getLocationAreaByName(String name);
getLocationAreaList(int limit, int offset);
getPalParkAreaById(int id);
getPalParkAreaByName(String name);
getPalParkAreaList(int limit, int offset);
getRegionById(int id);
getRegionByName(String name);
getRegionList(int limit, int offset);
getMachineById(int id);
getMachineList(int limit, int offset);
getMoveById(int id);
getMoveByName(String name);
getMoveList(int limit, int offset);
getMoveAilmentById(int id);
getMoveAilmentByName(String name);
getMoveAilmentList(int limit, int offset);
getMoveBattleStyleById(int id);
getMoveBattleStyleByName(String name);
getMoveBattleStyleList(int limit, int offset);
getMoveDamageClassById(int id);
getMoveDamageClassByName(String name);
getMoveDamageClassList(int limit, int offset);
getMoveLearnMethodById(int id);
getMoveLearnMethodByName(String name);
getMoveLearnMethodList(int limit, int offset);
getMoveTargetById(int id);
getMoveTargetByName(String name);
getMoveTargetList(int limit, int offset);
getAbilityById(int id);
getAbilityByName(String name);
getAbilityList(int limit, int offset);
getCharacteristicById(int id);
getCharacteristicList(int limit, int offset);
getEggGroupById(int id);
getEggGroupByName(String name);
getEggGroupList(int limit, int offset);
getGenderById(int id);
getGenderByName(String name);
getGenderList(int limit, int offset);
getGrowthRateById(int id);
getGrowthRateByName(String name);
getGrowthRateList(int limit, int offset);
getNatureById(int id);
getNatureByName(String name);
getNatureList(int limit, int offset);
getPokeathlonStatById(int id);
getPokeathlonStatByName(String name);
getPokeathlonStatList(int limit, int offset);
getPokemonById(int id);
getPokemonByName(String name);
getPokemonList(int limit, int offset);
getPokemonColorById(int id);
getPokemonColorByName(String name);
getPokemonColorList(int limit, int offset);
getPokemonFormById(int id);
getPokemonFormByName(String name);
getPokemonFormList(int limit, int offset);
getPokemonHabitatById(int id);
getPokemonHabitatByName(String name);
getPokemonHabitatList(int limit, int offset);
getPokemonShapeById(int id);
getPokemonShapeByName(String name);
getPokemonShapeList(int limit, int offset);
getPokemonSpeciesById(int id);
getPokemonSpeciesByName(String name);
getPokemonSpeciesList(int limit, int offset);
getStatById(int id);
getStatByName(String name);
getStatList(int limit, int offset);
getTypeById(int id);
getTypeByName(String name);
getTypeList(int limit, int offset);
getLanguageById(int id);
getLanguageByName(String name);
getLanguageList(int limit, int offset);
getMoveCategoryById(int id);
getMoveCategoryByName(String name);
getMoveCategoryList(int limit, int offset);
```

This project gets the information directly from the documentation. 
If there is a documentation update, or you encounter a bug/error please
contact me by email or open an issue.

[Documentation here](https://pokeapi.co/docsv2/).
