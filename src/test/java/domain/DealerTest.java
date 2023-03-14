package domain;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.player.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("dealer는 점수가 16점 이하일때 뽑을 수 있다.")
    void dealerHittableTest() {
        //given
        Dealer dealer = new Dealer();
        //then
        assertThat(dealer.isHittable()).isTrue();
    }

    @Test
    @DisplayName("dealer는 점수가 16점 일때 뽑을 수 있다.")
    void dealerHittableTest2() {
        //given
        Dealer dealer = new Dealer();
        Card card1 = new Card(Suit.HEART, Denomination.SIX);
        Card card2 = new Card(Suit.HEART, Denomination.QUEEN);
        //when
        dealer.drawCard(card1);
        dealer.drawCard(card2);
        //then
        assertThat(dealer.isHittable()).isTrue();
    }

    @Test
    @DisplayName("dealer는 점수가 17점 이상이면 뽑을 수 없다.")
    void dealerHittableTest3() {
        //given
        Dealer dealer = new Dealer();
        Card card1 = new Card(Suit.HEART, Denomination.SEVEN);
        Card card2 = new Card(Suit.HEART, Denomination.QUEEN);
        //when
        dealer.drawCard(card1);
        dealer.drawCard(card2);
        //then
        assertThat(dealer.isHittable()).isFalse();
    }
}
