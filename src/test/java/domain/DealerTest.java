package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DealerTest {

    Dealer dealer;

    @BeforeEach
    void setUp() {
        final List<String> players = List.of("crong", "pobi", "jerry", "hardy");
        dealer = new Dealer(players);
    }

    @Test
    void distributeTwoCardsToPlayersTest() {
        int expectedCardSize = 2;
        dealer.init();
        for (Player player : dealer.getPlayers()) {
            assertEquals(expectedCardSize, player.getCards().size());
        }
    }
}
