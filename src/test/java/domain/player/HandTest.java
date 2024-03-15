package domain.player;

import static domain.card.Number.ACE;
import static domain.card.Number.EIGHT;
import static domain.card.Number.FIVE;
import static domain.card.Number.JACK;
import static domain.card.Number.QUEEN;
import static domain.card.Number.SEVEN;
import static domain.card.Number.SIX;
import static domain.card.Number.TEN;
import static domain.card.Shape.CLUB;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static domain.money.GameResult.DRAW;
import static domain.money.GameResult.LOSE;
import static domain.money.GameResult.WIN;
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

        hand.receiveCard(card);

        assertThat(hand.getCards()).contains(card);
    }

    @Test
    @DisplayName("덱 카드의 숫자의 합을 구할 수 있다.")
    void sumCardcurrentDeck() {
        Hand hand = new Hand();

        hand.receiveCard(new Card(Shape.CLUB, Number.THREE));
        hand.receiveCard(new Card(Shape.CLUB, Number.EIGHT));

        assertThat(hand.sumCard()).isEqualTo(11);
    }

    @Test
    @DisplayName("ACE 카드는 합이 11 이하일 때 숫자가 11로 사용된다.")
    void sumCardContainingAce11Test() {
        Hand hand = new Hand();

        hand.receiveCard(new Card(Shape.CLUB, Number.ACE));
        hand.receiveCard(new Card(Shape.CLUB, Number.TWO));

        assertThat(hand.sumCard()).isEqualTo(13);
    }

    @Test
    @DisplayName("ACE 카드는 합이 11 초과일 때 숫자가 1로 사용된다.")
    void sumCardContainingAce1Test() {
        Hand hand = new Hand();

        hand.receiveCard(new Card(Shape.CLUB, Number.ACE));
        hand.receiveCard(new Card(Shape.CLUB, Number.TWO));
        hand.receiveCard(new Card(Shape.CLUB, Number.TEN));

        assertThat(hand.sumCard()).isEqualTo(13);
    }

    @Test
    @DisplayName("카드가 두장이고, 합이 21이면 블랙잭이다.")
    void isBlackJackTest() {
        Hand hand = new Hand();

        hand.receiveCard(new Card(Shape.CLUB, Number.ACE));
        hand.receiveCard(new Card(Shape.CLUB, Number.TEN));

        assertThat(hand.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("합이 21이 아니면 블랙잭이 아니다.")
    void isNotBlackJackBySumTest() {
        Hand hand = new Hand();

        hand.receiveCard(new Card(Shape.CLUB, Number.ACE));
        hand.receiveCard(new Card(Shape.CLUB, Number.TWO));

        assertThat(hand.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("카드가 두장이 아니면 블랙잭이 아니다.")
    void isNotBlackJackByCardsCountTest() {
        Hand hand = new Hand();

        hand.receiveCard(new Card(Shape.CLUB, Number.ACE));
        hand.receiveCard(new Card(Shape.CLUB, Number.TEN));
        hand.receiveCard(new Card(Shape.CLUB, Number.JACK));

        assertThat(hand.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("current(15), opponent(11) -> WIN")
    void should_win_when_current15Opponent11() {
        Hand current = new Hand(new Card(SPADE, SEVEN), new Card(SPADE, EIGHT));
        Hand opponent = new Hand(new Card(SPADE, FIVE), new Card(SPADE, SIX));

        assertThat(current.generateResult(opponent)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("current(11), opponent(11) -> DRAW")
    void should_draw_when_current11Opponent11() {
        Hand current = new Hand(new Card(HEART, FIVE), new Card(HEART, SIX));
        Hand opponent = new Hand(new Card(SPADE, FIVE), new Card(SPADE, SIX));

        assertThat(current.generateResult(opponent)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("current(11), opponent(15) -> LOSE")
    void should_lose_when_current11Opponent15() {
        Hand current = new Hand(new Card(SPADE, FIVE), new Card(SPADE, SIX));
        Hand opponent = new Hand(new Card(SPADE, SEVEN), new Card(SPADE, EIGHT));

        assertThat(current.generateResult(opponent)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("current(Blackjack), opponent(21) -> WIN")
    void should_win_when_currentBlackjackOpponent21() {
        Hand current = new Hand(new Card(SPADE, ACE), new Card(SPADE, TEN));
        Hand opponent = new Hand(new Card(SPADE, FIVE), new Card(SPADE, SIX), new Card(SPADE, JACK));

        assertThat(current.generateResult(opponent)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("current(Blackjack), opponent(Blackjack) -> DRAW")
    void should_draw_when_currentBlackjackOpponentBlackjack() {
        Hand current = new Hand(new Card(HEART, ACE), new Card(HEART, TEN));
        Hand opponent = new Hand(new Card(CLUB, ACE), new Card(CLUB, TEN));

        assertThat(current.generateResult(opponent)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("current(21), opponent(Blackjack) -> LOSE")
    void should_lose_when_current21OpponentBlackjack() {
        Hand current = new Hand(new Card(CLUB, FIVE), new Card(CLUB, SIX), new Card(CLUB, JACK));
        Hand opponent = new Hand(new Card(CLUB, ACE), new Card(CLUB, TEN));

        assertThat(current.generateResult(opponent)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("current(Busted), opponent(11) -> LOSE")
    void should_lose_when_currentBustedOpponent11() {
        Hand current = new Hand(new Card(CLUB, QUEEN), new Card(CLUB, TEN), new Card(CLUB, JACK));
        Hand opponent = new Hand(new Card(CLUB, FIVE), new Card(CLUB, SIX));

        assertThat(current.generateResult(opponent)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("current(Busted), opponent(Busted) -> LOSE")
    void should_lose_when_currentBustedOpponentBusted() {
        Hand current = new Hand(new Card(CLUB, QUEEN), new Card(CLUB, TEN), new Card(CLUB, JACK));
        Hand opponent = new Hand(new Card(HEART, QUEEN), new Card(HEART, TEN), new Card(HEART, JACK));

        assertThat(current.generateResult(opponent)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("current(11), opponent(Busted) -> WIN")
    void should_win_when_current11OpponentBusted() {
        Hand current = new Hand(new Card(CLUB, FIVE), new Card(CLUB, SIX));
        Hand opponent = new Hand(new Card(CLUB, QUEEN), new Card(CLUB, TEN), new Card(CLUB, JACK));

        assertThat(current.generateResult(opponent)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("카드의 합이 21 초과이면 busted이다.")
    void bustedTest() {
        Hand hand = new Hand(
                new Card(Shape.CLUB, Number.JACK),
                new Card(Shape.CLUB, Number.QUEEN),
                new Card(Shape.CLUB, Number.KING));

        assertThat(hand.busted()).isTrue();
    }

    @Test
    @DisplayName("카드의 합이 21 이하이면 busted이지 않다.")
    void notBustedTest() {
        Hand hand = new Hand(new Card(Shape.CLUB, Number.JACK), new Card(Shape.CLUB, Number.QUEEN));

        assertThat(hand.busted()).isFalse();
    }
}
