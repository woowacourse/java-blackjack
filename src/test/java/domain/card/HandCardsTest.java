package domain.card;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class HandCardsTest {
    private static Stream<Arguments> generateCards() {
        return Stream.of(
                Arguments.of(new int[]{2, 3}, 5),
                Arguments.of(new int[]{3, 6, 11}, 19),
                Arguments.of(new int[]{1, 2, 3}, 6),
                Arguments.of(new int[]{1, 7, 9}, 17)
        );
    }

    private static List<Card> parseNumbersToCards(int... input) {
        return Arrays.stream(input)
                .mapToObj(i -> Card.of(Rank.of(i), Suit.CLOVER))
                .collect(toList());
    }

    @Test
    void 카드뽑기테스트() {
        List<Card> cards = new ArrayList<>();
        HandCards handCards = new HandCards(cards);
        handCards.drawCard(() -> Card.of(Rank.JACK, Suit.HEART));
        assertThat(handCards.getCards()).contains(Card.of(Rank.JACK, Suit.HEART));
    }

    @ParameterizedTest
    @MethodSource("generateCards")
    void 순수한합구하기테스트(int[] ranks, int expectedScore) {
        HandCards handCards = new HandCards(parseNumbersToCards(ranks));

        assertThat(handCards.calculateDefaultSum()).isEqualTo(expectedScore);
    }

    @Test
    void 에이스가지는지테스트() {
        HandCards handCardsWithAce = new HandCards(parseNumbersToCards(1, 2, 3));
        HandCards handCardsWithoutAce = new HandCards(parseNumbersToCards(2, 3, 4));

        assertThat(handCardsWithAce.hasAce()).isTrue();
        assertThat(handCardsWithoutAce.hasAce()).isFalse();
    }
}