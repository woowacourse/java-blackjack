package blackjack.domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @Test
    void Card() {
        assertThat(new Card(Symbol.ACE, Type.CLUB)).isNotNull();
        assertThat(new Card(Symbol.TWO, Type.HEART)).isNotNull();
        assertThat(new Card(Symbol.JACK, Type.DIAMOND)).isNotNull();
        assertThat(new Card(Symbol.KING, Type.SPADE)).isNotNull();
    }
}
