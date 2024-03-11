package domain.blackjack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DeckTest {

    @DisplayName("카드 덱 초기화")
    @Test
    void initDeck() {
        Deck deck = new Deck();
        assertThat(deck.getCardCount())
                .isEqualTo(52);
    }

    @DisplayName("카드를 1장 뽑으면 덱에서 삭제한다.")
    @Test
    void cardRemove() {
        Deck deck = new Deck();
        deck.draw();

        assertThat(deck.getCardCount())
                .isEqualTo(51);
    }

    @DisplayName("카드를 52장 초과로 뽑으면 에러가 발생한다.")
    @Test
    void cardRemoveWhenEmpty() {
        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertThatThrownBy(deck::draw).isInstanceOf(IllegalStateException.class);
    }
}
