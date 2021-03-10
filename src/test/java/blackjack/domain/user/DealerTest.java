package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
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

        dealer.addCard(cardDeck.drawCard());

        assertThat(dealer.getCards().cards()).hasSize(1);
    }

    @DisplayName("isStay - 딜러는 점수가 17 미만일 때 카드를 뽑아야 한다.")
    @Test
    void dealerHasToDrawACardWhenUnder17Score() {
        Dealer dealer = new Dealer();

        Card card1 = new Card(Suit.CLUB, Denomination.JACK);
        Card card2 = new Card(Suit.CLUB, Denomination.SIX);

        dealer.addCard(card1);
        dealer.addCard(card2);

        assertTrue(dealer.canContinue());
    }

    @DisplayName("isStay - 딜러는 점수가 16 초과일 때 카드를 뽑지 않는다.")
    @Test
    void dealerCannotDrawACardWhenOver16Score() {
        Dealer dealer = new Dealer();

        Card card1 = new Card(Suit.CLUB, Denomination.JACK);
        Card card2 = new Card(Suit.CLUB, Denomination.SEVEN);

        dealer.addCard(card1);
        dealer.addCard(card2);

        assertFalse(dealer.canContinue());
    }

}