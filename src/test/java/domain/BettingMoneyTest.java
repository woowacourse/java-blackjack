package domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.participant.BettingMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @DisplayName("배팅 금액을 정상적으로 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 5000, 10000})
    void create_success(int expected) {
        //when && then
        assertThatNoException()
                .isThrownBy(() -> new BettingMoney(expected));
    }

    @DisplayName("배팅 금액은 1000원 이상 10000원 이하여야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999, 10001})
    void create_fail_high_money(int expected) {
        //when && then
        assertThatThrownBy(() -> new BettingMoney(expected))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 최소 1000원 최대 10000원 입니다.");
    }

    @DisplayName("배팅 금액은 1000원 단위가 아니면 예외를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1500, 2500, 3500})
    void create_fail_by_money_value(int expected) {
        //when && then
        assertThatThrownBy(() -> new BettingMoney(expected))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅금액은 1000원 단위로 입력해야합니다.");
    }
}
