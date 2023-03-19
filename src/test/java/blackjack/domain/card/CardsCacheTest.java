package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardsCacheTest {
    @DisplayName("캐싱된 카드 52장을 가져온다.")
    @Test
    void getAllCards() {
        final List<Card> cards = CardsCache.getAllCards();
        assertThat(cards).hasSize(52);
    }
}
