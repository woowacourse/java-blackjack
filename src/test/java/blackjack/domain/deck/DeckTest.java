package blackjack.domain.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;

class DeckTest {

    @Test
    @DisplayName("덱에 카드 수보다 더 많이 뽑을 경우")
    void drawTest() {
        Deck deck = new Deck(Deck.initCards());
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatThrownBy(deck::draw)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("덱이 더 이상 뽑을 수 있는 카드가 없습니다.");
    }

    @Test
    @DisplayName("처음 두장 뽑는지 확인")
    void drawStartTest() {
        Deck deck = new Deck(Deck.initCards());
        List<Card> cards = deck.drawStartCards();

        assertThat(cards.size()).isEqualTo(2);
    }
}