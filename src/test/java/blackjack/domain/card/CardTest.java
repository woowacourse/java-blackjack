package blackjack.domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @Test
    void Card() {
        assertThat(Card.of(Symbol.ACE, Type.CLUB)).isNotNull();
        assertThat(Card.of(Symbol.TWO, Type.HEART)).isNotNull();
        assertThat(Card.of(Symbol.ACE, Type.CLUB)).isNotNull();
        assertThat(Card.of(Symbol.ACE, Type.CLUB)).isNotNull();
    }
}
