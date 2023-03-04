package blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShuffledCardsGeneratorTest {

    @Test
    @DisplayName("생성된 카드에 중복이 없어야 한다.")
    void generate_success() {
        // given
        CardsGenerator cardsGenerator = new ShuffledCardsGenerator();

        // when
        List<Card> cards = cardsGenerator.generate().getCards();
        long uniqueCardsCount = cards.stream()
                .distinct()
                .count();

        // then
        assertThat(cards)
                .hasSize(48);
        assertThat(uniqueCardsCount)
                .isEqualTo(48);
    }
}
