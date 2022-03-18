package blackjack;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void cardGenerateTest() {
        assertThat(Card.generate(Suit.DIAMOND, Denomination.ACE))
                .isEqualTo(Card.generate(Suit.DIAMOND, Denomination.ACE));
    }
}
