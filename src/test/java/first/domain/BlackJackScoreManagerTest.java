package first.domain;

import first.domain.card.Card;
import first.domain.card.Rank;
import first.domain.card.Suit;
import first.domain.card.possessable.HandCards;
import first.domain.score.BlackJackScoreManager;
import first.domain.score.Score;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class BlackJackScoreManagerTest {

    private static Stream<Arguments> generateCards() {
        return Stream.of(
                Arguments.of(new Rank[]{Rank.TWO, Rank.THREE}, 5),
                Arguments.of(new Rank[]{Rank.THREE, Rank.SIX, Rank.K}, 19),
                Arguments.of(new Rank[]{Rank.ACE, Rank.TWO, Rank.THREE}, 16),
                Arguments.of(new Rank[]{Rank.ACE, Rank.SEVEN, Rank.NINE}, 17)
        );
    }

    private static List<Card> parseNumbersToCards(Rank... input) {
        return Arrays.stream(input)
                .map(i -> Card.of(i, Suit.CLOVER))
                .collect(toList());
    }

    @ParameterizedTest
    @MethodSource("generateCards")
    void calculate(Rank[] numbers, int expectedScore) {
        HandCards handCards = new HandCards(parseNumbersToCards(numbers));

        assertThat(BlackJackScoreManager.calculate(handCards)).isEqualTo(new Score(expectedScore));
    }
}