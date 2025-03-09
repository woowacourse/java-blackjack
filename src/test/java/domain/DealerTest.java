package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 처음 받은 2장의 카드의 합이 16 이하라면 카드를 뽑는다")
    @Test
    void canHit() {
        //given
        Deck deck = new Deck();
        Card card1 = new Card(Symbol.COLVER, Rank.ACE);
        Card card2 = new Card(Symbol.HEART, Rank.TWO);
        Card card3 = new Card(Symbol.SPADE, Rank.KING);

        deck.add(card1);
        deck.add(card2);
        deck.add(card3);

        Dealer dealer = new Dealer();
        dealer.prepareGame(deck);

        int initialPoint = dealer.getDeck().calculateTotalPoint();

        //when
        dealer.hit(deck);
        int actual = dealer.getDeck().calculateTotalPoint();

        //then
        assertThat(actual).isNotEqualTo(initialPoint);
    }

    @DisplayName("딜러는 처음 받은 2장의 카드의 합이 17 이상이라면 카드를 뽑지 않는다")
    @Test
    void cannotHit() {
        //given
        Deck deck = new Deck();
        Card card1 = new Card(Symbol.COLVER, Rank.JACK);
        Card card2 = new Card(Symbol.HEART, Rank.NINE);
        Card card3 = new Card(Symbol.SPADE, Rank.KING);

        deck.add(card1);
        deck.add(card2);
        deck.add(card3);

        Dealer dealer = new Dealer();
        dealer.prepareGame(deck);

        int initialPoint = dealer.getDeck().calculateTotalPoint();

        //when
        dealer.hit(deck);
        int actual = dealer.getDeck().calculateTotalPoint();

        //then
        assertThat(actual).isEqualTo(initialPoint);
    }

}
