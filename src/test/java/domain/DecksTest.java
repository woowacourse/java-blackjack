package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.ShuffledCardsGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DecksTest {

    @DisplayName("52 * 6개의 카드를 생성한다.")
    @Test
    void createDecksTest() {
        // given
        int expectedSize = 312;

        // when
        ShuffledCardsGenerator cardsGenerator = new ShuffledCardsGenerator();
        Decks decks = Decks.createByStrategy(cardsGenerator);

        // then
        assertThat(decks.getCards()).hasSize(expectedSize);
    }
}
