package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.ShuffledDecksGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DecksTest {

    @DisplayName("6벌의 덱을 생성한다.")
    @Test
    void createDecksTest() {
        // given
        int expectedSize = 312;

        // when
        ShuffledDecksGenerator decksGenerator = new ShuffledDecksGenerator();
        Decks decks = Decks.createByStrategy(decksGenerator);

        // then
        assertThat(decks.getCards()).hasSize(expectedSize);
    }
}
