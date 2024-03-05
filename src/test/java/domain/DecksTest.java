package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DecksTest {

    @DisplayName("6벌의 덱을 생성한다.")
    @Test
    void createDecks() {
        // given
        int expectedSize = 6;

        // when
        Decks decks = new Decks();

        // then
        assertThat(decks.getDecks()).hasSize(expectedSize);
    }
}
