package domain.card;

import domain.HitThreshold;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static domain.MockCard.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    private Cards myCards;
    private Cards dealerCards;

    @BeforeEach
    void setUp() {
        myCards = new Cards(HitThreshold.PLAYER_THRESHOLD);
        dealerCards = new Cards(HitThreshold.DEALER_THRESHOLD);
    }

    @ParameterizedTest
    @DisplayName("플레이어카드가 더 받을수 있는지 테스트")
    @CsvSource(value = {"SPADE,ACE,HEART,NINE,true", "DIAMOND,ACE,CLUB,JACK,false"})
    void CanReceiveCard(Suit firstSuit, Face firstFace, Suit secondSuit, Face secondFace, boolean expected) {
        myCards.add(new Card(firstSuit, firstFace));
        myCards.add(new Card(secondSuit, secondFace));

        assertThat(myCards.canAddCard()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("딜러카드가 더 받을수 있는지 테스트")
    @CsvSource(value = {"SPADE,ACE,HEART,FIVE,true", "DIAMOND,ACE,CLUB,SIX,false"})
    void CanReceiveCard2(Suit firstSuit, Face firstFace, Suit secondSuit, Face secondFace, boolean expected) {
        dealerCards.add(new Card(firstSuit, firstFace));
        dealerCards.add(new Card(secondSuit, secondFace));

        assertThat(dealerCards.canAddCard()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("카드점수 합 구하기")
    @MethodSource("provideSumAndCards")
    void calculateSum(int expected, List<Card> cards) {
        for (Card card : cards) {
            myCards.add(card);
        }
        assertThat(myCards.calculateSum()).isEqualTo(expected);
    }

    public static Stream<Arguments> provideSumAndCards() {
        return Stream.of(
                Arguments.of(11, List.of(CLUB_ACE_CARD)),
                Arguments.of(12, List.of(CLUB_ACE_CARD, CLUB_ACE_CARD)),
                Arguments.of(20, List.of(CLUB_ACE_CARD, SPADE_NINE_CARD)),
                Arguments.of(21, List.of(CLUB_ACE_CARD, HEART_TEN_CARD)),
                Arguments.of(21, List.of(CLUB_ACE_CARD, HEART_TEN_CARD, HEART_TEN_CARD)));
    }
}
