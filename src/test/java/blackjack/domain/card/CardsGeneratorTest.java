package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardsGeneratorTest {

    @DisplayName("52장의 카드를 생성한다.")
    @Test
    void generate() {
        int expectedCardSize = Shape.values().length * Symbol.values().length;

        List<Card> cards = CardsGenerator.generateShuffledCards();

        assertThat(cards).hasSize(expectedCardSize);
    }
}
