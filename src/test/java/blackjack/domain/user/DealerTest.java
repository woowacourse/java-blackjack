package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Dealer;
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

        dealer.hit(cardDeck.drawCard());

        assertThat(dealer.getCards()).hasSize(1);
    }

    @DisplayName("isStay - 딜러는 점수가 17 미만일 때 카드를 뽑아야 한다.")
    @Test
    void dealerHasToDrawACardWhenUnder17Score() {
        Dealer dealer = new Dealer();

        Card card1 = new Card(Suit.CLUB, CardNumber.JACK);
        Card card2 = new Card(Suit.CLUB, CardNumber.SIX);

        dealer.hit(card1);
        dealer.hit(card2);

        assertTrue(dealer.hasToDrawACard());
    }

    @DisplayName("isStay - 딜러는 점수가 16 초과일 때 카드를 뽑지 않는다.")
    @Test
    void dealerCannotDrawACardWhenOver16Score() {
        Dealer dealer = new Dealer();

        Card card1 = new Card(Suit.CLUB, CardNumber.JACK);
        Card card2 = new Card(Suit.CLUB, CardNumber.SEVEN);

        dealer.hit(card1);
        dealer.hit(card2);

        assertFalse(dealer.hasToDrawACard());
    }

}