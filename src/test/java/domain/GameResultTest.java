package domain;

import domain.card.Card;
import domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static domain.GameResult.*;
import static domain.MockCard.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    @ParameterizedTest
    @DisplayName("최종 승패 결과 반환")
    @MethodSource("provideResultAndCards")
    void calculateFinalResult(GameResult expected, List<Card> cardsPlayer, List<Card> cardsDealer) {
        final Cards myCards = new Cards(HitThreshold.PLAYER_THRESHOLD);
        for (Card card : cardsPlayer) {
            myCards.add(card);
        }

        final Cards dealerCards = new Cards(HitThreshold.DEALER_THRESHOLD);
        for (Card card : cardsDealer) {
            dealerCards.add(card);
        }

        assertThat(GameResult.getPlayerResult(myCards, dealerCards)).isEqualTo(expected);
    }

    public static Stream<Arguments> provideResultAndCards() {
        return Stream.of(
                Arguments.of(WIN,
                        List.of(CLUB_ACE_CARD, HEART_TEN_CARD),
                        List.of(HEART_TEN_CARD, HEART_TEN_CARD, HEART_TEN_CARD)),
                Arguments.of(WIN,
                        List.of(CLUB_ACE_CARD, HEART_TEN_CARD),
                        List.of(HEART_TEN_CARD, HEART_TEN_CARD)),
                Arguments.of(WIN,
                        List.of(CLUB_ACE_CARD, SPADE_NINE_CARD),
                        List.of(SPADE_NINE_CARD, SPADE_NINE_CARD)),
                Arguments.of(LOSE,
                        List.of(HEART_TEN_CARD, HEART_TEN_CARD, SPADE_NINE_CARD),
                        List.of(HEART_TEN_CARD, HEART_TEN_CARD, SPADE_NINE_CARD)),
                Arguments.of(LOSE,
                        List.of(CLUB_ACE_CARD, SPADE_NINE_CARD),
                        List.of(CLUB_ACE_CARD, HEART_TEN_CARD)),
                Arguments.of(LOSE,
                        List.of(HEART_TEN_CARD, SPADE_NINE_CARD),
                        List.of(CLUB_ACE_CARD, SPADE_NINE_CARD)),
                Arguments.of(DRAW,
                        List.of(CLUB_ACE_CARD, HEART_TEN_CARD),
                        List.of(CLUB_ACE_CARD, HEART_TEN_CARD)),
                Arguments.of(DRAW,
                        List.of(CLUB_ACE_CARD, SPADE_NINE_CARD),
                        List.of(CLUB_ACE_CARD, SPADE_NINE_CARD))
        );
    }
}
