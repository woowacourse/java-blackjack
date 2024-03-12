package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutcomeTest {

    @DisplayName("플레이어와 딜러가 모두 버스트된 경우 무승부이다.")
    @Test
    void pushWhenBothBust() {
        final Hand playerHand = new Hand(List.of(
                Card.of(Number.EIGHT, Suit.CLOVER),
                Card.of(Number.TEN, Suit.CLOVER),
                Card.of(Number.TEN, Suit.DIAMOND)));
        final Hand dealerHand = new Hand(List.of(
                Card.of(Number.SEVEN, Suit.CLOVER),
                Card.of(Number.TEN, Suit.HEART),
                Card.of(Number.TEN, Suit.SPADE)));

        final Outcome outcome = Outcome.doesPlayerWin(dealerHand, playerHand);

        assertThat(outcome).isEqualTo(Outcome.PUSH);
    }

    @DisplayName("플레이어만 버스트했다면 플레이어의 패배이다.")
    @Test
    void playerLoseWhenOnlyBust() {
        final Hand playerHand = new Hand(List.of(
                Card.of(Number.EIGHT, Suit.CLOVER),
                Card.of(Number.TEN, Suit.CLOVER),
                Card.of(Number.TEN, Suit.DIAMOND)));
        final Hand dealerHand = new Hand(List.of(
                Card.of(Number.SEVEN, Suit.CLOVER),
                Card.of(Number.TEN, Suit.HEART)));

        final Outcome outcome = Outcome.doesPlayerWin(dealerHand, playerHand);

        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("딜러만 버스트했다면 딜러의 패배이다.")
    @Test
    void dealerLoseWhenOnlyBust() {
        final Hand playerHand = new Hand(List.of(
                Card.of(Number.SEVEN, Suit.CLOVER),
                Card.of(Number.TEN, Suit.HEART)));
        final Hand dealerHand = new Hand(List.of(
                Card.of(Number.EIGHT, Suit.CLOVER),
                Card.of(Number.TEN, Suit.CLOVER),
                Card.of(Number.TEN, Suit.DIAMOND)));

        final Outcome outcome = Outcome.doesPlayerWin(dealerHand, playerHand);

        assertThat(outcome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("플레이어와 딜러가 모두 블랙잭인 경우 무승부이다.")
    @Test
    void pushWhenBothBlackJack() {
        final Hand playerHand = new Hand(List.of(
                Card.of(Number.ACE, Suit.CLOVER),
                Card.of(Number.TEN, Suit.CLOVER)));
        final Hand dealerHand = new Hand(List.of(
                Card.of(Number.ACE, Suit.DIAMOND),
                Card.of(Number.QUEEN, Suit.HEART)));

        final Outcome outcome = Outcome.doesPlayerWin(dealerHand, playerHand);

        assertThat(outcome).isEqualTo(Outcome.PUSH);
    }

    @DisplayName("플레이어만 블랙잭이라면 플레이어의 승리이다.")
    @Test
    void playerWinWhenOnlyBlackJack() {
        final Hand playerHand = new Hand(List.of(
                Card.of(Number.ACE, Suit.HEART),
                Card.of(Number.TEN, Suit.CLOVER)));
        final Hand dealerHand = new Hand(List.of(
                Card.of(Number.TWO, Suit.CLOVER),
                Card.of(Number.TEN, Suit.HEART)));

        final Outcome outcome = Outcome.doesPlayerWin(dealerHand, playerHand);

        assertThat(outcome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("딜러만 블랙잭이라면 딜러의 승리이다.")
    @Test
    void dealerWinWhenOnlyBlackJack() {
        final Hand playerHand = new Hand(List.of(
                Card.of(Number.TWO, Suit.CLOVER),
                Card.of(Number.TEN, Suit.HEART)));
        final Hand dealerHand = new Hand(List.of(
                Card.of(Number.ACE, Suit.HEART),
                Card.of(Number.TEN, Suit.CLOVER)));

        final Outcome outcome = Outcome.doesPlayerWin(dealerHand, playerHand);

        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("플레이어 또는 딜러 패에 에이스가 포함되지 않은 경우, 플레이어 점수가 딜러보다 높으면 승리한다.")
    @Test
    void playerWinWhenBiggerThanDealer() {
        final Hand playerHand = new Hand(List.of(
                Card.of(Number.EIGHT, Suit.CLOVER),
                Card.of(Number.TEN, Suit.CLOVER)));
        final Hand dealerHand = new Hand(List.of(
                Card.of(Number.SEVEN, Suit.CLOVER),
                Card.of(Number.TEN, Suit.HEART)));

        final Outcome outcome = Outcome.doesPlayerWin(dealerHand, playerHand);

        assertThat(outcome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("플레이어 또는 딜러 패에 에이스가 포함되지 않은 경우, 플레이어 점수가 딜러보다 낮으면 패배한다.")
    @Test
    void playerLoseWhenLessThanDealer() {
        final Hand playerHand = new Hand(List.of(
                Card.of(Number.SEVEN, Suit.CLOVER),
                Card.of(Number.TEN, Suit.HEART)));
        final Hand dealerHand = new Hand(List.of(
                Card.of(Number.EIGHT, Suit.CLOVER),
                Card.of(Number.TEN, Suit.CLOVER)));

        final Outcome outcome = Outcome.doesPlayerWin(dealerHand, playerHand);

        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("플레이어 또는 딜러 패에 에이스가 포함되지 않은 경우, 플레이어 점수와 딜러의 점수가 같으면 무승부이다.")
    @Test
    void pushWhenSame() {
        final Hand playerHand = new Hand(List.of(
                Card.of(Number.SEVEN, Suit.CLOVER),
                Card.of(Number.TEN, Suit.HEART)));
        final Hand dealerHand = new Hand(List.of(
                Card.of(Number.SEVEN, Suit.DIAMOND),
                Card.of(Number.TEN, Suit.CLOVER)));

        final Outcome outcome = Outcome.doesPlayerWin(dealerHand, playerHand);

        assertThat(outcome).isEqualTo(Outcome.PUSH);
    }

    @DisplayName("승을 패로 반전한다.")
    @Test
    void convertWinToLose() {
        final Outcome convertedOutcome = Outcome.reverse(Outcome.WIN);

        assertThat(convertedOutcome).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("패를 승으로 반전한다.")
    @Test
    void convertLoseToWin() {
        final Outcome convertedOutcome = Outcome.reverse(Outcome.LOSE);

        assertThat(convertedOutcome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("무는 반전해도 무다.")
    @Test
    void convertPushToPush() {
        final Outcome convertedOutcome = Outcome.reverse(Outcome.PUSH);

        assertThat(convertedOutcome).isEqualTo(Outcome.PUSH);
    }
}
