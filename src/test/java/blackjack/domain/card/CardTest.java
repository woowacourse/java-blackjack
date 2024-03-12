package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("카드")
class CardTest {
    @Test
    @DisplayName("는 총 52장 이다.")
    void cardNumTest() {
        // given
        List<Card> cards = Card.getAll();

        // when & then
        assertThat(cards.size()).isEqualTo(52);
    }
}
