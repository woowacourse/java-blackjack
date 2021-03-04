package blackjack.domain.card;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    
    @ParameterizedTest(name = "값 객체 비교")
    @MethodSource("equals_testcase")
    void equals(Suit suit, Rank rank) {
        Card firstCard = new Card(suit, rank);
        Card secondCard = new Card(suit, rank);
        
        assertThat(firstCard).isEqualTo(secondCard);
        assertThat(firstCard).hasSameHashCodeAs(secondCard);
    }
    
    private static Stream<Arguments> equals_testcase() {
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
    
    @Test
    void equals_fail() {
        Card firstCard = new Card(Suit.SPADE, Rank.ACE);
        Card secondCard = new Card(Suit.SPADE, Rank.TWO);
        
        assertThat(firstCard).isNotEqualTo(secondCard);
    }
}