package domain.betting;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 1000})
    void 배팅금액이_양의정수이고_짝수인_경우_객체정상생성(int value) {
        assertThatCode(() -> BettingAmount.of(value))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void 배팅금액이_양의정수가_아니면_예외처리(int value) {
        assertThatThrownBy(() -> BettingAmount.of(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 양의 정수만 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void 배팅금액이_홀수인_경우_예외처리(int value) {
        assertThatThrownBy(() -> BettingAmount.of(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 짝수만 가능합니다.");
    }
}
