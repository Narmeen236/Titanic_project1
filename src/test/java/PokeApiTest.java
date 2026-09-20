/* Copyright (C) Year 2026
Member 1 : 6688131 CHAYOCHA THONGPRASERT
Member 2 : 6688198 THEERATAT KUNAVORATHAM
Member 3 : 6688201 NARMEEN MASOODI
Member 4 : 6688209 POSCHAPAT PHETCHARAWUT
Member 5 : 6688226 THANAWAT THANASIRITHIP
- All Rights Reserved
* You may use, distribute and modify this code under the terms of
the MIT license.
*/

import com.github.oscar0812.pokeapi.models.evolution.EvolutionChain;
import com.github.oscar0812.pokeapi.models.games.Generation;
import com.github.oscar0812.pokeapi.models.items.Item;
import com.github.oscar0812.pokeapi.models.locations.Location;
import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import com.github.oscar0812.pokeapi.utils.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PokeApiTest {
    // Setup method executed before each test case (Prefix action)
    @Before
    public void setUp() {
        System.out.println("[INFO] Starting execution of a Pokémon API test case...");
    }

    // Teardown method executed after each test case (Postfix action)
    @After
    public void tearDown() {
        System.out.println("[INFO] Test case execution completed.");
    }
    // Test Suite 1: Test fetching pokemon by a valid ID (Happy Path)
    @Test
    public void testGetPokemonByValidId() {
        Pokemon pokemon = Client.getPokemonById(1); // Bulbasaur
        assertNotNull("Pokemon object should not be null", pokemon);
        assertEquals("Pokemon name should be bulbasaur", "bulbasaur", pokemon.getName());
    }

    // Test Suite 2: Test fetching pokemon by name and verifying its ID and Electric type
    @Test
    public void testGetPokemonByNameAndType() {
        Pokemon pokemon = Client.getPokemonByName("pikachu");
        assertNotNull("Pokemon object should not be null", pokemon);
        assertEquals("Pokemon name should be pikachu", "pikachu", pokemon.getName());
        assertEquals("Pokemon ID should be 25", 25, pokemon.getId());

        // Verify that Pikachu's type is electric
        assertNotNull("Pokemon types should not be null", pokemon.getTypes());
        assertFalse("Pokemon types list should not be empty", pokemon.getTypes().isEmpty());
        assertEquals("Pikachu primary type should be electric", "electric", pokemon.getTypes().get(0).getType().getName());
    }

    // Test Suite 3: Test fetching Charizard and verifying its moves count
    @Test
    public void testGetCharizardMovesCount() {
        Pokemon charizard = Client.getPokemonById(6); // Charizard ID is 6
        assertNotNull("Charizard object should not be null", charizard);
        assertEquals("Charizard name should be charizard", "charizard", charizard.getName());

        // Verify that Charizard has moves data and matches expected count from API (131 items)
        assertNotNull("Charizard moves list should not be null", charizard.getMoves());
        assertFalse("Charizard moves list should not be empty", charizard.getMoves().isEmpty());
        assertEquals("Charizard should have exactly 131 moves", 131, charizard.getMoves().size());
    }

    // Test Suite 4: Test verifying Snorlax base HP stat equals 160
    @Test
    public void testSnorlaxHpStat() {
        Pokemon snorlax = Client.getPokemonById(143);
        assertNotNull("Snorlax object should not be null", snorlax);
        // Index 0 in stats array is hp
        assertEquals("Snorlax HP base stat should be 160", 160, snorlax.getStats().get(0).getBaseStat());
        assertEquals("Stat name should be hp", "hp", snorlax.getStats().get(0).getStat().getName());
    }

    // Test Suite 5: Test verifying Mewtwo (Legendary) base Defense stat equals 90
    @Test
    public void testMewtwoDefenseStat() {
        Pokemon mewtwo = Client.getPokemonById(150); // Mewtwo ID is 150
        assertNotNull("Mewtwo object should not be null", mewtwo);
        assertEquals("Mewtwo name should be mewtwo", "mewtwo", mewtwo.getName());
        assertEquals("Mewtwo Defense base stat should be 90", 90, mewtwo.getStats().get(2).getBaseStat());
        assertEquals("Stat name should be defense", "defense", mewtwo.getStats().get(2).getStat().getName());
    }

    // Test Suite 6: Test verifying Eevee base Attack stat equals 55
    @Test
    public void testEeveeAttackStat() {
        Pokemon eevee = Client.getPokemonById(133); // Eevee ID is 133
        assertNotNull("Eevee object should not be null", eevee);
        assertEquals("Eevee name should be eevee", "eevee", eevee.getName());
        assertEquals("Eevee Attack base stat should be 55", 55, eevee.getStats().get(1).getBaseStat());
        assertEquals("Stat name should be attack", "attack", eevee.getStats().get(1).getStat().getName());
    }

    // Test Suite 7: Test evolution chain ID 2 and verify transition from Charmander to Charmeleon
    @Test
    public void testCharmanderToCharmeleonEvolution() {
        // Fetch evolution chain with ID 2 (Charmander's family)
        EvolutionChain evolutionChain = Client.getEvolutionChainById(2);

        assertNotNull("Evolution chain object should not be null", evolutionChain);
        assertEquals("Evolution chain ID should be 2", 2, evolutionChain.getId());

        // Verify the base species in the chain is charmander
        assertEquals("Base species should be charmander", "charmander", evolutionChain.getChain().getSpecies().getName());

        // Verify that charmander evolves into charmeleon
        assertNotNull("Evolves_to list should not be null", evolutionChain.getChain().getEvolvesTo());
        assertFalse("Evolves_to list should not be empty", evolutionChain.getChain().getEvolvesTo().isEmpty());
        assertEquals("Next evolution stage should be charmeleon", "charmeleon", evolutionChain.getChain().getEvolvesTo().get(0).getSpecies().getName());
    }

    // Test Suite 8: Test Generation VI, verify Kalos region, and check that Greninja (ID 658) is correctly listed
    @Test
    public void testGenerationAndGreninjaIdAssociation() {
        // Fetch generation ID 6 (Generation VI)
        Generation generation = Client.getGenerationById(6);

        assertNotNull("Generation object should not be null", generation);
        assertEquals("Generation ID should be 6", 6, generation.getId());
        assertEquals("Generation name should be generation-vi", "generation-vi", generation.getName());

        // Verify that the main region is kalos
        assertNotNull("Main region should not be null", generation.getMainRegion());
        assertEquals("Main region name should be kalos", "kalos", generation.getMainRegion().getName());

        // Verify that pokemon species list contains greninja (checking by URL containing id/658/)
        assertNotNull("Pokemon species list should not be null", generation.getPokemonSpecies());
        assertFalse("Pokemon species list should not be empty", generation.getPokemonSpecies().isEmpty());

        // Check if greninja's ID 658 is present in the URL path within Generation VI species list
        boolean containsGreninjaId = generation.getPokemonSpecies().stream()
                .anyMatch(species -> species.getUrl().endsWith("/658/") || species.getUrl().contains("/pokemon-species/658/"));
        assertTrue("Generation VI should include Greninja with ID 658", containsGreninjaId);
    }

    // Test Suite 9: Test fetching Rare Candy and verifying its ID is 50 and category exists
    @Test
    public void testRareCandyCategory() {
        // Fetch item by name "rare-candy" (Rare Candy is item ID 50 in this library)
        Item item = Client.getItemByName("rare-candy");

        assertNotNull("Item object should not be null", item);
        assertEquals("Item name should be rare-candy", "rare-candy", item.getName());
        assertEquals("Item ID should be 50", 50, item.getId());

        // Verify that the category is available and not null
        assertNotNull("Item category should not be null", item.getCategory());
        assertNotNull("Category name should not be null", item.getCategory().getName());
    }

    // Test Suite 10: Test fetching Location by name (lavender-town) and verifying its region and generation indices
    @Test
    public void testLocationLavenderTownDetails() {
        // Fetch location data for lavender-town
        Location location = Client.getLocationByName("lavender-town");

        assertNotNull("Location object should not be null", location);
        assertEquals("Location name should be lavender-town", "lavender-town", location.getName());

        // Verify that the region is kanto
        assertNotNull("Region should not be null", location.getRegion());
        assertEquals("Region name should be kanto", "kanto", location.getRegion().getName());

        // Verify that game indices / generation information is present
        assertNotNull("Game indices list should not be null", location.getGameIndices());
        assertFalse("Game indices list should not be empty", location.getGameIndices().isEmpty());
    }

}
