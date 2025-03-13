package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSymbol;
import domain.card.Hand;
import fixture.HandFixture;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    public static Stream<Arguments> provideEachCardsAndExpected() {
        return Stream.of(
                Arguments.of(List.of(
                                new Card(CardSymbol.COLVER, CardRank.JACK),
                                new Card(CardSymbol.HEART, CardRank.FIVE)),
                        15),
                Arguments.of(List.of(
                                new Card(CardSymbol.COLVER, CardRank.SEVEN),
                                new Card(CardSymbol.HEART, CardRank.TEN)),
                        17)
        );
    }

    @DisplayName("점수가 21점을 초과하면 버스트된다.")
    @Test
    void burst2() {
        //given
        Card card1 = new Card(CardSymbol.HEART, CardRank.KING);
        Card card2 = new Card(CardSymbol.HEART, CardRank.JACK);
        Card card3 = new Card(CardSymbol.HEART, CardRank.TWO);

        Hand hand = HandFixture.createHand(card1, card2, card3);

        //when
        boolean actual = hand.isBurst();

        //then
        assertThat(actual).isTrue();
    }

    @DisplayName("점수가 21점을 초과하지않으면 버스트되지 않는다.")
    @Test
    void notBurst() {
        //given
        Card card1 = new Card(CardSymbol.HEART, CardRank.KING);
        Card card2 = new Card(CardSymbol.HEART, CardRank.JACK);

        Hand hand = HandFixture.createHand(card1, card2);

        //when
        boolean actual = hand.isBurst();

        //then
        assertThat(actual).isFalse();
    }

    @DisplayName("카드 뭉치에 카드를 추가할 수 있다")
    @Test
    void addCard() {
        //given
        Hand hand = new Hand();
        Card card = new Card(CardSymbol.COLVER, CardRank.FIVE);
        //when

        //then
        assertThatCode(() -> hand.add(card))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드뭉치의 카드들의 점수의 합을 계산할 수 있다")
    @ParameterizedTest
    @MethodSource("provideEachCardsAndExpected")
    void calculateTotalPoint(List<Card> cardList, int expected) {
        //given
        Hand hand = new Hand();
        for (Card card : cardList) {
            hand.add(card);
        }

        //when
        int actual = hand.calculateTotalPoint();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Ace가 있을 때, 11로 간주해도 21을 초과하지않을 경우 11로 간주한다.")
    @Test
    void considerAceHas11() {
        //given
        //given
        Card card1 = new Card(CardSymbol.HEART, CardRank.ACE);
        Card card2 = new Card(CardSymbol.HEART, CardRank.KING);

        Hand hand = HandFixture.createHand(card1, card2);

        //when
        int actual = hand.calculateTotalPoint();

        //then
        assertThat(actual).isEqualTo(21);
    }

    @DisplayName("Ace가 있을 때, 11로 간주해도 21을 초과하지않을 경우 11로 간주한다.")
    @Test
    void considerAceHas112() {
        //given
        Card card1 = new Card(CardSymbol.HEART, CardRank.ACE);
        Card card2 = new Card(CardSymbol.HEART, CardRank.ACE);

        Hand hand = HandFixture.createHand(card1, card2);

        //when
        int actual = hand.calculateTotalPoint();

        //then
        assertThat(actual).isEqualTo(12);
    }

    @DisplayName("Ace가 있을 때, 11로 간주해도 21을 초과하지않을 경우 11로 간주한다.")
    @Test
    void considerAceHas113() {
        //given
        Card card1 = new Card(CardSymbol.HEART, CardRank.ACE);
        Card card2 = new Card(CardSymbol.HEART, CardRank.ACE);
        Card card3 = new Card(CardSymbol.HEART, CardRank.ACE);
        Card card4 = new Card(CardSymbol.HEART, CardRank.ACE);

        Hand hand = HandFixture.createHand(card1, card2, card3, card4);
        //when
        int actual = hand.calculateTotalPoint();

        //then
        assertThat(actual).isEqualTo(14);
    }

    @DisplayName("처음 두 장의 카드 합이 21일 경우 블랙잭이다.")
    @Test
    void blackjack() {
        // given
        Card card1 = new Card(CardSymbol.HEART, CardRank.ACE);
        Card card2 = new Card(CardSymbol.HEART, CardRank.JACK);

        Hand hand = HandFixture.createHand(card1, card2);

        // when
        final var actual = hand.isBlackjack();

        // then
        assertThat(actual).isTrue();
    }
}
