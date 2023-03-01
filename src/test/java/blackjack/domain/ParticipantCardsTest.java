package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("참여자 카드")
class ParticipantCardsTest {
    @Test
    @DisplayName("생성한다.")
    void create() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.TWO);

        assertThatNoException()
                .isThrownBy(() -> new ParticipantCards(List.of(cardOne, cardTwo)));
    }

    @Test
    @DisplayName("생성시에 카드가 두 장이 아닐 경우 예외가 발생한다.")
    void throwExceptionWhenCardsHasSizeLowerThan1() {
        assertThatThrownBy(() -> new ParticipantCards(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("생성시에 카드가 중복될 경우 예외가 발생한다.")
    void throwExceptionWhenInitialCardsDuplicated() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.THREE);

        assertThatThrownBy(() -> new ParticipantCards(List.of(cardOne, cardTwo)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복되는 카드를 가지는 경우 예외가 발생한다.")
    void throwExceptionWhenCardsDuplicated() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.FOUR);
        ParticipantCards participantCards = new ParticipantCards(List.of(cardOne, cardTwo));

        assertThatThrownBy(() -> participantCards.receive(new Card(CardShape.DIAMOND, CardNumber.THREE)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("calculateDummy")
    @DisplayName("가지고 있는 카드의 합을 계산한다.")
    void calculate(final List<Card> cards, final int expectedSum) {
        ParticipantCards participantCards = new ParticipantCards(cards);
        int cardSum = participantCards.calculate();

        assertThat(cardSum).isEqualTo(expectedSum);
    }

    static Stream<Arguments> calculateDummy() {
        return Stream.of(
                Arguments.arguments(List.of(
                        new Card(CardShape.DIAMOND, CardNumber.THREE),
                        new Card(CardShape.HEART, CardNumber.FOUR)
                ), CardNumber.THREE.getValue() + CardNumber.FOUR.getValue()),
                Arguments.arguments(List.of(
                        new Card(CardShape.SPADE, CardNumber.ACE),
                        new Card(CardShape.CLOVER, CardNumber.FOUR)
                ), CardNumber.ACE.getValue() + CardNumber.FOUR.getValue())
        );
    }
}