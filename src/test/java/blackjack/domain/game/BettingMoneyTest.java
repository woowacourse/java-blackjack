package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @ParameterizedTest
    @DisplayName("자연수가 아닌 값으로 객체를 생성하려고 하면 예외를 발생시킨다.")
    @ValueSource(strings = {"0", "-1", "1.2", "?"})
    void createExceptionByNotNaturalNumberValue(String invalidValue) {
        assertThatThrownBy(() -> new BettingMoney(invalidValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 자연수여야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("null 값으로 배팅 금액 객체를 생성하려고 하면 예외를 발생시킨다.")
    @NullSource
    void createExceptionByNullValue(String nullValue) {
        assertThatThrownBy(() -> new BettingMoney(nullValue))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("배팅 금액에는 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("빈 문자열으로 배팅 금액 객체를 생성하려고 하면 예외를 발생시킨다.")
    @ValueSource(strings = {"", " "})
    void createExceptionByBlankValue(String blankValue) {
        assertThatThrownBy(() -> new BettingMoney(blankValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 빈 값으로 만들 수 없습니다.");
    }

    @Test
    @DisplayName("10의 배수가 아닌 값으로 배팅 금액 객체를 생성하려고 하면 예외를 발생시킨다.")
    void createExceptionByInvalidUnitValue() {
        assertThatThrownBy(() -> new BettingMoney("1001"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액의 단위는 10입니다.");
    }
}