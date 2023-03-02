package blackjack.domain;

import static blackjack.domain.Rank.ACE;
import static blackjack.domain.Rank.EIGHT;
import static blackjack.domain.Rank.JACK;
import static blackjack.domain.Rank.KING;
import static blackjack.domain.Rank.SEVEN;
import static blackjack.domain.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class HandTest {

    @ParameterizedTest
    @MethodSource("playBlackjackSource")
    void 승패를_구한다(final List<Rank> ranks, final List<Rank> dealerRanks, final Result expectedResult) {
        final Hand hand = generateHand(ranks);
        final Hand dealerHand = generateHand(dealerRanks);

        Result result = hand.play(dealerHand);

        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> playBlackjackSource() {
        return Stream.of(
                Arguments.of(List.of(ACE, JACK), List.of(ACE, JACK), Result.DRAW),
                Arguments.of(List.of(ACE, JACK), List.of(JACK, JACK), Result.WIN),
                Arguments.of(List.of(ACE, JACK), List.of(JACK, SIX, JACK), Result.WIN),
                Arguments.of(List.of(JACK, JACK), List.of(JACK, ACE), Result.LOSE),
                Arguments.of(List.of(JACK, JACK, JACK), List.of(JACK, ACE), Result.LOSE),
                Arguments.of(List.of(JACK, JACK), List.of(JACK, EIGHT), Result.WIN),
                Arguments.of(List.of(JACK, JACK), List.of(JACK, JACK), Result.DRAW),
                Arguments.of(List.of(JACK, SEVEN), List.of(JACK, JACK), Result.LOSE),
                Arguments.of(List.of(JACK, SEVEN), List.of(JACK, SIX, JACK), Result.WIN),
                Arguments.of(List.of(JACK, SEVEN, KING), List.of(JACK, SEVEN), Result.LOSE),
                Arguments.of(List.of(JACK, SIX, KING), List.of(JACK, SIX, KING), Result.DRAW)
        );
    }

    private Hand generateHand(List<Rank> ranks) {
        final Hand hand = new Hand();
        for (Rank rank : ranks) {
            hand.add(new Card(rank, Shape.SPADE));
        }
        return hand;
    }

    @Test
    void 카드를_넣는다() {
        final Hand hand = new Hand();

        hand.add(new Card(ACE, Shape.SPADE));

        assertThat(hand.getCardLetters()).containsExactly("A스페이드");
    }
}
