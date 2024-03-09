package domain.blackjack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("카드 덱 초기화")
    @Test
    void initDeck() {
        Deck deck = new Deck();
        Assertions.assertThat(deck.getCardCount())
                .isEqualTo(52);
    }

    @DisplayName("카드를 1장 뽑으면 덱에서 삭제한다.")
    @Test
    void cardRemove() {
        Deck deck = new Deck();
        deck.draw();

        Assertions.assertThat(deck.getCardCount())
                .isEqualTo(51);
    }

    @DisplayName("덱에 카드가 더 이상 없을 때 드로우를 시도할 경우 예외를 발생시킨다")
    @Test
    void emptyDeck() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        Assertions.assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("덱에 더 이상 카드가 없습니다.");
    }
}
