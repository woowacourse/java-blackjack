package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class CardTest {

    @Test
    @DisplayName("카드 생성 테스트")
    void createCard() {
        Card card = new Card(Symbol.SPADE, Number.ACE);

        assertThat(card).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("generateNumbers")
    @DisplayName("카드의 숫자에 해당하는 값을 확인한다.")
    void getValueInCard(Number number, int expectedValue) {
        Card card = new Card(Symbol.SPADE, number);

        assertThat(card.getValue()).isEqualTo(expectedValue);
    }

    static Stream<Arguments> generateNumbers() {
        return Stream.of(
                Arguments.of(Number.ACE, 11),
                Arguments.of(Number.TWO, 2),
                Arguments.of(Number.THREE, 3),
                Arguments.of(Number.FOUR, 4),
                Arguments.of(Number.FIVE, 5),
                Arguments.of(Number.SIX, 6),
                Arguments.of(Number.SEVEN, 7),
                Arguments.of(Number.EIGHT, 8),
                Arguments.of(Number.NINE, 9),
                Arguments.of(Number.TEN, 10),
                Arguments.of(Number.JACK, 10),
                Arguments.of(Number.QUEEN, 10),
                Arguments.of(Number.KING, 10)
        );
    }
}
