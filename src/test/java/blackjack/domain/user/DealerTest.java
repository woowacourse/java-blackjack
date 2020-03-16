package blackjack.domain.user;

import blackjack.domain.card.*;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.assertj.core.api.Assertions.*;

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
        dealer.drawCard(deck);
        dealer.drawCard(deck);

        assertThat(dealer.shouldReceiveCard()).isTrue();
    }

    @Test
    void shouldReceiveCard_returnFalse() {
        Dealer dealer = Dealer.create();

        Stack<Card> cards = new Stack<>();
        cards.add(new Card(Symbol.EIGHT, Type.SPADE));
        cards.add(new Card(Symbol.NINE, Type.SPADE));

        Deck deck = new Deck(cards);
        dealer.drawCard(deck);
        dealer.drawCard(deck);

        assertThat(dealer.shouldReceiveCard()).isFalse();
    }
}