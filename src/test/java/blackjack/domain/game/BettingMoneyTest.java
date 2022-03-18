package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @ParameterizedTest
    @DisplayName("음의 정수 혹은 0으로 객체를 생성하려고 하면 예외를 발생시킨다.")
    @ValueSource(ints = {0, -1})
    void createExceptionByNotNaturalNumberValue(int invalidValue) {
        assertThatThrownBy(() -> new BettingMoney(invalidValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 양수여야 합니다.");
    }

    @Test
    @DisplayName("10의 배수가 아닌 값으로 배팅 금액 객체를 생성하려고 하면 예외를 발생시킨다.")
    void createExceptionByInvalidUnitValue() {
        assertThatThrownBy(() -> new BettingMoney(1001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액의 단위는 10입니다.");
    }

    @Test
    @DisplayName("수익률을 받아 베팅 금액에 곱해 최종 수익을 반환한다.")
    void getProfit() {
        final BettingMoney bettingMoney = new BettingMoney(1000);
        assertThat(bettingMoney.getProfit(1.5)).isEqualTo(1500);
    }
}