package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DecksTest {

    @DisplayName("6벌의 덱을 생성한다.")
    @Test
    void createDecksTest() {
        // given
        int expectedSize = 6;

        // when
        Decks decks = new Decks();

        // then
        assertThat(decks.getCards()).hasSize(expectedSize);
    }

    @DisplayName("모든 덱을 돌아가면서 카드를 뽑는다.")
    @Test
    void drawTest() {
        // given
        Decks decks = new Decks();

        // when
        decks.draw();

        // then
        assertThat(decks.getCards()).hasSize(51);
    }
}
