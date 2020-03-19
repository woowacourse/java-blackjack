package model.user.money;

import exception.IllegalBettingMoneyFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BettingMoneyTest {

    @ParameterizedTest
    @DisplayName("숫자 이외의 값이 들어 올때")
    @ValueSource(strings = {"a", "안녕하세요", ",;'"})
    void validate_String_test(String input) {
        assertThatThrownBy(()->new BettingMoney(input))
            .isInstanceOf(IllegalBettingMoneyFormatException.class)
            .hasMessageMatching("배팅 금액은 100이상의 숫자만 입력 가능합니다.");
    }

    @ParameterizedTest
    @DisplayName("100 미만의 숫자가 들어올 때")
    @ValueSource(strings = {"-1", "0" , "99"})
    void validate_Range_Test(String input) {
        assertThatThrownBy(() -> new BettingMoney(input))
            .isInstanceOf(IllegalBettingMoneyFormatException.class)
            .hasMessageMatching("배팅 금액은 100이상의 숫자만 입력 가능합니다.");
    }
}