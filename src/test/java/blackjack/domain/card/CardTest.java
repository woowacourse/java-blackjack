package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardTest {

    @ParameterizedTest(name = "값 객체 비교 - 성공")
    @MethodSource("equalsSuccessTestcase")
    void equalsSuccess(Suit suit, Denomination denomination) {
        Card card1 = new Card(suit, denomination);
        Card card2 = new Card(suit, denomination);

        assertThat(card1).isEqualTo(card2);
        assertThat(card1).hasSameHashCodeAs(card2);
    }

    private static Stream<Arguments> equalsSuccessTestcase() {
        return Stream.of(
                Arguments.of(Suit.SPADE, Denomination.ACE),
                Arguments.of(Suit.SPADE, Denomination.KING),
                Arguments.of(Suit.DIAMOND, Denomination.ACE),
                Arguments.of(Suit.DIAMOND, Denomination.KING),
                Arguments.of(Suit.HEART, Denomination.ACE),
                Arguments.of(Suit.HEART, Denomination.KING),
                Arguments.of(Suit.CLOVER, Denomination.ACE),
                Arguments.of(Suit.CLOVER, Denomination.KING)
        );
    }

    @ParameterizedTest(name = "값 객체 비교 - 실패")
    @MethodSource("equalsFailTestcase")
    void equalsFail(Suit suit, Denomination denomination) {
        Card card1 = new Card(suit, denomination);
        Card card2 = new Card(Suit.SPADE, Denomination.ACE);

        assertThat(card1).isNotEqualTo(card2);
        assertThat(card1.hashCode()).isNotEqualTo(card2.hashCode());
    }

    private static Stream<Arguments> equalsFailTestcase() {
        return Stream.of(
                Arguments.of(Suit.SPADE, Denomination.TWO),
                Arguments.of(Suit.CLOVER, Denomination.ACE),
                Arguments.of(Suit.CLOVER, Denomination.TWO)
        );
    }
}