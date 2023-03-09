package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Deck;
import blackjack.fixture.ParticipantCardsFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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
        int cardSum = participantCards.getMaxValueNearBlackJack();

        assertThat(cardSum).isEqualTo(expectedSum);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("카드를 사이즈만큼 오픈한다.")
    void open(final int openCount) {
        final Deck deck = new Deck();
        final List<Card> cards = List.of(deck.draw(), deck.draw());
        final ParticipantCards participantCards = new ParticipantCards(cards);
        participantCards.receive(deck.draw());
        final List<Card> openCards = participantCards.open(openCount);

        assertThat(openCards).hasSize(openCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("모든 카드를 오픈한다.")
    void openAll(final int drawCount) {
        final Deck deck = new Deck();
        final List<Card> cards = List.of(deck.draw(), deck.draw());
        final ParticipantCards participantCards = new ParticipantCards(cards);
        for (int count = 0; count < drawCount; count++) {
            participantCards.receive(deck.draw());
        }
        final List<Card> openCards = participantCards.openAll();
        final int expectedSize = cards.size() + drawCount;

        assertThat(openCards).hasSize(expectedSize);
    }

    @ParameterizedTest
    @MethodSource("isBustDummy")
    @DisplayName("가지고 있는 카드가 버스트인지 판단한다.")
    void isBust(final Card one, final Card two, final List<Card> additionalCards, final boolean expected) {
        ParticipantCards participantsCards = ParticipantCardsFixture.create(one, two, additionalCards);
        boolean isBust = participantsCards.isBust();

        assertThat(isBust).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("isBlackJackDummy")
    @DisplayName("가지고 있는 카드가 버스트인지 판단한다.")
    void isBlackJack(final Card one, final Card two, final List<Card> additionalCards, final boolean expected) {
        ParticipantCards participantsCards = ParticipantCardsFixture.create(one, two, additionalCards);
        boolean isBlackJack = participantsCards.isBlackJack();

        assertThat(isBlackJack).isEqualTo(expected);
    }

    static Stream<Arguments> calculateDummy() {
        return Stream.of(
                Arguments.arguments(List.of(
                        new Card(CardShape.DIAMOND, CardNumber.THREE),
                        new Card(CardShape.HEART, CardNumber.FOUR)
                ), 7),
                Arguments.arguments(List.of(
                        new Card(CardShape.SPADE, CardNumber.ACE),
                        new Card(CardShape.CLOVER, CardNumber.FOUR)
                ), 15)
        );
    }

    static Stream<Arguments> isBustDummy() {
        return Stream.of(
                Arguments.arguments(
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FOUR),
                        List.of(
                                new Card(CardShape.DIAMOND, CardNumber.THREE),
                                new Card(CardShape.HEART, CardNumber.FOUR)
                        ), false),
                Arguments.arguments(
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.THREE),
                        List.of(
                                new Card(CardShape.SPADE, CardNumber.ACE),
                                new Card(CardShape.CLOVER, CardNumber.FOUR)
                        ), false),
                Arguments.arguments(
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FOUR),
                        List.of(
                                new Card(CardShape.SPADE, CardNumber.ACE),
                                new Card(CardShape.CLOVER, CardNumber.QUEEN),
                                new Card(CardShape.HEART, CardNumber.JACK),
                                new Card(CardShape.DIAMOND, CardNumber.THREE)
                        ), true)
        );
    }

    static Stream<Arguments> isBlackJackDummy() {
        return Stream.of(
                // 블랙잭, 2 + 4 + 5 + 10 = 21
                Arguments.arguments(
                        new Card(CardShape.SPADE, CardNumber.TWO),
                        new Card(CardShape.SPADE, CardNumber.FOUR),
                        List.of(
                                new Card(CardShape.SPADE, CardNumber.FIVE),
                                new Card(CardShape.SPADE, CardNumber.KING)
                        ), true
                ),
                // 블랙잭, 1 + 10 + 10 = 21
                Arguments.arguments(
                        new Card(CardShape.SPADE, CardNumber.ACE),
                        new Card(CardShape.SPADE, CardNumber.KING),
                        List.of(
                                new Card(CardShape.SPADE, CardNumber.QUEEN)
                        ), true
                ),
                // 블랙잭, 11 + 10 = 21
                Arguments.arguments(
                        new Card(CardShape.SPADE, CardNumber.ACE),
                        new Card(CardShape.SPADE, CardNumber.KING),
                        List.of(
                        ), true
                ),
                // 블랙잭이 아닌 경우, 2 + 1 + 5 + 10 = 18
                Arguments.arguments(
                        new Card(CardShape.SPADE, CardNumber.TWO),
                        new Card(CardShape.SPADE, CardNumber.ACE),
                        List.of(
                                new Card(CardShape.SPADE, CardNumber.FIVE),
                                new Card(CardShape.SPADE, CardNumber.KING)
                        ), false
                ),
                // 블랙잭이 아닌 경우 3 + 1 + 5 + 10 == 19
                Arguments.arguments(
                        new Card(CardShape.SPADE, CardNumber.THREE),
                        new Card(CardShape.SPADE, CardNumber.ACE),
                        List.of(
                                new Card(CardShape.SPADE, CardNumber.FIVE),
                                new Card(CardShape.SPADE, CardNumber.KING)
                        ), false
                )
        );
    }
}
