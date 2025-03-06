package blackjack.domain.deck;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;

class DeckTest {

    @Test
    @DisplayName("뽑히는 카드는 중복되지 않는다")
    void randomCardsNotDuplicatedTest() {
        Deck deck = Deck.generateFrom(new RandomCardStrategy());
        Stack<Card> cards = deck.getAll();
        assertThat(cards.stream().distinct().count()).isEqualTo(cards.size());
    }

    @Test
    void generateFrom() {
    }

    @Test
    void draw() {
    }
}
