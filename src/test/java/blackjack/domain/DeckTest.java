package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Stack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewDeck() {
        Stack<Card> cards = new Stack<>();
        cards.add(new Card(CardNumber.ACE, CardSymbol.CLUBS));
        assertDoesNotThrow(() -> new Deck(cards));
    }

    @DisplayName("덱의 가장 위쪽에 위치하는 카드를 뽑을 수 있다.")
    @Test
    void Should_Draw_When_HIT() {
        Stack<Card> cards = new Stack<>();

        Card card1 = new Card(CardNumber.ACE, CardSymbol.CLUBS);
        Card card2 = new Card(CardNumber.ACE, CardSymbol.DIAMONDS);

        cards.add(card1);
        cards.add(card2);

        Deck deck = new Deck(cards);

        assertThat(cards).contains(deck.draw());
    }
}
