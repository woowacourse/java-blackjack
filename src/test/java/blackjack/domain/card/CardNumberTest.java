package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardNumberTest {
    @ParameterizedTest
    @MethodSource("getNumbers")
    @DisplayName("21에 가장 가까운 수를 구한다")
    void sumCard(int total, List<CardNumber> cardNumbers) {
        int expected = CardNumber.sum(cardNumbers);

        assertThat(expected).isEqualTo(total);
    }

    static Stream<Arguments> getNumbers() {
        return Stream.of(
                Arguments.of(16, List.of(CardNumber.THREE, CardNumber.FIVE, CardNumber.EIGHT)),
                Arguments.of(30, List.of(CardNumber.KING, CardNumber.QUEEN, CardNumber.JACK)),

                Arguments.of(13, List.of(CardNumber.ACE, CardNumber.TWO)),
                Arguments.of(21, List.of(CardNumber.ACE, CardNumber.TEN)),
                Arguments.of(21, List.of(CardNumber.ACE, CardNumber.ACE, CardNumber.NINE)),
                Arguments.of(12, List.of(CardNumber.ACE, CardNumber.ACE, CardNumber.TEN)),

                Arguments.of(18, List.of(CardNumber.ACE, CardNumber.SEVEN, CardNumber.KING)),
                Arguments.of(20, List.of(CardNumber.ACE, CardNumber.ACE, CardNumber.EIGHT)),
                Arguments.of(21, List.of(CardNumber.ACE, CardNumber.ACE, CardNumber.ACE, CardNumber.EIGHT)),
                Arguments.of(21, List.of(CardNumber.ACE, CardNumber.ACE, CardNumber.NINE)),

                Arguments.of(12, List.of(CardNumber.ACE, CardNumber.ACE)),
                Arguments.of(13, List.of(CardNumber.ACE, CardNumber.ACE, CardNumber.ACE)),
                Arguments.of(14, List.of(CardNumber.ACE, CardNumber.ACE, CardNumber.ACE, CardNumber.ACE))
        );
    }
}
