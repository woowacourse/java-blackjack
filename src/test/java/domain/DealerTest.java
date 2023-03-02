package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    List<String> names;
    Dealer dealer;

    @BeforeEach
    void setUp() {
        Deck deck = new Deck();
        names = List.of("crong", "pobi", "jerry", "hardy");
        dealer = new Dealer(deck, names);
    }

    @DisplayName("처음 카드를 나눠주면 플레이어의 패의 크기는 2이다.")
    @Test
    void distributeTwoCardsToPlayersTest() {
        dealer.init();
        int expectedCardSize = 2;
        for (String name : names) {
            assertEquals(expectedCardSize, dealer.getCards(name).size());
        }
    }

    @DisplayName("처음 카드를 나눠주면 플레이어의 패의 크기는 2이다.")
    @Test
    void drawCardTest() {
        dealer.init();
        int expectedCardSize = 3;
        for (String name : names) {
            dealer.drawCard(name);
            assertEquals(expectedCardSize, dealer.getCards(name).size());
        }
    }

    @DisplayName("딜러의 점수가 16이하이면 더 뽑아야 한다.")
    @Test
    void shouldDealerDrawTest() {
        Card firstCard = new Card(Suit.CLOVER, Rank.FOUR);
        Card secondCard = new Card(Suit.CLOVER, Rank.KING);

        dealer.drawCard(firstCard);
        dealer.drawCard(secondCard);

        assertTrue(dealer.isDealerDraw());
    }

    @DisplayName("딜러의 점수가 17이상이면 더 뽑으면 안된다.")
    @Test
    void shouldNotDealerDrawTest() {
        Card firstCard = new Card(Suit.CLOVER, Rank.ACE);
        Card secondCard = new Card(Suit.CLOVER, Rank.KING);

        dealer.drawCard(firstCard);
        dealer.drawCard(secondCard);

        assertFalse(dealer.isDealerDraw());
    }
}
