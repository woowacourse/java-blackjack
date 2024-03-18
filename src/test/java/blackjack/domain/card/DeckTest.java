package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    Deck deck;

    @BeforeEach
    void setUP() {
        deck = new Deck();
    }

    @Test
    @DisplayName("카드 덱은 52장의 카드로 이루어져 있다.")
    void deckSizeTest() {
        assertThat(deck.getCards().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("맨 위의 카드를 한 장 뽑는다.")
    void drawTest() {
        assertThat(deck.draw()).isEqualTo(new Card(Shape.HEART, Number.ACE));
    }

    @Test
    @DisplayName("52장의 카드를 모두 뽑으면 더 이상 뽑을 수 없다.")
    void addAfterDrawTest() {
        // 52장의 카드를 뽑는다.
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모든 카드를 사용하였습니다.");
    }
}
