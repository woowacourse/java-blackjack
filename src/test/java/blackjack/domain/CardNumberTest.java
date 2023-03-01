package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CardNumberTest {
    @ParameterizedTest
    @MethodSource("getMaxValueNear21Dummy")
    @DisplayName("카드 숫자 합이 21에 가장 가까운 값이 되도록 구한다.")
    void getMaxValueNear21(final List<CardNumber> cardNumbers, final int expectedValue) {
        int maxValue = CardNumber.getMaxValueNear21(cardNumbers);
        assertThat(maxValue).isEqualTo(expectedValue);
    }

    static Stream<Arguments> getMaxValueNear21Dummy() {
        return Stream.of(
                Arguments.arguments(List.of(
                        CardNumber.THREE,
                        CardNumber.FOUR), 7),
                Arguments.arguments(List.of(
                        CardNumber.ACE,
                        CardNumber.ACE,
                        CardNumber.NINE), 21),
                Arguments.arguments(List.of(
                        CardNumber.TWO,
                        CardNumber.ACE,
                        CardNumber.FOUR), 17),
                Arguments.arguments(List.of(
                        CardNumber.QUEEN,
                        CardNumber.JACK,
                        CardNumber.ACE,
                        CardNumber.ACE), 22)
                );
    }
}