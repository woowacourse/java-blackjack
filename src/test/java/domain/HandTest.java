package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.Emblem;
import domain.card.Grade;
import domain.participant.Hand;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    @Test
    void 카드를_받으면_손패의_카드_수가_늘어난다() {
        // given
        Hand hand = new Hand(List.of());
        Card expectedCard = new Card(Emblem.CLOVER, Grade.ACE);
        // when
        hand.receiveCard(expectedCard);
        // then
        assertAll(
                () -> assertThat(hand.getCards().size()).isEqualTo(1),
                () -> assertThat(hand.getCards().getFirst()).isEqualTo(expectedCard)
        );
    }

    @ParameterizedTest
    @MethodSource("handCalculateTestCases")
    void 손패에_있는_카드의_합을_계산한다(List<Card> cards, int expectedScore) {
        // given
        Hand hand = new Hand(cards);
        // when
        GameScore score = hand.calculateTotalScore();
        // then
        assertThat(score.getScore()).isEqualTo(expectedScore);
    }

    static Stream<Arguments> handCalculateTestCases() {
        return Stream.of(
                Arguments.of(cards(Grade.FIVE, Grade.TWO), 7),
                Arguments.of(cards(Grade.ACE, Grade.FOUR), 15),
                Arguments.of(cards(Grade.ACE, Grade.ACE), 12),
                Arguments.of(cards(Grade.ACE, Grade.ACE, Grade.NINE), 21),
                Arguments.of(cards(Grade.ACE, Grade.ACE, Grade.TEN, Grade.NINE), 21),
                Arguments.of(cards(Grade.JACK, Grade.FIVE, Grade.ACE, Grade.FOUR), 20)
        );
    }

    private static List<Card> cards(Grade... grades) {
        return Arrays.stream(grades)
                .map(grade -> new Card(Emblem.HEART, grade))
                .toList();
    }
}
