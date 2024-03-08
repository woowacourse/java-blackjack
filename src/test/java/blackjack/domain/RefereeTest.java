package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RefereeTest {

    @DisplayName("플레이어와 딜러가 모두 버스트된 경우 무승부이다.")
    @Test
    void pushWhenBothBust() {
        final Cards playerCards = new Cards(List.of(
                new Card(Number.EIGHT, Suit.CLOVER),
                new Card(Number.TEN, Suit.CLOVER),
                new Card(Number.TEN, Suit.DIAMOND)));
        final Cards dealerCards = new Cards(List.of(
                new Card(Number.SEVEN, Suit.CLOVER),
                new Card(Number.TEN, Suit.HEART),
                new Card(Number.TEN, Suit.SPADE)));

        final Referee referee = new Referee(dealerCards);
        final Outcome outcome = referee.doesPlayerWin(playerCards);

        assertThat(outcome).isEqualTo(Outcome.PUSH);
    }

    @DisplayName("플레이어만 버스트했다면 플레이어의 패배이다.")
    @Test
    void playerLoseWhenOnlyBust() {
        final Cards playerCards = new Cards(List.of(
                new Card(Number.EIGHT, Suit.CLOVER),
                new Card(Number.TEN, Suit.CLOVER),
                new Card(Number.TEN, Suit.DIAMOND)));
        final Cards dealerCards = new Cards(List.of(
                new Card(Number.SEVEN, Suit.CLOVER),
                new Card(Number.TEN, Suit.HEART)));

        final Referee referee = new Referee(dealerCards);
        final Outcome outcome = referee.doesPlayerWin(playerCards);

        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("딜러만 버스트했다면 딜러의 패배이다.")
    @Test
    void dealerLoseWhenOnlyBust() {
        final Cards playerCards = new Cards(List.of(
                new Card(Number.SEVEN, Suit.CLOVER),
                new Card(Number.TEN, Suit.HEART)));
        final Cards dealerCards = new Cards(List.of(
                new Card(Number.EIGHT, Suit.CLOVER),
                new Card(Number.TEN, Suit.CLOVER),
                new Card(Number.TEN, Suit.DIAMOND)));

        final Referee referee = new Referee(dealerCards);
        final Outcome outcome = referee.doesPlayerWin(playerCards);

        assertThat(outcome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("플레이어와 딜러가 모두 블랙잭인 경우 무승부이다.")
    @Test
    void pushWhenBothBlackJack() {
        final Cards playerCards = new Cards(List.of(
                new Card(Number.ACE, Suit.CLOVER),
                new Card(Number.TEN, Suit.CLOVER)));
        final Cards dealerCards = new Cards(List.of(
                new Card(Number.ACE, Suit.DIAMOND),
                new Card(Number.QUEEN, Suit.HEART)));

        final Referee referee = new Referee(dealerCards);
        final Outcome outcome = referee.doesPlayerWin(playerCards);

        assertThat(outcome).isEqualTo(Outcome.PUSH);
    }

    @DisplayName("플레이어만 블랙잭이라면 플레이어의 승리이다.")
    @Test
    void playerWinWhenOnlyBlackJack() {
        final Cards playerCards = new Cards(List.of(
                new Card(Number.ACE, Suit.HEART),
                new Card(Number.TEN, Suit.CLOVER)));
        final Cards dealerCards = new Cards(List.of(
                new Card(Number.TWO, Suit.CLOVER),
                new Card(Number.TEN, Suit.HEART)));

        final Referee referee = new Referee(dealerCards);
        final Outcome outcome = referee.doesPlayerWin(playerCards);

        assertThat(outcome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("딜러만 블랙잭이라면 딜러의 승리이다.")
    @Test
    void dealerWinWhenOnlyBlackJack() {
        final Cards playerCards = new Cards(List.of(
                new Card(Number.TWO, Suit.CLOVER),
                new Card(Number.TEN, Suit.HEART)));
        final Cards dealerCards = new Cards(List.of(
                new Card(Number.ACE, Suit.HEART),
                new Card(Number.TEN, Suit.CLOVER)));

        final Referee referee = new Referee(dealerCards);
        final Outcome outcome = referee.doesPlayerWin(playerCards);

        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("플레이어 또는 딜러 패에 에이스가 포함되지 않은 경우, 플레이어 점수가 딜러보다 높으면 승리한다.")
    @Test
    void playerWinWhenBiggerThanDealer() {
        final Cards playerCards = new Cards(List.of(
                new Card(Number.EIGHT, Suit.CLOVER),
                new Card(Number.TEN, Suit.CLOVER)));
        final Cards dealerCards = new Cards(List.of(
                new Card(Number.SEVEN, Suit.CLOVER),
                new Card(Number.TEN, Suit.HEART)));

        final Referee referee = new Referee(dealerCards);
        final Outcome outcome = referee.doesPlayerWin(playerCards);

        assertThat(outcome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("플레이어 또는 딜러 패에 에이스가 포함되지 않은 경우, 플레이어 점수가 딜러보다 낮으면 패배한다.")
    @Test
    void playerLoseWhenLessThanDealer() {
        final Cards playerCards = new Cards(List.of(
                new Card(Number.SEVEN, Suit.CLOVER),
                new Card(Number.TEN, Suit.HEART)));
        final Cards dealerCards = new Cards(List.of(
                new Card(Number.EIGHT, Suit.CLOVER),
                new Card(Number.TEN, Suit.CLOVER)));

        final Referee referee = new Referee(dealerCards);
        final Outcome outcome = referee.doesPlayerWin(playerCards);

        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("플레이어 또는 딜러 패에 에이스가 포함되지 않은 경우, 플레이어 점수와 딜러의 점수가 같으면 무승부이다.")
    @Test
    void pushWhenSame() {
        final Cards playerCards = new Cards(List.of(
                new Card(Number.SEVEN, Suit.CLOVER),
                new Card(Number.TEN, Suit.HEART)));
        final Cards dealerCards = new Cards(List.of(
                new Card(Number.SEVEN, Suit.DIAMOND),
                new Card(Number.TEN, Suit.CLOVER)));

        final Referee referee = new Referee(dealerCards);
        final Outcome outcome = referee.doesPlayerWin(playerCards);

        assertThat(outcome).isEqualTo(Outcome.PUSH);
    }
}
