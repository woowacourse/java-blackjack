package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {
    @DisplayName("카드를 52 * 6 만큼 생성한다.")
    @Test
    void generate() {
        // given
        CardDeck cardDeck = CardDeck.generate();

        // when && then
        Assertions.assertThat(cardDeck.size()).isEqualTo(52 * 6);
    }
}
