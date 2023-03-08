package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CardNumberTest {
    @ParameterizedTest
    @MethodSource("getValueDummy")
    @DisplayName("번호의 값을 가져온다.")
    void getValue(final CardNumber cardNumber, final int expectedValue) {
        assertThat(cardNumber.getValue()).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("getNumberDummy")
    @DisplayName("번호의 기호를 가져온다.")
    void getNumber(final CardNumber cardNumber, final String expectedNumber) {
        assertThat(cardNumber.getNumber()).isEqualTo(expectedNumber);
    }

    static Stream<Arguments> getValueDummy() {
        return Stream.of(
                Arguments.arguments(CardNumber.ACE, 1),
                Arguments.arguments(CardNumber.TWO, 2),
                Arguments.arguments(CardNumber.THREE, 3),
                Arguments.arguments(CardNumber.FOUR, 4),
                Arguments.arguments(CardNumber.FIVE, 5),
                Arguments.arguments(CardNumber.SIX, 6),
                Arguments.arguments(CardNumber.SEVEN, 7),
                Arguments.arguments(CardNumber.EIGHT, 8),
                Arguments.arguments(CardNumber.NINE, 9),
                Arguments.arguments(CardNumber.TEN, 10),
                Arguments.arguments(CardNumber.KING, 10),
                Arguments.arguments(CardNumber.QUEEN, 10),
                Arguments.arguments(CardNumber.JACK, 10)
        );
    }

    static Stream<Arguments> getNumberDummy() {
        return Stream.of(
                Arguments.arguments(CardNumber.ACE, "A"),
                Arguments.arguments(CardNumber.TWO, "2"),
                Arguments.arguments(CardNumber.THREE, "3"),
                Arguments.arguments(CardNumber.FOUR, "4"),
                Arguments.arguments(CardNumber.FIVE, "5"),
                Arguments.arguments(CardNumber.SIX, "6"),
                Arguments.arguments(CardNumber.SEVEN, "7"),
                Arguments.arguments(CardNumber.EIGHT, "8"),
                Arguments.arguments(CardNumber.NINE, "9"),
                Arguments.arguments(CardNumber.TEN, "10"),
                Arguments.arguments(CardNumber.KING, "K"),
                Arguments.arguments(CardNumber.QUEEN, "Q"),
                Arguments.arguments(CardNumber.JACK, "J")
        );
    }
}
