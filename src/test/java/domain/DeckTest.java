package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {

    @DisplayName("트럼프 카드 덱을 초기화한다.")
    @Test
    void initDeckTest() {
        // When
        Deck deck = Deck.init();

        // Then
        assertThat(deck).isNotNull();
    }

    @DisplayName("카드를 한 장 건네준다.")
    @Test
    void drawnTest() {
        // Given
        Deck deck = Deck.init();

        // When
        PlayingCard card = deck.drawn();

        // Then
        assertThat(card).isNotNull();
    }

    @DisplayName("덱에서 모든 카드를 뽑은 이후에 카드를 뽑을 경우 예외를 발생한다.")
    @Test
    void drawnMaximumTest() {
        // Given
        Deck deck = Deck.init();

        // When
        IntStream.range(0, 52).forEach(i -> deck.drawn());

        // Then
        assertThatThrownBy(deck::drawn)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("덱이 모두 소진되었습니다.");
    }
}
