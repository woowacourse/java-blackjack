package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {

    @Test
    @DisplayName("52장을 모두 뽑은 경우 예외를 발생시킨다.")
    void draw_AllDrew_ExceptionThrown() {
        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatThrownBy(deck::draw).isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("덱에 남아있는 카드가 없습니다.");
    }

    @Test
    @DisplayName("처음 두장을 뽑는다.")
    void drawInitialHands() {
        Deck deck = new Deck();

        assertThat(deck.drawInitialHands()).hasSize(2);
    }
}
