package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardTest {

    @ParameterizedTest(name = "값 객체 비교 - 성공")
    @MethodSource("equals_success_testcase")
    void equals_success(Suit suit, Rank rank) {
        Card card1 = new Card(suit, rank);
        Card card2 = new Card(suit, rank);

        assertThat(card1).isEqualTo(card2);
        assertThat(card1).hasSameHashCodeAs(card2);
    }

    private static Stream<Arguments> equals_success_testcase() {
        return Stream.of(
                Arguments.of(Suit.SPADE, Rank.ACE),
                Arguments.of(Suit.SPADE, Rank.KING),
                Arguments.of(Suit.DIAMOND, Rank.ACE),
                Arguments.of(Suit.DIAMOND, Rank.KING),
                Arguments.of(Suit.HEART, Rank.ACE),
                Arguments.of(Suit.HEART, Rank.KING),
                Arguments.of(Suit.CLOVER, Rank.ACE),
                Arguments.of(Suit.CLOVER, Rank.KING)
        );
    }

    @ParameterizedTest(name = "값 객체 비교 - 실패")
    @MethodSource("equals_fail_testcase")
    void equals_fail(Suit suit, Rank rank) {
        Card card = new Card(suit, rank);

        assertThat(card).isNotEqualTo(new Card(Suit.SPADE, Rank.ACE));
    }

    private static Stream<Arguments> equals_fail_testcase() {
        return Stream.of(
                Arguments.of(Suit.SPADE, Rank.TWO),
                Arguments.of(Suit.CLOVER, Rank.ACE),
                Arguments.of(Suit.CLOVER, Rank.TWO)
        );
    }
}