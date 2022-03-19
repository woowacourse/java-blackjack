package blackjack.domain.card;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.EIGHT;
import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardNumber.sum;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardNumberTest {

    @ParameterizedTest
    @MethodSource("getNumbers")
    @DisplayName("21에 가장 가까운 수를 구한다")
    void sumCard(int total, List<CardNumber> cardNumbers) {
        int expected = sum(cardNumbers);

        assertThat(expected).isEqualTo(total);
    }

    @Test
    @DisplayName("두 수의 합이 21(블랙잭)인지 확인한다.")
    void checkBlackjack() {
        assertThat(CardNumber.isBlackjack(List.of(ACE, TEN))).isTrue();
    }

    static Stream<Arguments> getNumbers() {
        return Stream.of(
            Arguments.of(16, List.of(THREE, FIVE, EIGHT)),
            Arguments.of(30, List.of(KING, QUEEN, JACK)),

            Arguments.of(13, List.of(ACE, TWO)),
            Arguments.of(21, List.of(ACE, TEN)),
            Arguments.of(21, List.of(ACE, ACE, NINE)),
            Arguments.of(12, List.of(ACE, ACE, TEN)),

            Arguments.of(18, List.of(ACE, SEVEN, KING)),
            Arguments.of(20, List.of(ACE, ACE, EIGHT)),
            Arguments.of(21, List.of(ACE, ACE, ACE, EIGHT)),
            Arguments.of(21, List.of(ACE, ACE, NINE)),

            Arguments.of(12, List.of(ACE, ACE)),
            Arguments.of(13, List.of(ACE, ACE, ACE)),
            Arguments.of(14, List.of(ACE, ACE, ACE, ACE))
        );
    }
}
