package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DealerTest {

    @DisplayName("히트 - 딜러는 카드를 받는다.")
    @Test
    void hitCard() {
        Dealer dealer = new Dealer();
        CardDeck cardDeck = CardDeck.createDeck();

        dealer.hit(cardDeck.getDeck().pop());

        assertThat(dealer.getCards()).hasSize(1);
    }

    @DisplayName("isStay - 딜러는 16이하일 때 스테이 할 수 없다.")
    @Test
    void check16() {
        Dealer dealer = new Dealer();

        Card card1 = new Card(Suit.CLUB, CardNumber.JACK);
        Card card2 = new Card(Suit.CLUB, CardNumber.SIX);

        dealer.hit(card1);
        dealer.hit(card2);

        assertFalse(dealer.isStay());
    }

    @DisplayName("isStay - 딜러는 17 이상일 때 스테이한다.")
    @Test
    void check17() {
        Dealer dealer = new Dealer();

        Card card1 = new Card(Suit.CLUB, CardNumber.JACK);
        Card card2 = new Card(Suit.CLUB, CardNumber.SEVEN);

        dealer.hit(card1);
        dealer.hit(card2);

        assertTrue(dealer.isStay());
    }

}