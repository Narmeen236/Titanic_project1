# ITCS386 Project 1: Unit Test for Open-Source Software
**Team Name:** Titanic  
**Selected Project:** [PokéApi V2 Java Wrapper](https://github.com/oscar0812/pokeapi-v2-java)

---

## 📌 Project Overview & Selection Rationale
*(สำหรับสมาชิกคนที่ 1 และ 5 เติมรายละเอียด)*
- **เหตุผลที่เลือกโปรเจคนี้:** 
- **ผลการตรวจสอบเงื่อนไข (Stars / Java / Test Framework):** 

---

## 🧪 Unit Test Documentation (Input Space Partitioning)
*(สำหรับสมาชิกในทีมเติมข้อมูล Test Case 1 - 10)*

> **ข้อกำหนดจากอาจารย์:**
> 1. ต้องเขียน Test Suite ทั้งหมด 10 ข้อ 
> 2. ต้องใช้วิธีจัดกลุ่มให้ครบทั้ง 5 แบบ (ACoC, ECC, PWC, BCC, MBCC) โดยใช้แบบละ 2 Test Cases
> 3. แต่ละ Test Case ต้องมีทั้ง Interface-based และ Functionality-based characteristic อย่างน้อยอย่างละ 1 ตัว

### 📝 โครงสร้างการอธิบายสำหรับ Test Case (1 - 10)
*(ให้เพื่อนๆ ใช้โครงสร้างนี้ในการเติมข้อมูล Test Case แต่ละข้อ)*

```text
Test Case X: [ชื่อ Test Case]
1. Goal: [เป้าหมายการทดสอบ]
2. Characteristics:
   - Interface-based: [ระบุ]
   - Functionality-based: [ระบุ]
3. Input Domain Modelling:
   a. Testable Function: [ชื่อฟังก์ชัน]
   b. Parameters, Return Types & Exceptions: [ระบุ]
   c. Input Domain Partitions: [ระบุ]
   d. Partition Combination Method: [ระบุ ACoC / ECC / PWC / BCC / MBCC]
   e. Test Values & Expected Values: [ระบุค่าที่ใช้รันจริง]
```
   📋 รายการ Test Cases (1 - 10)
Test Case 1: [ระบุชื่อ]
(รอเติมข้อมูล)

Test Case 2: [ระบุชื่อ]
(รอเติมข้อมูล)

Test Case 3: [ระบุชื่อ]
(รอเติมข้อมูล)

Test Case 4: [ระบุชื่อ]
(รอเติมข้อมูล)

Test Case 5: [ระบุชื่อ]
(รอเติมข้อมูล)

Test Case 6: [ระบุชื่อ]
(รอเติมข้อมูล)

Test Case 7: [ระบุชื่อ]
(รอเติมข้อมูล)

Test Case 8: [ระบุชื่อ]
(รอเติมข้อมูล)

Test Case 9: [ระบุชื่อ]
(รอเติมข้อมูล)

Test Case 10: [ระบุชื่อ]
(รอเติมข้อมูล)

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
