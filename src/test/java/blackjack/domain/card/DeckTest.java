package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DeckTest {

    @Test
    @DisplayName("52개의 카드를 섞어서 덱에 넣는다.")
    void deckCreateTest() {
        Deck deck = new Deck(new CardGenerator());

        assertThat(deck.getCards().size()).isEqualTo(52);
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

    @Test
    @DisplayName("정상적으로 카드를 뽑는 경우 예외가 발생하지 않는다.")
    void drawDeckTest() {
        Deck deck = new Deck(new CardGenerator());

        assertDoesNotThrow(() -> {
            deck.drawCard();
        });
    }
}
