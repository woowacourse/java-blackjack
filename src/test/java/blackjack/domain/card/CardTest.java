package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardTest {

    @ParameterizedTest(name = "값 객체 비교")
    @MethodSource("equals_testcase")
    void equals(Suit suit, Rank rank) {
        Card card1 = new Card(suit, rank);
        Card card2 = new Card(suit, rank);

        assertThat(card1).isEqualTo(card2);
        assertThat(card1).hasSameHashCodeAs(card2);
    }

    private static Stream<Arguments> equals_testcase() {
        return Stream.of(
                Arguments.of(Suit.SPACE, Rank.ONE),
                Arguments.of(Suit.SPACE, Rank.KING),
                Arguments.of(Suit.DIAMOND, Rank.ONE),
                Arguments.of(Suit.DIAMOND, Rank.KING),
                Arguments.of(Suit.HEART, Rank.ONE),
                Arguments.of(Suit.HEART, Rank.KING),
                Arguments.of(Suit.CLOVER, Rank.ONE),
                Arguments.of(Suit.CLOVER, Rank.KING)
        );
    }

}