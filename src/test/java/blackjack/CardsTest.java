package blackjack;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    void scoreSumTest() {
        Cards cards = Cards.generateEmpty();
        cards.addCard(Card.generate(Suit.DIAMOND, Denomination.FIVE));
        cards.addCard(Card.generate(Suit.HEART, Denomination.ACE));
        cards.addCard(Card.generate(Suit.CLOVER, Denomination.K));
        assertThat(cards.scoreSum()).isEqualTo(16);
    }
}
