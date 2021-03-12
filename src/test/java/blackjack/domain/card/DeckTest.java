package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @Test
    @DisplayName("덱에서 1장의 카드를 뽑으면, 1장의 카드가 나온다.")
    void drawCardFromDeck() {
        Deck deck = new Deck();
        assertThat(deck.draw()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("덱에서 52장의 카드를 뽑으면, 매번 다른 카드가 나온다.")
    void drawNonDuplicateCardFromDeck() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        for (int i = 1; i <= 52; ++i) {
            cards.add(deck.draw());
        }
        assertThat(cards).doesNotHaveDuplicates();
    }

    @Test
    @DisplayName("덱에서 1000장의 카드를 뽑아도, 문제가 발생하지 않는다.")
    void draw1000CardsFromDeck() {
        Deck deck = new Deck();
        for (int i = 1; i <= 1000; ++i) {
            deck.draw();
        }
    }
}