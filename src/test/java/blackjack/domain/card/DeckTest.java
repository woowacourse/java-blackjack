package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DeckTest {

    @Test
    @DisplayName("총 52장의 카드가 생성되므로 52번 draw 할 동안 예외가 발생하지 않는다.")
    void drawDeckTest() {
        Deck deck = new Deck(new CardGenerator());

        assertDoesNotThrow(() -> {
            for (int i = 0; i < 52; i++) {
                deck.drawCard();
            }
        });
    }

    @Test
    @DisplayName("빈 덱에서 카드를 뽑을 경우 예외가 발생한다.")
    void drawEmptyDeckTest() {
        Deck deck = new Deck(new CardGenerator());

        assertThatThrownBy(() -> {
            for (int i = 0; i < 53; i++) {
                deck.drawCard();
            }
        }).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR] 더이상 뽑을 카드가 없습니다.");
    }
}
