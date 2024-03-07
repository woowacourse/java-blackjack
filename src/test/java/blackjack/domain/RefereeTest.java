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

    @DisplayName("플레이어 또는 딜러 패에 에이스가 포함되지 않은 경우, 플레이어 점수가 딜러보다 높으면 승리한다.")
    @Test
    void decidePlayerWinningResult() {
        Cards playerCards = new Cards(List.of(
                new Card(Number.EIGHT, Suit.CLOVER),
                new Card(Number.TEN, Suit.CLOVER)));
        Cards dealerCards = new Cards(List.of(
                new Card(Number.SEVEN, Suit.CLOVER),
                new Card(Number.TEN, Suit.HEART)));

        Referee referee = new Referee(dealerCards);
        Outcome outcome = referee.doesPlayerWin(playerCards);

        assertThat(outcome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("플레이어와 딜러가 모두 버스트된 경우 무승부이다.")
    @Test
    void pushWhenBothBust() {
        Cards playerCards = new Cards(List.of(
                new Card(Number.EIGHT, Suit.CLOVER),
                new Card(Number.TEN, Suit.CLOVER),
                new Card(Number.TEN, Suit.DIAMOND)));
        Cards dealerCards = new Cards(List.of(
                new Card(Number.SEVEN, Suit.CLOVER),
                new Card(Number.TEN, Suit.HEART),
                new Card(Number.TEN, Suit.SPADE)));

        Referee referee = new Referee(dealerCards);
        Outcome outcome = referee.doesPlayerWin(playerCards);

        assertThat(outcome).isEqualTo(Outcome.PUSH);
    }

    @DisplayName("플레이어만 버스트했다면 플레이어의 패배이다.")
    @Test
    void playerLoseWhenOnlyBust() {
        Cards playerCards = new Cards(List.of(
                new Card(Number.EIGHT, Suit.CLOVER),
                new Card(Number.TEN, Suit.CLOVER),
                new Card(Number.TEN, Suit.DIAMOND)));
        Cards dealerCards = new Cards(List.of(
                new Card(Number.SEVEN, Suit.CLOVER),
                new Card(Number.TEN, Suit.HEART)));

        Referee referee = new Referee(dealerCards);
        Outcome outcome = referee.doesPlayerWin(playerCards);

        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("플레이어와 딜러가 모두 블랙잭인 경우 무승부이다.")
    @Test
    void pushWhenBothBlackJack() {
        Cards playerCards = new Cards(List.of(
                new Card(Number.ACE, Suit.CLOVER),
                new Card(Number.TEN, Suit.CLOVER)));
        Cards dealerCards = new Cards(List.of(
                new Card(Number.ACE, Suit.DIAMOND),
                new Card(Number.QUEEN, Suit.HEART)));

        Referee referee = new Referee(dealerCards);
        Outcome outcome = referee.doesPlayerWin(playerCards);

        assertThat(outcome).isEqualTo(Outcome.PUSH);
    }

    @DisplayName("플레이어만 블랙잭이라면 플레이어의 승리이다.")
    @Test
    void playerWinWhenOnlyBlackJack() {
        Cards playerCards = new Cards(List.of(
                new Card(Number.ACE, Suit.HEART),
                new Card(Number.TEN, Suit.CLOVER)));
        Cards dealerCards = new Cards(List.of(
                new Card(Number.TWO, Suit.CLOVER),
                new Card(Number.TEN, Suit.HEART)));

        Referee referee = new Referee(dealerCards);
        Outcome outcome = referee.doesPlayerWin(playerCards);

        assertThat(outcome).isEqualTo(Outcome.WIN);
    }
}
