package domain.card;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

@DisplayName("Hand는 ")
class HandTest {
    @Test
    @DisplayName("비어있는 Hand를 나타낼 수 있다.")
    void createHandTest() {
        assertDoesNotThrow(() -> new Hand());
    }

    @Test
    @DisplayName("카드를 한 장 받을 수 있다.")
    void addTest() {
        Card card = CloverCard.FOUR;
        Hand hand = new Hand();

        assertDoesNotThrow(() -> hand.add(card));
    }

    @Test
    @DisplayName("가진 카드 숫자의 합산을 계산할 수 있다.")
    void sumTest() {
        Hand hand = new Hand(List.of(CloverCard.FOUR, CloverCard.FIVE));

        assertThat(hand.calculateScore()).isEqualTo(new Score(9));
    }

    @ParameterizedTest
    @MethodSource("bustHandCase")
    @DisplayName("bust 인지 판단할 수 있다.")
    void isBustTest(List<Card> cards, boolean expected) {
        Hand hand = new Hand(cards);

        assertThat(hand.isBust()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("calculateScoreWithAceCase")
    @DisplayName("에이스를 포함하며 21 초과 시 에이스를 1점으로 계산한다.")
    void calculateScoreWithAceTest(List<Card> cards, Score expected) {
        Hand hand = new Hand(cards);

        assertThat(hand.calculateScore()).isEqualTo(expected);
    }

    static Stream<Arguments> calculateScoreWithAceCase() {
        return Stream.of(
                Arguments.of(List.of(CloverCard.ACE, CloverCard.SEVEN,
                        CloverCard.EIGHT), new Score(16)),
                Arguments.of(List.of(CloverCard.ACE, CloverCard.ACE,
                        CloverCard.EIGHT), new Score(20)),
                Arguments.of(List.of(CloverCard.ACE, CloverCard.ACE, CloverCard.TEN,
                        CloverCard.NINE), new Score(21))
        );
    }

    static Stream<Arguments> bustHandCase() {
        return Stream.of(
                Arguments.of(List.of(CloverCard.KING, CloverCard.QUEEN, CloverCard.JACK), true),
                Arguments.of(List.of(CloverCard.KING, CloverCard.QUEEN, CloverCard.ACE), false),
                Arguments.of(List.of(CloverCard.TWO, CloverCard.ACE), false),
                Arguments.of(List.of(CloverCard.KING, CloverCard.NINE, CloverCard.ACE, HeartCard.ACE), false),
                Arguments.of(List.of(CloverCard.KING, CloverCard.ACE), false),
                Arguments.of(List.of(HeartCard.ACE, CloverCard.ACE), false),
                Arguments.of(List.of(HeartCard.ACE, SpadeCard.ACE, CloverCard.ACE, DiamondCard.ACE), false)
        );
    }
}
