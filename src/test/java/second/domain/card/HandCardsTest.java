package second.domain.card;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class HandCardsTest {
    @Test
    void 생성자_테스트() {
        HandCards handCards = new HandCards(Collections.emptyList());
        assertThat(handCards).isInstanceOf(HandCards.class);
    }

    @Test
    void drawCard() {
        HandCards handCards = new HandCards(new ArrayList<>());

        handCards.drawCard(new CardDeck());
        List<Card> result = handCards.getCards();

        assertThat(result.size()).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("generateCards")
    void 순수한합구하기테스트(Rank[] ranks, int expectedScore) {
        HandCards handCards = new HandCards(parseNumbersToCards(ranks));

        assertThat(handCards.calculateDefaultSum()).isEqualTo(expectedScore);
    }

    @Test
    void 에이스가지는지테스트() {
        HandCards handCardsWithAce = new HandCards(parseNumbersToCards(Rank.ACE, Rank.TWO, Rank.THREE));
        HandCards handCardsWithoutAce = new HandCards(parseNumbersToCards(Rank.TWO, Rank.THREE, Rank.FOUR));

        assertThat(handCardsWithAce.hasAce()).isTrue();
        assertThat(handCardsWithoutAce.hasAce()).isFalse();
    }

    private static Stream<Arguments> generateCards() {
        return Stream.of(
                Arguments.of(new Rank[]{Rank.TWO, Rank.THREE}, 5),
                Arguments.of(new Rank[]{Rank.THREE, Rank.SIX, Rank.K}, 19),
                Arguments.of(new Rank[]{Rank.ACE, Rank.TWO, Rank.THREE}, 6),
                Arguments.of(new Rank[]{Rank.ACE, Rank.SEVEN, Rank.NINE}, 17)
        );
    }

    private static List<Card> parseNumbersToCards(Rank... input) {
        return Arrays.stream(input)
                .map(i -> Card.of(i, Suit.CLOVER))
                .collect(toList());
    }
}
