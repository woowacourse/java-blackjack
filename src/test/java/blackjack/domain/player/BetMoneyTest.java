package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BetMoneyTest {

    @ParameterizedTest
    @CsvSource(value = {"1000", "50"}, delimiter = ':')
    @DisplayName("돈 계산 로직 확인: 일반 우승")
    void checkPlusMoney(int money) {
        BetMoney betMoney = new BetMoney(money);
        betMoney.plusMoney();

        assertThat(betMoney.getMoney()).isEqualTo(money*2);
    }

    @ParameterizedTest
    @CsvSource(value = {"1000", "50"}, delimiter = ':')
    @DisplayName("돈 계산 로직 확인: 일반 패배")
    void checkMinusMoney(int money) {
        BetMoney betMoney = new BetMoney(money);
        betMoney.minusMoney();

        assertThat(betMoney.getMoney()).isEqualTo(-money*2);
    }

    @ParameterizedTest
    @CsvSource(value = {"1000", "50"}, delimiter = ':')
    @DisplayName("돈 계산 로직 확인: 블랙잭 우승")
    void checkBlackjackPlusMoney(int money) {
        BetMoney betMoney = new BetMoney(money);
        betMoney.plusBlackjackMoney();

        assertThat(betMoney.getMoney()).isEqualTo(money + money*1.5);
    }

    @ParameterizedTest
    @CsvSource(value = {"1000", "50"}, delimiter = ':')
    @DisplayName("돈 계산 로직 확인: 블랙잭 패배")
    void checkBlackjackLoseMoney(int money) {
        BetMoney betMoney = new BetMoney(money);
        betMoney.minusBlackjackMoney();

        assertThat(betMoney.getMoney()).isEqualTo(-(money + money*1.5));
    }
}
