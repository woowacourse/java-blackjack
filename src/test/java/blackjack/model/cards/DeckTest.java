package blackjack.model.cards;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("카드 덱을 생성한다")
    @Test
    void testDeck() {
        Deck deck = new Deck();

        assertThat(deck).isNotNull();
    }

    @DisplayName("덱에서 지정된 개수만큼의 카드를 뽑아 반환한다")
    @Test
    void testDrawCards() {
        Deck deck = new Deck();
        Card card = deck.drawCards(1).getFirst();

        assertThat(card).isNotNull();
    }

    @DisplayName("예외: 덱에서 지정된 개수만큼의 카드를 뽑아 반환할 수 없는 경우")
    @Test
    void testDrawCardsException() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.drawCards(1).getFirst();
        }

        assertThatThrownBy(() -> deck.drawCards(1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 덱에 카드가 부족합니다");
    }
}
