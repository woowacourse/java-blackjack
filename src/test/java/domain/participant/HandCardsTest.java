package domain.participant;

import domain.card.Card;
import domain.card.TrumpNumber;
import domain.card.TrumpSuit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HandCardsTest {
    @ParameterizedTest
    @MethodSource("provideHandCardsData")
    void ace_값_계산_테스트(List<Card> cards, int exceptResult) {
        HandCards handCards = new HandCards();

        for (Card card : cards) {
            handCards.drawCard(card);
        }

        assertThat(handCards.calculateCardsScore()).isEqualTo(exceptResult);
    }

    static Stream<Arguments> provideHandCardsData() {
        return Stream.of(
                Arguments.of(List.of(new Card(TrumpSuit.HEART, TrumpNumber.NINE), new Card(TrumpSuit.CLUB, TrumpNumber.NINE)), 18), // ACE 없는 경우
                Arguments.of(List.of(new Card(TrumpSuit.HEART, TrumpNumber.JACK), new Card(TrumpSuit.CLUB, TrumpNumber.ACE)), 21), // ACE가 11으로 처리되어야 하는 경우
                Arguments.of(List.of(new Card(TrumpSuit.HEART, TrumpNumber.SEVEN), new Card(TrumpSuit.CLUB, TrumpNumber.NINE),
                        new Card(TrumpSuit.CLUB, TrumpNumber.ACE)), 17), // ACE가 1로 처리되어야 하는 경우
                Arguments.of(List.of(new Card(TrumpSuit.HEART, TrumpNumber.ACE), new Card(TrumpSuit.CLUB, TrumpNumber.ACE),
                        new Card(TrumpSuit.CLUB, TrumpNumber.NINE)), 21), // ACE가 두 개이며, 하나는 11, 하나는 1로 처리되어야 하는 경우
                Arguments.of(List.of(new Card(TrumpSuit.HEART, TrumpNumber.ACE), new Card(TrumpSuit.CLUB, TrumpNumber.ACE),
                        new Card(TrumpSuit.CLUB, TrumpNumber.NINE)), 21)// ACE가 두 개이며, 둘 다 1로 처리되어야 하는 경우
        );
    }
}