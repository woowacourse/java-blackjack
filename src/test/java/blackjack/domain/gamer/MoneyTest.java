package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MoneyTest {

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidParameters")
    @DisplayName("금액 투입 테스트")
    void addCard(int amount, String testName) {
        Money money = new Money();
        assertThatThrownBy(() -> money.add(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(

            Arguments.of(5, "5 투입"),
            Arguments.of(15, "15 투입")
        );
    }
}