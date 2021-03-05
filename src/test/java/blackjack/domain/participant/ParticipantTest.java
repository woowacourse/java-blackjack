package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParticipantTest {
    private static final Card TWO_DIAMONDS = new Card(Number.TWO, Suit.DIAMONDS);
    private static final Card JACK_SPADES = new Card(Number.JACK, Suit.SPADES);
    private static final Card THREE_HEARTS = new Card(Number.THREE, Suit.HEARTS);
    private static final Card ACE_CLUBS = new Card(Number.ACE, Suit.CLUBS);
    private Participant participant;

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
        participant = new Player("wannte");
    }

    @Test
    @DisplayName("플레이어 카드 추가")
    void addCard() {
        Card card = Deck.draw();
        participant.addCard(card);
        assertThat(participant.getCards()).containsExactly(card);
    }

    @Test
    void calculateCardsTest() {
        participant.addCard(TWO_DIAMONDS);
        participant.addCard(JACK_SPADES);
        participant.addCard(THREE_HEARTS);

        assertThat(participant.sumCardsValue()).isEqualTo(15);
    }

    @Test
    @DisplayName("ACE 개수 세기")
    void countAce() {
        participant.addCard(ACE_CLUBS);
        participant.addCard(ACE_CLUBS);
        participant.addCard(ACE_CLUBS);
        assertThat(participant.countAce()).isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedResult")
    void calculateResultTest(List<Card> cards, int expectedResult) {
        for (Card card : cards) {
            participant.addCard(card);
        }

        assertThat(participant.calculateResult()).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("현재 카드 값이 21을 넘지 않을 때")
    void notExceedBlackjackNumber() {
        participant.addCard(JACK_SPADES);
        participant.addCard(JACK_SPADES);
        participant.addCard(ACE_CLUBS);
        assertFalse(participant.isBust());
    }

    @Test
    @DisplayName("현재 카드 값이 21을 넘을 때")
    void exceedBlackjackNumber() {
        participant.addCard(JACK_SPADES);
        participant.addCard(JACK_SPADES);
        participant.addCard(JACK_SPADES);
        assertTrue(participant.isBust());
    }
}