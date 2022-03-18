package blackjack;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    void scoreSumTest() {
        Cards cards = Cards.generateEmpty();
        cards.addCard(Card.generate(Suit.DIAMOND, Denomination.ACE));
        cards.addCard(Card.generate(Suit.HEART, Denomination.ACE));
        cards.addCard(Card.generate(Suit.CLOVER, Denomination.ACE));
        assertThat(cards.scoreSum()).isEqualTo(13);
    }
}
