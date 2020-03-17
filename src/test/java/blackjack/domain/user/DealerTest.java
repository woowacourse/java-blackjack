package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    void create() {
        assertThat(Dealer.create()).isNotNull();
    }

    @Test
    void shouldReceiveCard_returnTrue() {
        Dealer dealer = Dealer.create();

        Stack<Card> cards = new Stack<>();
        cards.add(new Card(Symbol.EIGHT, Type.SPADE));
        cards.add(new Card(Symbol.EIGHT, Type.SPADE));

        Deck deck = new Deck(cards);
        dealer.drawCardsInTurn(deck);
        dealer.drawCardsInTurn(deck);

        assertThat(dealer.shouldReceiveCard()).isTrue();
    }

    @Test
    void shouldReceiveCard_returnFalse() {
        Dealer dealer = Dealer.create();

        Stack<Card> cards = new Stack<>();
        cards.add(new Card(Symbol.EIGHT, Type.SPADE));
        cards.add(new Card(Symbol.NINE, Type.SPADE));

        Deck deck = new Deck(cards);
        dealer.drawCardsInTurn(deck);
        dealer.drawCardsInTurn(deck);

        assertThat(dealer.shouldReceiveCard()).isFalse();
    }
}