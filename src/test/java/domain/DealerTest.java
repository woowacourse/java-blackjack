package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    List<String> names;
    Dealer dealer;

    @BeforeEach
    void setUp() {
        names = List.of("crong", "pobi", "jerry", "hardy");
        dealer = new Dealer(names);
        dealer.init();
    }

    @DisplayName("처음 카드를 나눠주면 플레이어의 패의 크기는 2이다.")
    @Test
    void distributeTwoCardsToPlayersTest() {
        int expectedCardSize = 2;
        for (String name : names) {
            assertEquals(expectedCardSize, dealer.getCards(name).size());
        }
    }

    @DisplayName("처음 카드를 나눠주면 플레이어의 패의 크기는 2이다.")
    @Test
    void drawCardTest() {
        int expectedCardSize = 3;
        for (String name : names) {
            dealer.drawCard(name);
            assertEquals(expectedCardSize, dealer.getCards(name).size());
        }
    }
}
