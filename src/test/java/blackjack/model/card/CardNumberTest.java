package blackjack.model.card;

import static blackjack.model.card.CardNumber.ACE;
import static blackjack.model.card.CardNumber.EIGHT;
import static blackjack.model.card.CardNumber.FOUR;
import static blackjack.model.card.CardNumber.JACK;
import static blackjack.model.card.CardNumber.KING;
import static blackjack.model.card.CardNumber.SIX;
import static blackjack.model.card.CardNumber.THREE;
import static blackjack.model.card.CardNumber.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardNumberTest {

    @ParameterizedTest
    @MethodSource("generateCardNumbers")
    void scoreTest(List<CardNumber> cardNumbers, int expectedScore) {
        assertThat(calculateScore(cardNumbers)).isEqualTo(expectedScore);
    }

    static Stream<Arguments> generateCardNumbers() {
        return Stream.of(
                Arguments.of(Arrays.asList(ACE, JACK), 21),
                Arguments.of(Arrays.asList(ACE, ACE), 12),
                Arguments.of(Arrays.asList(TWO, EIGHT), 10),
                Arguments.of(Arrays.asList(ACE, JACK, KING), 21),
                Arguments.of(Arrays.asList(KING, JACK, SIX), 26),
                Arguments.of(Arrays.asList(THREE, FOUR), 7)
        );
    }

    static int calculateScore(List<CardNumber> cardNumbers) {
        return CardNumber.calculateScore(cardNumbers);
    }
}
