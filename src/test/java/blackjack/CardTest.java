package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("스페이드 에이스를 생성한다.")
    void createSpadeAce() {
        Card card = new Card(Suit.SPADE, Number.ACE);

        assertThat(card).isNotNull();
    }
}
