package model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import model.card.Card;
import model.card.Rank;
import model.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParticipantTest {
    Participant participant;

    @BeforeEach
    void setUp() {
        participant = Player.of("pobi");
    }

    @Test
    void 카드를_뽑으면_현재_카드_목록에_추가한다() {
        // given
        Card card = Card.of(Suit.SPADE, Rank.THREE);

        // when
        List<Card> cards = participant.receive(card);

        // then
        Assertions.assertThat(cards.getFirst()).isEqualTo(card);
    }

    @Test
    void 참가자의_점수가_버스트인지_확인하는_테스트() {
        // given
        Participant jason = Player.of("jason");
        Participant gump = Player.of("gump");

        Card card1 = Card.of(Suit.SPADE, Rank.TEN);
        Card card2 = Card.of(Suit.SPADE, Rank.NINE);
        Card card3 = Card.of(Suit.SPADE, Rank.EIGHT);

        // when
        jason.receive(card1);
        jason.receive(card2);
        jason.receive(card3);
        gump.receive(card1);
        gump.receive(card2);

        // then
        assertThat(jason.isBust()).isTrue();
        assertThat(gump.isBust()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideCard")
    void 참가자의_현재_점수를_계산한다(List<Card> cards, int expected) {
        // given
        for (Card card : cards) {
            participant.receive(card);
        }

        // when
        int score = participant.calculateScore();

        // then
        assertThat(score).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCard() {
        return Stream.of(
                Arguments.of(
                        List.of(Card.of(Suit.SPADE, Rank.ACE), Card.of(Suit.SPADE, Rank.SIX)), 17),
                Arguments.of(
                        List.of(Card.of(Suit.SPADE, Rank.TEN), Card.of(Suit.SPADE, Rank.ACE)), 21),
                Arguments.of(
                        List.of(Card.of(Suit.SPADE, Rank.SIX), Card.of(Suit.SPADE, Rank.EIGHT),
                                Card.of(Suit.SPADE, Rank.ACE)), 15),
                Arguments.of(
                        List.of(Card.of(Suit.SPADE, Rank.TEN), Card.of(Suit.SPADE, Rank.JACK),
                                Card.of(Suit.SPADE, Rank.ACE)), 21),
                Arguments.of(
                        List.of(Card.of(Suit.SPADE, Rank.ACE), Card.of(Suit.SPADE, Rank.TWO),
                                Card.of(Suit.SPADE, Rank.THREE), Card.of(Suit.SPADE, Rank.ACE)), 17),
                Arguments.of(
                        List.of(Card.of(Suit.SPADE, Rank.ACE), Card.of(Suit.SPADE, Rank.ACE),
                                Card.of(Suit.SPADE, Rank.ACE), Card.of(Suit.SPADE, Rank.ACE)), 14)
        );
    }
}
