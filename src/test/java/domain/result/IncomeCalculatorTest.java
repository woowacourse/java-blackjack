package domain.result;

import domain.participant.BetAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IncomeCalculatorTest {

    @Test
    @DisplayName("승리 시 배팅금액 만큼 수익을 얻는다.")
    void determineIncome_Win() {
        IncomeCalculator incomeCalculator = new IncomeCalculator();

        assertThat(incomeCalculator.determineIncome(Status.WIN, new BetAmount(1000))).isEqualTo(new Income(1000));
    }

    @Test
    @DisplayName("블랙잭으로 승리 시 배팅금액의 1.5배를 얻는다.")
    void determineIncome_WIN_BLACKJACK() {
        IncomeCalculator incomeCalculator = new IncomeCalculator();

        assertThat(incomeCalculator.determineIncome(Status.WIN_BLACKJACK, new BetAmount(1000))).isEqualTo(new Income(1500));
    }

    @Test
    @DisplayName("무승부 시 수익을 얻지 못한다.")
    void determineIncome_Tie() {
        IncomeCalculator incomeCalculator = new IncomeCalculator();

        assertThat(incomeCalculator.determineIncome(Status.TIE, new BetAmount(1000))).isEqualTo(new Income(0));
    }

    @Test
    @DisplayName("패배 시 배팅금액 만큼 금액을 잃는다.")
    void determineIncome_Lose() {
        IncomeCalculator incomeCalculator = new IncomeCalculator();

        assertThat(incomeCalculator.determineIncome(Status.LOSE, new BetAmount(1000))).isEqualTo(new Income(-1000));
    }

}
