package domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
    @ParameterizedTest
    @ValueSource(strings = {"a", "-"})
    @DisplayName("베팅금액의 인풋값으로 올바르지 않은 형식의 값이 들어가는 경우 예외를 잘 처리하는지")
    void invalidInput(String input) {
        assertThatThrownBy(()->
            Money.create(input)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("베팅금액의 인풋값으로 음수가 들어가는 경우 예외를 잘 처리하는지")
    void minusInput() {
        assertThatThrownBy(() ->
            Money.create("-1")
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
