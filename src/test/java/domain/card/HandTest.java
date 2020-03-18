package domain.card;

import domain.card.cardDrawable.Hand;
import domain.result.Score;
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

class HandTest {
    private static Stream<Arguments> generateCards() {
        return Stream.of(
                Arguments.of(new String[]{"2", "3"}, 5),
                Arguments.of(new String[]{"3", "6", "Q"}, 19),
                Arguments.of(new String[]{"Ace", "2", "3"}, 16),
                Arguments.of(new String[]{"Ace", "7", "9"}, 17)
        );
    }

    private static List<Card> parseNumbersToCards(String... input) {
        return Arrays.stream(input)
                .map(i -> Card.of(Rank.of(i), Suit.CLOVER))
                .collect(toList());
    }

    @Test
    void 카드뽑기테스트() {
        List<Card> cards = new ArrayList<>();
        Hand hand = new Hand(cards);
        hand.drawCard(() -> Card.of(Rank.JACK, Suit.HEART));
        assertThat(hand.getCards()).contains(Card.of(Rank.JACK, Suit.HEART));
    }

    @ParameterizedTest
    @MethodSource("generateCards")
    void 점수계산테스트(String[] ranks, int expectedScore) {
        Hand hand = new Hand(parseNumbersToCards(ranks));

        assertThat(hand.calculateScore()).isEqualTo(new Score(expectedScore));
    }
}