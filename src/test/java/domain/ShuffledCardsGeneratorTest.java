package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.ShuffledCardsGenerator;
import domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShuffledCardsGeneratorTest {

    @DisplayName("52 * 6개의 카드를 생성한다.")
    @Test
    void generateTest() {
        // given
        int expectedSize = 312;

        // when
        ShuffledCardsGenerator cardsGenerator = new ShuffledCardsGenerator();
        List<Card> cards = cardsGenerator.generate();

        // then
        assertThat(cards).hasSize(expectedSize);
    }
}
