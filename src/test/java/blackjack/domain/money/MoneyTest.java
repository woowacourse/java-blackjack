package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @ParameterizedTest(name = "[{index}] {0}원")
    @ValueSource(ints = {-10_000, 0, 10_000})
    @DisplayName("원하는 돈을 생성한다.")
    void generateMoney(long value) {
        Money money = Money.valueOf(value);

        assertThat(money.getValue()).isEqualTo(value);
    }

    @ParameterizedTest(name = "[{index}] {0}원 {1} -> {2}원")
    @MethodSource("generateMultiplyArguments")
    @DisplayName("곱셈을 한 결과를 반환한다.")
    void multiply(long value, double multiplier, long expected) {
        assertThat(Money.valueOf(value).multiply(multiplier)).isEqualTo(Money.valueOf(expected));
    }

    static Stream<Arguments> generateMultiplyArguments() {
        return Stream.of(
                Arguments.of(20_000, 1.5, 30_000),
                Arguments.of(20_000, 1, 20_000),
                Arguments.of(20_000, 0, 0),
                Arguments.of(20_000, -1, -20_000)
        );
    }
}
