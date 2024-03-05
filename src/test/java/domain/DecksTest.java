package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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
        assertThat(decks.getDecks()).hasSize(expectedSize);
    }

    @DisplayName("모든 덱을 돌아가면서 카드를 뽑는다.")
    @Test
    void drawTest() {
        // given
        Decks decks = new Decks();
        int index = 3;

        // when
        decks.draw(index);
        List<Deck> result = decks.getDecks();

        // then
        assertThat(result.get(index).getCards()).hasSize(51);
    }
}
