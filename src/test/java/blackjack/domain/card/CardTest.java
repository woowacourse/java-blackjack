package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardTest {

    @DisplayName("부적절한 카드 정보를 호출할 경우 예외가 발생하는 것을 확인한다.")
    @Test
    void not_cached_card() {
        assertThatThrownBy(() -> Card.of(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("적절하지 않은 카드 정보가 존재합니다.");
    }

    @DisplayName("52개의 생성된 카드 개수가 올바른지 확인한다.")
    @Test
    void total_card_cache() {
        List<Card> playingCards = Card.getTotalCard();

        assertThat(playingCards.size()).isEqualTo(52);
    }
}
