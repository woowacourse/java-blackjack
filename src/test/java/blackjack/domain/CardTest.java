package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    @DisplayName("카드 생성 테스트")
    void createCard() {
        Card card = new Card(Symbol.SPADE, Number.ACE);

        assertThat(card).isNotNull();
    }
}
