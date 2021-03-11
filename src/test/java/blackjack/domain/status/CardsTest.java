package blackjack.domain.status;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import blackjack.domain.status.Cards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CardsTest {
    private static final Card TWO_DIAMONDS = new Card(Number.TWO, Suit.DIAMONDS);
    private static final Card JACK_SPADES = new Card(Number.JACK, Suit.SPADES);
    private static final Card THREE_HEARTS = new Card(Number.THREE, Suit.HEARTS);
    private static final Card ACE_CLUBS = new Card(Number.ACE, Suit.CLUBS);
    private Cards cards;

    private static Stream<Arguments> provideCardsAndExpectedResult() {
        return Stream.of(
                Arguments.of(Arrays.asList(ACE_CLUBS, ACE_CLUBS), 12),
                Arguments.of(Arrays.asList(TWO_DIAMONDS, JACK_SPADES, THREE_HEARTS, ACE_CLUBS), 16),
                Arguments.of(Arrays.asList(TWO_DIAMONDS, JACK_SPADES, THREE_HEARTS, ACE_CLUBS, ACE_CLUBS), 17),
                Arguments.of(Arrays.asList(JACK_SPADES, JACK_SPADES, JACK_SPADES), 30)
        );
    }

    @BeforeEach
    void setUp() {
        cards = new Cards();
    }


    @Test
    void calculateCardsTest() {
        cards.add(TWO_DIAMONDS);
        cards.add(JACK_SPADES);
        cards.add(THREE_HEARTS);

        assertThat(cards.sumCardsValue()).isEqualTo(15);
    }

    @Test
    @DisplayName("ACE 개수 세기")
    void countAce() {
        cards.add(ACE_CLUBS);
        cards.add(ACE_CLUBS);
        cards.add(ACE_CLUBS);
        assertThat(cards.countAce()).isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedResult")
    void calculateResultTest(List<Card> cards, int expectedResult) {
        for (Card card : cards) {
            this.cards.add(card);
        }

        assertThat(this.cards.calculateResult()).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("현재 카드 값이 21을 넘지 않을 때")
    void notExceedBlackjackNumber() {
        cards.add(JACK_SPADES);
        cards.add(JACK_SPADES);
        cards.add(ACE_CLUBS);
        assertFalse(cards.isBust());
    }

    @Test
    @DisplayName("현재 카드 값이 21을 넘을 때")
    void exceedBlackjackNumber() {
        cards.add(JACK_SPADES);
        cards.add(JACK_SPADES);
        cards.add(JACK_SPADES);
        assertTrue(cards.isBust());
    }


    @Test
    void subList() {
        cards.add(JACK_SPADES);
        cards.add(THREE_HEARTS);
        cards.add(ACE_CLUBS);
        assertThat(cards.subList(1, 3)).isEqualTo(new ArrayList<>(Arrays.asList(THREE_HEARTS, ACE_CLUBS)));
    }
}