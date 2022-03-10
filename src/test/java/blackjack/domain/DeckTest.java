package blackjack.domain;


import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @Test
    @DisplayName("덱은 48장의 카드를 가지고 있다")
    void deckSize() {
        Deck deck = new Deck();
        int size = deck.size();

        assertThat(size).isEqualTo(48);
    }

    @Test
    @DisplayName("한장의 카드를 뽑은 뒤 덱에 존재하는 카드는 한장 줄어들어야한다.")
    void draw() {
        Deck deck = new Deck();
        int size = deck.size();
        deck.draw();
        assertThat(deck.size()).isEqualTo(size - 1);
    }

    @Test
    @DisplayName("첫 드로우에 카드 두 장을 반환한다")
    void initialDraw() {
        Deck deck = new Deck();

        List<Card> init = deck.initialDraw();
        assertThat(init.size()).isEqualTo(2);
    }
}
