package blackjack.controller;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import java.util.Collection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShuffledCardsGeneratorTest {

    @Test
    @DisplayName("생성된 카드에 중복이 없어야 한다.")
    void generate_success() {
        // given
        DeckGenerator deckGenerator = new ShuffledDeckGenerator();

        // when
        Collection<Card> cards = deckGenerator.generate().getCards();
        long uniqueCardsCount = cards.stream()
                .distinct()
                .count();

        // then
        assertThat(cards)
                .hasSize(52);
        assertThat(uniqueCardsCount)
                .isEqualTo(52);
    }
}
