package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {
    @Test
    @DisplayName("유저 카드 덱에 카드를 추가할 수 있다.")
    void pushCardTest() {
        Card card = new Card(Shape.CLUB, Number.ACE);
        Hand hand = new Hand();

        hand.addCard(card);

        assertThat(hand.getCards()).contains(card);
    }

    @Test
    @DisplayName("덱 카드의 숫자의 합을 구할 수 있다.")
    void sumCardPlayerDeck() {
        Hand hand = new Hand();

        hand.addCard(new Card(Shape.CLUB, Number.THREE));
        hand.addCard(new Card(Shape.CLUB, Number.EIGHT));

        assertThat(hand.sumCard()).isEqualTo(11);
    }

    @Test
    @DisplayName("ACE 카드는 합이 11 이하일 때 숫자가 11로 사용된다.")
    void sumCardContainingAceTest() {
        Hand hand = new Hand();

        hand.addCard(new Card(Shape.CLUB, Number.ACE));
        hand.addCard(new Card(Shape.CLUB, Number.TWO));

        assertThat(hand.sumCard()).isEqualTo(13);
    }

    @Test
    @DisplayName("ACE 카드는 합이 11 초과일 때 숫자가 1로 사용된다.")
    void sumCardContainingAceTest2() {
        Hand hand = new Hand();

        hand.addCard(new Card(Shape.CLUB, Number.ACE));
        hand.addCard(new Card(Shape.CLUB, Number.TWO));
        hand.addCard(new Card(Shape.CLUB, Number.TEN));

        assertThat(hand.sumCard()).isEqualTo(13);
    }

    @Test
    @DisplayName("카드가 두장이고, 합이 21이면 블랙잭이다.")
    void isBlackJackTest() {
        Hand hand = new Hand();

        hand.addCard(new Card(Shape.CLUB, Number.ACE));
        hand.addCard(new Card(Shape.CLUB, Number.TEN));

        assertThat(hand.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("합이 21이 아니면 블랙잭이 아니다.")
    void isNotBlackJackBySumTest() {
        Hand hand = new Hand();

        hand.addCard(new Card(Shape.CLUB, Number.ACE));
        hand.addCard(new Card(Shape.CLUB, Number.TWO));

        assertThat(hand.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("카드가 두장이 아니면 블랙잭이 아니다.")
    void isNotBlackJackByCardsCountTest() {
        Hand hand = new Hand();

        hand.addCard(new Card(Shape.CLUB, Number.ACE));
        hand.addCard(new Card(Shape.CLUB, Number.TEN));
        hand.addCard(new Card(Shape.CLUB, Number.JACK));

        assertThat(hand.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("카드의 합이 21 초과이면 busted이다.")
    void bustedTest() {
        Hand hand = new Hand();

        hand.addCard(new Card(Shape.CLUB, Number.JACK));
        hand.addCard(new Card(Shape.CLUB, Number.QUEEN));
        hand.addCard(new Card(Shape.CLUB, Number.KING));

        assertThat(hand.busted()).isTrue();
    }

    @Test
    @DisplayName("카드의 합이 21 이하이면 busted이지 않다.")
    void notBustedTest() {
        Hand hand = new Hand();

        hand.addCard(new Card(Shape.CLUB, Number.JACK));
        hand.addCard(new Card(Shape.CLUB, Number.QUEEN));

        assertThat(hand.busted()).isFalse();
    }
}
