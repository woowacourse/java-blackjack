package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class DeckTest {

    @Test
    @DisplayName("of()는 48개의 카드를 생성하여 반환한다.")
    void test_initialize_cards() {
        // given & when
        List<Card> cards = Deck.initializeCards().getCards();
        int expectedSize = 52;

        // then
        Assertions.assertThat(cards).hasSize(expectedSize);
    }
}
