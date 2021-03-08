package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardNumberTest {

    private static Stream<Arguments> testNumberSample() {
        return Stream.of(
            Arguments.of("5", 5),
            Arguments.of("10", 10),
            Arguments.of("K", 10),
            Arguments.of("A", 11)
        );
    }

    @ParameterizedTest
    @DisplayName("카드 숫자 값을 잘 가져오는지 테스트")
    @MethodSource("testNumberSample")
    void cardNumberTest(String number, int expected) {
        CardNumber cardNumber = CardNumber.matchByNumber(number);
        assertThat(cardNumber.getValue()).isEqualTo(expected);
    }

    private static Stream<Arguments> testNumberInstanceSample() {
        return Stream.of(
            Arguments.of("5", CardNumber.FIVE),
            Arguments.of("10", CardNumber.TEN),
            Arguments.of("K", CardNumber.K),
            Arguments.of("A", CardNumber.A)
        );
    }

    @ParameterizedTest
    @DisplayName("카드 숫자 동일 확인 테스트")
    @MethodSource("testNumberInstanceSample")
    void cardNumberInstanceTest(String number, CardNumber expected) {
        CardNumber cardNumber = CardNumber.matchByNumber(number);
        assertThat(cardNumber).isEqualTo(expected);
    }
}
