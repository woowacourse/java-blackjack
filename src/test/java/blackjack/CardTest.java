package blackjack;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.Card;
import blackjack.domain.Denomination;
import blackjack.domain.Suit;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void cardGenerateTest() {
        assertThat(Card.generate(Suit.DIAMOND, Denomination.ACE))
                .isEqualTo(Card.generate(Suit.DIAMOND, Denomination.ACE));
    }

    @Test
    void isAceTest_True() {
        assertThat(Card.generate(Suit.DIAMOND, Denomination.ACE).isAce())
                .isTrue();
    }

    @Test
    void isAceTest_False() {
        assertThat(Card.generate(Suit.DIAMOND, Denomination.TWO).isAce())
                .isFalse();
    }

    @Test
    void scoreTest() {
        assertThat(Card.generate(Suit.DIAMOND, Denomination.TWO).score())
                .isEqualTo(2);
    }
}
