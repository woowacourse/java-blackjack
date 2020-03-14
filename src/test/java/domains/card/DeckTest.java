package domains.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {
    @DisplayName("카드를 정상적으로 가져올 경우 deck의 사이즈 감소")
    @Test
    void draw_Success_DecreaseDeckSize() {
        Deck deck = new Deck();
        int initialSize = 52;

        deck.draw();

        assertThat(deck.isSize(initialSize - 1)).isTrue();
    }

    @DisplayName("덱이 비어있을 경우 예외처리")
    @Test
    void draw_DeckIsEmpty_ExceptionThrown() {
        Deck deck = new Deck(new ArrayList<>());
        assertThatThrownBy(deck::draw).isInstanceOf(InvalidDeckException.class)
                .hasMessage(InvalidDeckException.DECK_IS_EMPTY);
    }
}
