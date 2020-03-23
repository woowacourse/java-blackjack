package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingMoneyTest {
    @DisplayName("bettingMoney 객체 생성자 테스트")
    @Test
    void constructorTest() {
        Assertions.assertThat(new BettingMoney(10_000)).isInstanceOf(BettingMoney.class);
    }

    @DisplayName("bettingMoney 객체 생성시 잘못된 값을 넣었을 때 오류 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void throw_constructorTest(int input) {
        Assertions.assertThatThrownBy(() -> {
            new BettingMoney(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
