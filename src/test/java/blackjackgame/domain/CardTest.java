package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CardTest {
    static Stream<Arguments> cardDummy() {
        return Stream.of(
                Arguments.arguments(Symbol.HEART, CardValue.FIVE),
                Arguments.arguments(Symbol.SPADE, CardValue.ACE),
                Arguments.arguments(Symbol.DIAMOND, CardValue.JACK),
                Arguments.arguments(Symbol.HEART, CardValue.TWO)
        );
    }

    @DisplayName("생성된 카드의 숫자가 생성자에 들어간 숫자와 일치하는지 확인한다")
    @ParameterizedTest()
    @MethodSource("cardDummy")
    void Should_ReturnSameValue_When_MakeCard(Symbol symbol, CardValue cardValue) {
        Card card = new Card(symbol, cardValue);
        assertThat(card.getScore()).isEqualTo(cardValue.getScore());
    }

    @DisplayName("생성된 카드의 문양이 생성자에 들어간 문양과 일치하는지 확인한다")
    @ParameterizedTest()
    @MethodSource("cardDummy")
    void Should_ReturnSameSymbol_When_MakeCard(Symbol symbol, CardValue cardValue) {
        Card card = new Card(symbol, cardValue);
        assertThat(card.getSymbol()).isEqualTo(symbol.getSymbol());
    }
}
