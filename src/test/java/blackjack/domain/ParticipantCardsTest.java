package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
import blackjack.domain.card.ParticipantCards;
import blackjack.fixture.ParticipantCardsFixture;
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
        Card cardOne = Card.of(Suit.DIAMOND, CardNumber.THREE);
        Card cardTwo = Card.of(Suit.DIAMOND, CardNumber.TWO);

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
        Card cardOne = Card.of(Suit.DIAMOND, CardNumber.THREE);
        Card cardTwo = Card.of(Suit.DIAMOND, CardNumber.THREE);

        assertThatThrownBy(() -> new ParticipantCards(List.of(cardOne, cardTwo)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복되는 카드를 가지는 경우 예외가 발생한다.")
    void throwExceptionWhenCardsDuplicated() {
        Card cardOne = Card.of(Suit.DIAMOND, CardNumber.THREE);
        Card cardTwo = Card.of(Suit.DIAMOND, CardNumber.FOUR);
        ParticipantCards participantCards = new ParticipantCards(List.of(cardOne, cardTwo));

        assertThatThrownBy(() -> participantCards.receive(Card.of(Suit.DIAMOND, CardNumber.THREE)))
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
                        Card.of(Suit.DIAMOND, CardNumber.THREE),
                        Card.of(Suit.HEART, CardNumber.FOUR)
                ), 7),
                Arguments.arguments(List.of(
                        Card.of(Suit.SPADE, CardNumber.ACE),
                        Card.of(Suit.CLOVER, CardNumber.FOUR)
                ), 15)
        );
    }

    @ParameterizedTest
    @MethodSource("isBustDummy")
    @DisplayName("가지고 있는 카드가 버스트인지 판단한다.")
    void isBust(final List<Card> initialCards, final List<Card> additionalCards, final boolean expected) {
        ParticipantCards participantsCards = ParticipantCardsFixture.createParticipantsCards(initialCards, additionalCards);
        boolean isBust = participantsCards.isBust();

        assertThat(isBust).isEqualTo(expected);
    }

    static Stream<Arguments> isBustDummy() {
        return Stream.of(
                Arguments.arguments(
                        List.of(Card.of(Suit.DIAMOND, CardNumber.TWO),
                        Card.of(Suit.DIAMOND, CardNumber.FOUR)),
                        List.of(
                                Card.of(Suit.DIAMOND, CardNumber.THREE),
                                Card.of(Suit.HEART, CardNumber.FOUR)
                        ), false),
                Arguments.arguments(
                        List.of(Card.of(Suit.DIAMOND, CardNumber.TWO),
                        Card.of(Suit.DIAMOND, CardNumber.THREE)),
                        List.of(
                                Card.of(Suit.SPADE, CardNumber.ACE),
                                Card.of(Suit.CLOVER, CardNumber.FOUR)
                        ), false),
                Arguments.arguments(
                        List.of(Card.of(Suit.DIAMOND, CardNumber.TWO),
                        Card.of(Suit.DIAMOND, CardNumber.FOUR)),
                        List.of(
                                Card.of(Suit.SPADE, CardNumber.ACE),
                                Card.of(Suit.CLOVER, CardNumber.QUEEN),
                                Card.of(Suit.HEART, CardNumber.JACK),
                                Card.of(Suit.DIAMOND, CardNumber.THREE)
                        ), true)
        );
    }
}
