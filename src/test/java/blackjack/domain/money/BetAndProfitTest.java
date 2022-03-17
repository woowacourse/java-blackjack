package blackjack.domain.money;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BetAndProfitTest {
    @DisplayName("from 메소드에 베팅금액을 전달하면 BetAndProfit 인스턴스가 생성되어 반환된다.")
    @Test
    void from_withMoneyReturnsNewInstanceOfBetAndProfit() {
        // given
        Money betMoney = Money.from(10000);

        // when
        BetAndProfit betAndProfit = BetAndProfit.from(betMoney);

        // then
        assertThat(betAndProfit).isNotNull();
    }

    @DisplayName("from 메소드에 음수값을 갖는 Money 는 전달할 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {-1000, -1})
    void from_throwsExceptionOnNegativeMoney(int input) {
        assertThatThrownBy(() -> BetAndProfit.from(Money.from(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 음수일 수 없습니다.");
    }

    @DisplayName("서로 다른 BetAndProfit 의 betMoney 와 profitMoney 필드의 값이 동일하면 동등성을 갖는다.")
    @Test
    void equalityTest() {
        // given
        Money betMoney = Money.from(10000);

        // when
        BetAndProfit betAndProfit1 = BetAndProfit.from(betMoney);
        BetAndProfit betAndProfit2 = BetAndProfit.from(betMoney);

        // then
        assertThat(betAndProfit1).isEqualTo(betAndProfit2);
    }

    @DisplayName("win 메소드는 profitMoney 필드를 betMoney 와 동일하게 설정한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 10000, 20000})
    void win_setProfitMoneySameAsBetMoney(int input) {
        // given
        Money betMoney = Money.from(input);
        BetAndProfit betAndProfit = BetAndProfit.from(betMoney);

        // when
        betAndProfit.win();
        Money actual = betAndProfit.getProfitMoney();

        // then
        assertThat(actual).isEqualTo(betMoney);
    }


    @DisplayName("winWithBlackjack 메소드는 profitMoney 필드를 betMoney 의 1.5배로 설정한다.")
    @ParameterizedTest
    @CsvSource(value = {"10000,15000", "20000,30000", "0,0"})
    void winWithBlackjack_setProfitMoneySameAsBetMoney(int rawBetMoney, int rawProfitMoney) {
        // given
        Money betMoney = Money.from(rawBetMoney);
        BetAndProfit betAndProfit = BetAndProfit.from(betMoney);

        // when
        betAndProfit.winWithBlackjack();
        Money actual = betAndProfit.getProfitMoney();
        Money expected = Money.from(rawProfitMoney);

        // then
        assertThat(actual).isEqualTo(expected);
    }


    @DisplayName("lose 메소드는 profitMoney 필드를 betMoney 의 음수값을 갖는 Money 로 설정한다.")
    @ParameterizedTest
    @CsvSource(value = {"10000,-10000", "20000,-20000", "0,0"})
    void lose_setProfitMoneySameAsNegativeBetMoney(int rawBetMoney, int rawProfitMoney) {
        // given
        Money betMoney = Money.from(rawBetMoney);
        BetAndProfit betAndProfit = BetAndProfit.from(betMoney);

        // when
        betAndProfit.lose();
        Money actual = betAndProfit.getProfitMoney();
        Money expected = Money.from(rawProfitMoney);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}