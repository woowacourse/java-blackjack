package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardRank;
import domain.card.CardSymbol;
import domain.card.Hand;
import fixture.CardDeckFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    public static Stream<Arguments> provideEachCardDecksAndExpected() {
        return Stream.of(
                Arguments.of(CardDeckFixture.createCardDeck(
                                Card.of(CardSymbol.SPADE, CardRank.EIGHT),
                                Card.of(CardSymbol.SPADE, CardRank.SEVEN)),
                        15),
                Arguments.of(CardDeckFixture.createCardDeck(
                                Card.of(CardSymbol.SPADE, CardRank.TEN),
                                Card.of(CardSymbol.SPADE, CardRank.SEVEN)),
                        17)
        );
    }

    public static Stream<Arguments> provideCardsWithAce() {
        return Stream.of(
                Arguments.of(CardDeckFixture.createCardDeck(
                                Card.of(CardSymbol.SPADE, CardRank.TWO),
                                Card.of(CardSymbol.SPADE, CardRank.THREE),
                                Card.of(CardSymbol.SPADE, CardRank.ACE)),
                        16),
                Arguments.of(CardDeckFixture.createCardDeck(
                                Card.of(CardSymbol.SPADE, CardRank.TEN),
                                Card.of(CardSymbol.SPADE, CardRank.TWO),
                                Card.of(CardSymbol.SPADE, CardRank.ACE)),
                        13),
                Arguments.of(CardDeckFixture.createCardDeck(
                                Card.of(CardSymbol.SPADE, CardRank.ACE),
                                Card.of(CardSymbol.HEART, CardRank.ACE),
                                Card.of(CardSymbol.COLVER, CardRank.ACE)),
                        13)
        );
    }

    @DisplayName("Hand는 처음 생성 될 때 2장의 카드를 가진다.")
    @Test
    void initHand() {
        // given
        Card card1 = Card.of(CardSymbol.HEART, CardRank.KING);
        Card card2 = Card.of(CardSymbol.HEART, CardRank.JACK);
        CardDeck cardDeck = CardDeckFixture.createCardDeck(card1, card2);

        Hand hand = new Hand(cardDeck);

        // when
        int actual = hand.getCards().size();

        // then
        assertThat(actual).isEqualTo(2);
    }

    @DisplayName("Hand의 카드들의 점수의 합을 계산할 수 있다")
    @ParameterizedTest
    @MethodSource("provideEachCardDecksAndExpected")
    void calculateTotalPoint(CardDeck cardDeck, int expected) {
        //given
        Hand hand = new Hand(cardDeck);

        //when
        int actual = hand.calculateTotalPoint();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("처음 생성 될 때 2장의 카드의 합이 21이라면 블랙잭이다.")
    @Test
    void blackjackHand() {
        // given
        Card card1 = Card.of(CardSymbol.HEART, CardRank.KING);
        Card card2 = Card.of(CardSymbol.HEART, CardRank.ACE);
        CardDeck cardDeck = CardDeckFixture.createCardDeck(card1, card2);

        Hand hand = new Hand(cardDeck);

        // when
        boolean actual = hand.isBlackjack();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("카드 뭉치에 카드를 추가할 수 있다")
    @Test
    void addCard() {
        //given
        Card card1 = Card.of(CardSymbol.HEART, CardRank.KING);
        Card card2 = Card.of(CardSymbol.HEART, CardRank.ACE);
        CardDeck cardDeck = CardDeckFixture.createCardDeck(card1, card2);

        Hand hand = new Hand(cardDeck);

        Card card = new Card(CardSymbol.COLVER, CardRank.FIVE);

        //when //then
        assertThatCode(() -> hand.add(card))
                .doesNotThrowAnyException();
    }

    @DisplayName("점수가 21점을 초과하면 버스트된다.")
    @Test
    void bust() {
        //given
        Card card1 = Card.of(CardSymbol.HEART, CardRank.KING);
        Card card2 = Card.of(CardSymbol.HEART, CardRank.JACK);
        Card card3 = Card.of(CardSymbol.HEART, CardRank.TWO);
        CardDeck cardDeck = CardDeckFixture.createCardDeck(card1, card2, card3);

        Hand hand = new Hand(cardDeck);
        hand.add(cardDeck.drawCard());

        //when
        boolean actual = hand.isBust();

        //then
        assertThat(actual).isTrue();
    }

    @DisplayName("점수가 21점을 초과하지않으면 버스트되지 않는다.")
    @Test
    void notBust() {
        //given
        Card card1 = Card.of(CardSymbol.HEART, CardRank.KING);
        Card card2 = Card.of(CardSymbol.HEART, CardRank.JACK);
        Card card3 = Card.of(CardSymbol.HEART, CardRank.ACE);
        CardDeck cardDeck = CardDeckFixture.createCardDeck(card1, card2, card3);

        Hand hand = new Hand(cardDeck);
        hand.add(cardDeck.drawCard());

        //when
        boolean actual = hand.isBust();

        //then
        assertThat(actual).isFalse();
    }

    @DisplayName("Ace가 있을 때, 11로 간주해도 21을 초과하지 않으면 11로 간주한다.")
    @ParameterizedTest
    @MethodSource("provideCardsWithAce")
    void processAcePoint(CardDeck cardDeck, int expected) {
        // given
        Hand hand = new Hand(cardDeck);
        hand.add(cardDeck.drawCard());

        // when
        int actual = hand.calculateTotalPoint();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
