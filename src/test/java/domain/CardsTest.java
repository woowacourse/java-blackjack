package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CanAddCardThreshold;
import domain.card.Card;
import domain.card.FACE;
import domain.card.SUIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardsTest {

    @ParameterizedTest
    @DisplayName("플레이어카드가 더 받을수 있는지 테스트")
    @CsvSource(value = {"SPADE,ACE,HEART,NINE,true", "DIAMOND,ACE,CLUB,JACK,false"})
    void CanReceiveCard(SUIT firstSuit, FACE firstFace, SUIT secondSuit, FACE secondFace, boolean expected) {
        //given
        Cards cards = new Cards(CanAddCardThreshold.PLAYER_THRESHOLD);
        cards.add(new Card(firstSuit, firstFace));
        cards.add(new Card(secondSuit, secondFace));

        assertThat(cards.canAddCard()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("딜러카드가 더 받을수 있는지 테스트")
    @CsvSource(value = {"SPADE,ACE,HEART,FIVE,true", "DIAMOND,ACE,CLUB,SIX,false"})
    void CanReceiveCard2(SUIT firstSuit, FACE firstFace, SUIT secondSuit, FACE secondFace, boolean expected) {
        //given
        Cards cards = new Cards(CanAddCardThreshold.DEALER_THRESHOLD);
        cards.add(new Card(firstSuit, firstFace));
        cards.add(new Card(secondSuit, secondFace));

        assertThat(cards.canAddCard()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("카드점수 합 구하기")
    @CsvSource(value = {"SPADE,ACE,HEART,NINE,20", "DIAMOND,ACE,CLUB,JACK,21"})
    void calculateSum(SUIT firstSuit, FACE firstFace, SUIT secondSuit, FACE secondFace, int expected) {
        Cards cards = new Cards(CanAddCardThreshold.PLAYER_THRESHOLD);
        cards.add(new Card(firstSuit, firstFace));
        cards.add(new Card(secondSuit, secondFace));

        assertThat(cards.calculateSum()).isEqualTo(expected);
    }

//    @ParameterizedTest
//    @DisplayName("플레이어가 카드를 더 받을수 있는지 테스트")
//    @MethodSource("provideCard")
//    void calculateSum(int expected, Card... cards) {
//        Cards myCards = new Cards(CanAddCardThreshold.PLAYER_THRESHOLD);
//        for (Card card : cards) {
//            myCards.add(card);
//        }
//        assertThat(myCards.calculateSum()).isEqualTo(expected);
//    }
//
//    public static Stream<Arguments> provideCard() {
//        return Stream.of(
//                Arguments.of(11, new Card(SUIT.CLUB, FACE.ACE)),
//                Arguments.of(12, new Card(SUIT.CLUB, FACE.ACE),
//                        new Card(SUIT.CLUB, FACE.ACE)),
//                Arguments.of(13, new Card(SUIT.CLUB, FACE.ACE),
//                        new Card(SUIT.CLUB, FACE.ACE),
//                        new Card(SUIT.CLUB, FACE.ACE)),
//                Arguments.of(20, new Card(SUIT.CLUB, FACE.ACE),
//                        new Card(SUIT.CLUB, FACE.NINE)),
//                Arguments.of(20, new Card(SUIT.CLUB, FACE.TEN),
//                        new Card(SUIT.CLUB, FACE.TEN)),
//                Arguments.of(21, new Card(SUIT.CLUB, FACE.ACE),
//                        new Card(SUIT.CLUB, FACE.JACK)),
//                Arguments.of(22, new Card(SUIT.CLUB, FACE.ACE),
//                        new Card(SUIT.CLUB, FACE.JACK),
//                        new Card(SUIT.CLUB, FACE.JACK)));
//    }
}
