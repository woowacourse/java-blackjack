package blackjack.domain.deck;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;

class DeckTest {

    @Test
    @DisplayName("뽑히는 카드는 중복되지 않는다")
    void randomCardsNotDuplicatedTest() {
        Deck deck = Deck.generateFrom(new RandomCardStrategy());
        Stack<Card> cards = deck.getAll();
        assertThat(cards.stream().distinct().count()).isEqualTo(cards.size());
    }

    @Test
    @DisplayName("덱의 맨 위에서부터 카드를 뽑는다")
    void drawTest() {
        Stack<Card> expected = new Stack<>();
        expected.add(new Card(CardType.CLOVER, CardNumber.ACE));
        expected.add(new Card(CardType.CLOVER, CardNumber.TWO));
        expected.add(new Card(CardType.CLOVER, CardNumber.THREE));
        Deck deck = Deck.generateFrom(() -> expected);

        assertThat(deck.draw()).isEqualTo(new Card(CardType.CLOVER, CardNumber.THREE));
        assertThat(deck.draw()).isEqualTo(new Card(CardType.CLOVER, CardNumber.TWO));
        assertThat(deck.draw()).isEqualTo(new Card(CardType.CLOVER, CardNumber.ACE));
    }
}
