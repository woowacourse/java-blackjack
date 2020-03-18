package domains.user.money;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BettingMoneyTest {
    @DisplayName("문자열을 입력받아 객체를 생성")
    @Test
    void constructor_String_CreateBettingMoney() {
        Assertions.assertThat(new BettingMoney("1")).isInstanceOf(Money.class);
    }

    @DisplayName("null 혹은 빈 문자열이 입력되었을 경우 InvalidMoneyException 발생")
    @ParameterizedTest
    @NullAndEmptySource
    void constructor_NullAndEmpty_ExceptionThrown(String money) {
        assertThatThrownBy(() -> new BettingMoney(money))
                .isInstanceOf(InvalidMoneyException.class)
                .hasMessage(InvalidMoneyException.NULL_OR_EMPTY);
    }

    @DisplayName("문자가 입력되었을 경우 InvalidMoneyException 발생")
    @Test
    void constructor_NotNumberFormatString_ExceptionThrown() {
        assertThatThrownBy(() -> new BettingMoney("%"))
                .isInstanceOf(InvalidMoneyException.class)
                .hasMessage(InvalidMoneyException.NOT_NUMBER_FORMAT);
    }

    @DisplayName("음수 혹은 0이 입력되었을 경우 InvalidMoneyException 발생")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "-10"})
    void constructor_ZeroOrNegative_ExceptionThrown(String money) {
        assertThatThrownBy(() -> new BettingMoney(money))
                .isInstanceOf(InvalidBettingMoneyException.class)
                .hasMessage(InvalidBettingMoneyException.ZERO_OR_NEGATIVE);
    }
}
