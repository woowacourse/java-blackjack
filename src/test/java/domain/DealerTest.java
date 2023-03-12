package domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Suit;
import domain.player.Dealer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @DisplayName("딜러의 점수가 16이하이면 더 뽑아야 한다.")
    @Test
    void shouldDealerDrawTest() {
        // given
        final Card firstCard = new Card(Suit.CLOVER, Rank.FOUR);
        final Card secondCard = new Card(Suit.CLOVER, Rank.KING);

        // when
        dealer.drawCard(firstCard);
        dealer.drawCard(secondCard);

        // then
        assertTrue(dealer.isDealerDraw());
    }

    @DisplayName("딜러의 점수가 17이상이면 더 뽑으면 안된다.")
    @Test
    void shouldNotDealerDrawTest() {
        // given
        final Card firstCard = new Card(Suit.CLOVER, Rank.ACE);
        final Card secondCard = new Card(Suit.CLOVER, Rank.KING);

        // when
        dealer.drawCard(firstCard);
        dealer.drawCard(secondCard);

        // then
        assertFalse(dealer.isDealerDraw());
    }
}
