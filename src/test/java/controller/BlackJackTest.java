package controller;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Dealer;
import domain.Denomination;
import domain.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {


    @Test
    @DisplayName("딜러의 핸드 총합이 16 이하면 카드를 추가로 받는다.")
    void test6() {
        BlackJack blackjack = new BlackJack();
        Dealer dealer = new Dealer();

        dealer.addCard(new Card(Denomination.TWO, Suit.CLUB));
        dealer.addCard(new Card(Denomination.TEN, Suit.DIAMOND));

        blackjack.dealersTurn(dealer);

        assertThat(dealer.getCardCount()).isGreaterThan(2);
    }
}