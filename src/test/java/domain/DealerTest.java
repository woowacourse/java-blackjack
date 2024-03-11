package domain;

import domain.constant.CardNumber;
import domain.constant.CardType;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("딜러는 자신의 패를 반환할 수 있다.")
    void getHand() {
        Dealer dealer = new Dealer();
        List<Card> cards = dealer.getCards();
        Assertions.assertThat(cards).isNotNull();
    }

    @Test
    @DisplayName("딜러는 처음에 카드 2장을 받을 수 있다.")
    void pickTwoCard() {
        Dealer dealer = new Dealer();
        Card one = new Card(CardType.CLOVER,CardNumber.ACE);
        Card two = new Card(CardType.CLOVER,CardNumber.TWO);
        Deck deck = new Deck(one,two);
        dealer.pickTwoCards(deck);
        List<Card> cards = dealer.getCards();
        Assertions.assertThat(cards)
                .contains(one)
                .contains(two);
    }
    @Test
    @DisplayName("딜러가 카드를 뽑을 경우, 점수가 16점 이하일 경우 카드를 계속 뽑고, 뽑은 횟수를 반환한다.")
    void takeCard() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck(
                new Card(CardType.SPADE, CardNumber.TEN),
                new Card(CardType.SPADE, CardNumber.FIVE),
                new Card(CardType.SPADE, CardNumber.ACE),
                new Card(CardType.DIAMOND, CardNumber.ACE));
        int drawCount = dealer.hit(deck);
        Assertions.assertThat(3).isEqualTo(drawCount);
    }

    @Test
    @DisplayName("딜러의 이름은 \"딜러\"로 초기화 된다.")
    void isName() {
        Dealer gamer = new Dealer();
        Assertions.assertThat(gamer.isName("딜러")).isTrue();
    }

    @Test
    @DisplayName("딜러의 점수 합계를 반환한다.")
    void getTotalScore() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck(
                new Card(CardType.SPADE, CardNumber.TEN),
                new Card(CardType.SPADE, CardNumber.ACE));
        int drawCount = dealer.hit(deck);
        Assertions.assertThat(2).isEqualTo(drawCount);
        Assertions.assertThat(dealer.getTotalScore())
                .isEqualTo(21);
    }

    @Test
    @DisplayName("Bust이면 true를 반환한다.")
    void isBustTrue() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck(
                new Card(CardType.SPADE, CardNumber.TEN),
                new Card(CardType.SPADE, CardNumber.SIX),
                new Card(CardType.SPADE, CardNumber.TEN));

        dealer.hit(deck);
        Assertions.assertThat(dealer.isBust()).isTrue();
    }

    @Test
    @DisplayName("Bust가 아니면 false를 반환한다.")
    void isBustFalse() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck(
                new Card(CardType.SPADE, CardNumber.TEN),
                new Card(CardType.SPADE, CardNumber.TEN));
        dealer.hit(deck);
        Assertions.assertThat(dealer.isBust()).isFalse();
    }

}