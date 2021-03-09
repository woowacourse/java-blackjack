package blackjack.domain;

import blackjack.exception.InvalidMoneyInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @DisplayName("money 객체 생성")
    @Test
    void create() {
        Money money = new Money("10000");
        assertThat(money).isEqualTo(new Money("10000"));
    }

    @DisplayName("돈 입력값 검증")
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "d", ","})
    void validate(String input) {
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(InvalidMoneyInputException.class)
                .hasMessage("1 이상의 숫자를 입력해야 합니다.");
    }
}
