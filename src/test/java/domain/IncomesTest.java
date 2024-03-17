package domain;

import domain.card.Card;
import domain.card.Shape;
import domain.participant.BetAmount;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.result.Income;
import domain.result.Incomes;
import domain.result.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IncomesTest {

    @Test
    @DisplayName("딜러의 최종 수익이 양수이다.")
    void dealerIncome_Win() {
        Player capy1 = new Player(new Name("capy1"), new BetAmount(1000),
                List.of(new Card(5, Shape.CLUB), new Card(6, Shape.CLUB)));
        Player capy2 = new Player(new Name("capy2"), new BetAmount(2000),
                List.of(new Card(5, Shape.HEART), new Card(6, Shape.HEART)));
        Dealer dealer = new Dealer(new ArrayList<>(
                List.of(new Card(6, Shape.SPADE), new Card(10, Shape.SPADE))));

        Incomes incomes = new Incomes(List.of(capy1, capy2));

        assertThat(incomes.dealerIncome(dealer)).isEqualTo(new Income(3000));
    }

    @Test
    @DisplayName("딜러의 최종 수익이 음수이다.")
    void dealerIncome_Lose() {
        Player capy1 = new Player(new Name("capy1"), new BetAmount(1000),
                List.of(new Card(10, Shape.CLUB), new Card(11, Shape.CLUB)));
        Player capy2 = new Player(new Name("capy2"), new BetAmount(2000),
                List.of(new Card(10, Shape.HEART), new Card(11, Shape.HEART)));
        Dealer dealer = new Dealer(new ArrayList<>(
                List.of(new Card(6, Shape.SPADE), new Card(10, Shape.SPADE))));

        Incomes incomes = new Incomes(List.of(capy1, capy2));

        assertThat(incomes.dealerIncome(dealer)).isEqualTo(new Income(-3000));
    }

    @Test
    @DisplayName("승리 시 배팅금액 만큼 수익을 얻는다.")
    void determineIncome_Win() {
        Player capy1 = new Player(new Name("capy1"), new BetAmount(1000),
                List.of(new Card(5, Shape.CLUB), new Card(6, Shape.CLUB)));
        Incomes incomes = new Incomes(List.of(capy1));

        assertThat(incomes.determineIncome(Status.WIN, new BetAmount(1000))).isEqualTo(new Income(1000));
    }

    @Test
    @DisplayName("블랙잭으로 승리 시 배팅금액의 1.5배를 얻는다.")
    void determineIncome_WIN_BLACKJACK() {
        Player capy1 = new Player(new Name("capy1"), new BetAmount(1000),
                List.of(new Card(10, Shape.CLUB), new Card(1, Shape.CLUB)));
        Incomes incomes = new Incomes(List.of(capy1));

        assertThat(incomes.determineIncome(Status.WIN_BLACKJACK, new BetAmount(1000))).isEqualTo(new Income(1500));
    }

    @Test
    @DisplayName("무승부 시 수익을 얻지 못한다.")
    void determineIncome_Tie() {
        Player capy1 = new Player(new Name("capy1"), new BetAmount(1000),
                List.of(new Card(5, Shape.CLUB), new Card(6, Shape.CLUB)));
        Incomes incomes = new Incomes(List.of(capy1));

        assertThat(incomes.determineIncome(Status.TIE, new BetAmount(1000))).isEqualTo(new Income(0));
    }

    @Test
    @DisplayName("패배 시 배팅금액 만큼 금액을 잃는다.")
    void determineIncome_Lose() {
        Player capy1 = new Player(new Name("capy1"), new BetAmount(1000),
                List.of(new Card(5, Shape.CLUB), new Card(6, Shape.CLUB)));
        Incomes incomes = new Incomes(List.of(capy1));

        assertThat(incomes.determineIncome(Status.LOSE, new BetAmount(1000))).isEqualTo(new Income(-1000));
    }
}
