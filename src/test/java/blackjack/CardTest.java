package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    private static Stream<Arguments> CardProvider() {
        return Stream.of(
                Arguments.of(new Card(Symbol.HEART, Type.ACE), "A하트"),
                Arguments.of(new Card(Symbol.HEART, Type.KING), "K하트"),
                Arguments.of(new Card(Symbol.CLOVER, Type.FIVE), "5클로버"),
                Arguments.of(new Card(Symbol.DIAMOND, Type.QUEEN), "Q다이아몬드"),
                Arguments.of(new Card(Symbol.SPADE, Type.JACK), "J스페이드")
        );
    }

    @DisplayName("타입과 심볼로 이루어진 카드 네이밍 확인")
    @ParameterizedTest
    @MethodSource("CardProvider")
    void cardNamingByToStringTest(Card card, String expected) {
        assertThat(card.toString()).isEqualTo(expected);
    }
}
