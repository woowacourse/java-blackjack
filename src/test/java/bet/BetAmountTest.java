package bet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BetAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {100, 1_550, 3_500, 10_500, 22_222})
    void 베팅금액이_1000원_단위가_아니면_예외가_발생한다(int amount) {
        assertThatThrownBy(() -> new BetAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 1000원 단위만 입력 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1_001_000, 1_500_000, 2_000_000, 3_000_000})
    void 베팅금액이_100만원을_초과하면_예외가_발생한다(int amount) {
        assertThatThrownBy(() -> new BetAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 100만원 이하만 입력 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1_000_000, 800_000, 500_000, 200_000, 100_000, 50_000, 10_000})
    void 베팅금액이_100만원_이하면_예외가_발생하지_않는다(int amount) {
        Assertions.assertDoesNotThrow(() -> new BetAmount(amount));
    }

}
