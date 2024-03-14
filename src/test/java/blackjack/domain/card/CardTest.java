package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @DisplayName("올바르게 카드 시그니처를 반환한다.")
    @Test
    void getSignatureTest() {
        Number number = Number.EIGHT;
        Shape shape = Shape.CLOVER;
        Card card = Card.from(number, shape);
        assertThat(card.getSignature()).isEqualTo("8클로버");
    }

    @DisplayName("에이스를 판별한다.")
    @ParameterizedTest
    @MethodSource("provideCardWithIsAce")
    void isAceTest(Card card, boolean isAce) {
        assertThat(card.isAce()).isEqualTo(isAce);
    }

    private static Stream<Arguments> provideCardWithIsAce() {
        return Stream.of(
                Arguments.of(Card.from(Number.ACE, Shape.HEART), true),
                Arguments.of(Card.from(Number.FIVE, Shape.CLOVER), false),
                Arguments.of(Card.from(Number.QUEEN, Shape.DIAMOND), false),
                Arguments.of(Card.from(Number.ACE, Shape.SPADE), true)
        );
    }
}
