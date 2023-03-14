package blackjack.domain.game;

import blackjack.domain.card.*;
import blackjack.fixture.MockDeck;
import blackjack.fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class HandTest {
    @Test
    @DisplayName("생성한다.")
    void create() {
        final Deck deck = new CardDeck();

        assertThatNoException()
                .isThrownBy(() -> new Hand(deck));
    }

    @Test
    @DisplayName("생성시에 카드가 두 장이 아닐 경우 예외가 발생한다.")
    void throwExceptionWhenCardsHasSizeLowerThan1() {
        final Deck deck = MockDeck.create(Collections.emptyList());

        assertThatThrownBy(() -> new Hand(deck))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("생성시에 카드가 중복될 경우 예외가 발생한다.")
    void throwExceptionWhenInitialCardsDuplicated() {
        final Deck deck = MockDeck.create(List.of(
                new Card(CardShape.SPADE, CardNumber.ACE),
                new Card(CardShape.SPADE, CardNumber.ACE)
        ));

        assertThatThrownBy(() -> new Hand(deck))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복되는 카드를 가지는 경우 예외가 발생한다.")
    void throwExceptionWhenCardsDuplicated2() {
        final Deck deck = new CardDeck();
        final Hand hand = new Hand(deck);

        assertThatThrownBy(() -> CardsCache.getAllCards().forEach(hand::receive))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("calculateDummy")
    @DisplayName("가지고 있는 카드의 합을 계산한다.")
    void calculate(final List<Card> cards, final int expectedSum) {
        final Deck deck = MockDeck.create(cards);
        final Hand hand = new Hand(deck);
        final int cardSum = hand.getMaxValueNearBlackJack();

        assertThat(cardSum).isEqualTo(expectedSum);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("카드를 사이즈만큼 오픈한다.")
    void open2(final int openCount) {
        final Deck deck = new CardDeck();
        final Hand hand = new Hand(deck);
        final List<Card> openCards = hand.open(openCount);

        assertThat(openCards).hasSize(openCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("모든 카드를 오픈한다.")
    void openAll2(final int drawCount) {
        final Deck deck = new CardDeck();
        final Hand hand = new Hand(deck);
        for (int count = 0; count < drawCount; count++) {
            hand.receive(deck.draw());
        }
        final List<Card> openCards = hand.openAll();
        final int expectedSize = drawCount + 2;

        assertThat(openCards).hasSize(expectedSize);
    }

    @ParameterizedTest
    @MethodSource("isBustDummy")
    @DisplayName("가지고 있는 카드가 버스트인지 판단한다.")
    void isBust(final Card one, final Card two, final List<Card> additionalCards, final boolean expected) {
        Hand participantsCards = HandFixture.create(one, two, additionalCards);
        boolean isBust = participantsCards.isBust();

        assertThat(isBust).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("isBlackJackDummy")
    @DisplayName("가지고 있는 카드가 버스트인지 판단한다.")
    void isBlackJack(final Card one, final Card two, final List<Card> additionalCards, final boolean expected) {
        Hand participantsCards = HandFixture.create(one, two, additionalCards);
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
