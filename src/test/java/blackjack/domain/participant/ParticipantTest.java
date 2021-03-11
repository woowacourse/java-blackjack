package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Shape;
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
    private static final Card TWO_DIAMOND = new Card(CardNumber.TWO, Shape.DIAMOND);
    private static final Card JACK_SPADE = new Card(CardNumber.JACK, Shape.SPADE);
    private static final Card THREE_HEART = new Card(CardNumber.THREE, Shape.HEART);
    private static final Card ACE_CLOVER = new Card(CardNumber.ACE, Shape.CLOVER);
    private Participant participant;

    private static Stream<Arguments> provideCardsAndExpectedResult() {
        return Stream.of(
                Arguments.of(Arrays.asList(ACE_CLOVER, ACE_CLOVER), 12),
                Arguments.of(Arrays.asList(TWO_DIAMOND, JACK_SPADE, THREE_HEART, ACE_CLOVER), 16),
                Arguments.of(Arrays.asList(TWO_DIAMOND, JACK_SPADE, THREE_HEART, ACE_CLOVER, ACE_CLOVER), 17),
                Arguments.of(Arrays.asList(JACK_SPADE, JACK_SPADE, JACK_SPADE), 30)
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
    @DisplayName("ACE 개수 세기")
    void countAce() {
        participant.addCard(ACE_CLOVER);
        participant.addCard(ACE_CLOVER);
        participant.addCard(ACE_CLOVER);
        assertThat(participant.countAce()).isEqualTo(3);
    }

    @ParameterizedTest
    @DisplayName("주어진 카드들 점수 합 확인")
    @MethodSource("provideCardsAndExpectedResult")
    void calculateResultTest(List<Card> cards, int expectedResult) {
        for (Card card : cards) {
            participant.addCard(card);
        }

        assertThat(participant.calculateCardsScoreResult()).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("현재 카드 값이 21을 넘지 않을 때")
    void notExceedBlackjackNumber() {
        participant.addCard(JACK_SPADE);
        participant.addCard(JACK_SPADE);
        participant.addCard(ACE_CLOVER);
        assertFalse(participant.isBust());
    }

    @Test
    @DisplayName("현재 카드 값이 21을 넘을 때")
    void exceedBlackjackNumber() {
        participant.addCard(JACK_SPADE);
        participant.addCard(JACK_SPADE);
        participant.addCard(JACK_SPADE);
        assertTrue(participant.isBust());
    }
}