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
    @DisplayName("카드 숫자 포장 값 매칭 테스트")
    @MethodSource("testNumberSample")
    void cardNumberTest(String number, int value) {
        CardNumber cardNumber = CardNumber.from(number);

        int numberPoint = cardNumber.getValue();

        assertThat(numberPoint).isEqualTo(value);
    }

    private static Stream<Arguments> testNumberInstanceSample() {
        return Stream.of(
            Arguments.of("5", CardNumber.from("5")),
            Arguments.of("10", CardNumber.from("10")),
            Arguments.of("K", CardNumber.from("K")),
            Arguments.of("A", CardNumber.from("A"))
        );
    }

    @ParameterizedTest
    @DisplayName("카드 숫자 동일 인스턴스 확인 테스트")
    @MethodSource("testNumberInstanceSample")
    void cardNumberInstanceTest(String number, CardNumber cardTargetNumber) {
        CardNumber cardNumber = CardNumber.from(number);

        assertThat(cardNumber).isEqualTo(cardTargetNumber);
    }
}
