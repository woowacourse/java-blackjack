package domain;

import static domain.deck.Rank.ACE;
import static domain.deck.Rank.EIGHT;
import static domain.deck.Rank.FIVE;
import static domain.deck.Rank.FOUR;
import static domain.deck.Rank.JACK;
import static domain.deck.Rank.KING;
import static domain.deck.Rank.NINE;
import static domain.deck.Rank.QUEEN;
import static domain.deck.Rank.SEVEN;
import static domain.deck.Rank.SIX;
import static domain.deck.Rank.TEN;
import static domain.deck.Rank.THREE;
import static domain.deck.Rank.TWO;
import static domain.deck.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import domain.deck.Card;
import domain.deck.Rank;
import fixture.CardFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardTest {
    @ParameterizedTest
    @MethodSource("cardRankArguments")
    @DisplayName("카드의 기본 점수를 가져올 수 있다.")
    void testCardScore(Rank rank, int expectedScore) {
        // given
        Card card = CardFixture.of(rank, HEART);
        // when
        int score = card.score();
        // then
        assertThat(score).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("카드가 에이스인지 판단할 수 있다.")
    void testIsAce() {
        // given
        Card card = CardFixture.of(ACE, HEART);
        // when
        boolean isAce = card.isAce();
        // then
        assertThat(isAce).isTrue();
    }

    private static Stream<Arguments> cardRankArguments() {
        return Stream.of(
                Arguments.arguments(ACE, 1),
                Arguments.arguments(TWO, 2),
                Arguments.arguments(THREE, 3),
                Arguments.arguments(FOUR, 4),
                Arguments.arguments(FIVE, 5),
                Arguments.arguments(SIX, 6),
                Arguments.arguments(SEVEN, 7),
                Arguments.arguments(EIGHT, 8),
                Arguments.arguments(NINE, 9),
                Arguments.arguments(TEN, 10),
                Arguments.arguments(JACK, 10),
                Arguments.arguments(QUEEN, 10),
                Arguments.arguments(KING, 10)
        );
    }
}
