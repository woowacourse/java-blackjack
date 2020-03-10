package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @DisplayName("카드를 생성하는 기능")
    @Test
    void Card() {
        assertThat(new Card(Symbol.ACE, Type.CLUB)).isNotNull();
        assertThat(new Card(Symbol.TWO, Type.HEART)).isNotNull();
        assertThat(new Card(Symbol.JACK, Type.DIAMOND)).isNotNull();
        assertThat(new Card(Symbol.KING, Type.SPADE)).isNotNull();
    }
}
