package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Emblem;
import domain.card.Grade;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    @Test
    void 카드를_받으면_손패의_카드_수가_늘어난다() {
        // given
        Deck deck = new Deck();
        Hand hand = new Hand();
        Card card = deck.drawCard();
        // when
        hand.receiveCard(card);
        // then
        assertThat(hand.getCards().size()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource(value = {"FIVE, TWO, 7", "ACE, FOUR, 15"})
    void 손패에_있는_카드의_합을_계산한다(Grade card1Value, Grade card2Value, int result) {
        // given
        Hand hand = new Hand();

        hand.receiveCard(new Card(Emblem.CLOVER, card1Value));
        hand.receiveCard(new Card(Emblem.CLOVER, card2Value));
        // when
        int score = hand.calculate();
        // then
        assertThat(score).isEqualTo(result);
    }

    @ParameterizedTest
    @MethodSource("handCalculateTestCases")
    void 손패에_있는_카드의_합을_계산한다(List<Grade> grades, int expected) {
        // given
        Hand hand = new Hand();
        for (Grade grade : grades) {
            hand.receiveCard(new Card(Emblem.CLOVER, grade));
        }

        // when
        int score = hand.calculate();

        // then
        assertThat(score).isEqualTo(expected);
    }

    static Stream<Arguments> handCalculateTestCases() {
        return Stream.of(
                Arguments.of(List.of(Grade.FIVE, Grade.TWO), 7),
                Arguments.of(List.of(Grade.ACE, Grade.FOUR), 15),
                Arguments.of(List.of(Grade.ACE, Grade.ACE, Grade.TEN, Grade.NINE), 21),
                Arguments.of(List.of(Grade.JACK, Grade.FIVE, Grade.ACE, Grade.FOUR), 20)
        );
    }
}
