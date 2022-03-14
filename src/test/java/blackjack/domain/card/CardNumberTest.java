package blackjack.domain.card;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.THREE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardNumberTest {

    @Test
    @DisplayName("카드 숫자 13개를 반환할 수 있다.")
    void cardNumbers() {
        final List<CardNumber> cardNumbers = CardNumber.allNumbers();
        final int distinctCount = (int) cardNumbers.stream()
                .distinct()
                .count();
        assertThat(distinctCount).isEqualTo(13);
    }

    @ParameterizedTest
    @DisplayName("카드 숫자들을 받아, 21 이하의 최대 합을 반환한다. J, Q, K는 10으로 계산하고 A는 1 혹은 11로 계산할 수 있다.")
    @MethodSource("provideCardNumbersAndExpectedSum")
    void calculateScore(List<CardNumber> cardNumbers, int expected) {
        final int result = CardNumber.calculateScore(cardNumbers);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardNumbersAndExpectedSum() {
        return Stream.of(
                Arguments.of(Arrays.asList(THREE, SEVEN, TEN), 20),
                Arguments.of(Arrays.asList(A, TEN), 21),
                Arguments.of(Arrays.asList(A, A, TEN), 12)
        );
    }
}
