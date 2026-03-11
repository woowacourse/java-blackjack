package model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import model.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParticipantTest {
    Participant participant;
    @Test
    void 참가자_생성_테스트() {
        // given
        // when
        String dealerName = "딜러";
        Participant participant1 = Dealer.of(dealerName);

        // then
        assertThat(participant1).isInstanceOf(Dealer.class);
    }

    @BeforeEach
    void setUp() {
         participant = Player.of("pobi", 10000);
    }

    @Test
    void 카드를_뽑으면_현재_카드_목록에_추가한다() {
        // given
        Card card = Card.of("스페이드", 3);

        // when
        List<Card> cards = participant.draw(card);

        // then
        Assertions.assertThat(cards.getFirst()).isEqualTo(card);
    }

    @Test
    void 참가자의_점수가_버스트인지_확인하는_테스트() {
        // given
        Participant jason = Player.of("jason", 10000);
        Participant gump = Player.of("gump", 10000);

        Card card1 = Card.of("스페이드", 10);
        Card card2 = Card.of("스페이드", 9);
        Card card3 = Card.of("스페이드", 8);

        // when
        jason.draw(card1);
        jason.draw(card2);
        jason.draw(card3);
        gump.draw(card1);
        gump.draw(card2);

        // then
        assertThat(jason.isBust()).isTrue();
        assertThat(gump.isBust()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideCard")
    void 참가자의_현재_점수를_계산한다(List<Card> cards, int expected) {
        // given
        for (Card card : cards) {
            participant.draw(card);
        }

        // when
        int score = participant.calculateScore();

        // then
        assertThat(score).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCard() {
        return Stream.of(
                Arguments.of(List.of(Card.of("스페이드", 11), Card.of("스페이드", 6)), 17),
                Arguments.of(List.of(Card.of("스페이드", 10), Card.of("스페이드", 11)), 21),
                Arguments.of(List.of(Card.of("스페이드", 6), Card.of("스페이드", 8), Card.of("스페이드", 11)), 15),
                Arguments.of(List.of(Card.of("스페이드", 10), Card.of("스페이드", 10), Card.of("스페이드", 11)), 21),
                Arguments.of(List.of(Card.of("스페이드", 11), Card.of("스페이드", 1), Card.of("스페이드", 2), Card.of("스페이드", 11)), 15),
                Arguments.of(List.of(Card.of("스페이드", 11), Card.of("스페이드", 11), Card.of("스페이드", 11), Card.of("스페이드", 11)), 14)

        );

    }
}