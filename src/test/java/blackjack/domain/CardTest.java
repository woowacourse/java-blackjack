package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void isAce() {
        // given
        Card card = new Card(CardNumber.ACE, CardShape.CLOVER);

        // when
        boolean isAce = card.isAce();

        // then
        assertThat(isAce).isTrue();
    }
}