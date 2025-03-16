package domain;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardDeckTest {
    @DisplayName("카드 배부 시 52장의 카드 덱에서 카드를 뽑아서 배부한다.")
    @Test
    void test2() {
        // given
        CardDeck cardDeck = new CardDeck();
        int originCardDeckSize = cardDeck.getCardDeck().size();

        // when
        cardDeck.drawCard();
        int afterDrawDeckSize = cardDeck.getCardDeck().size();

        // then
        Assertions.assertThat(originCardDeckSize - 1).isEqualTo(afterDrawDeckSize);
    }

    @DisplayName("카드가 다 떨어지면 예외를 발생한다")
    @Test
    void test3() {
        // given
        CardDeck cardDeck = new CardDeck();
        int count = 0;
        for (int i = 0; i < 52; i++) {
            cardDeck.drawCard();
            count++;
        }

        // when & then
        Assertions.assertThatThrownBy(cardDeck::drawCard)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드가 다 떨어졌습니다");
    }

    @DisplayName("카드의 합이 21 초과 시 패배한다")
    @Test
    void test4() {
        //given
        Card trumpCard1 = new Card(CardShape.CLOVER, CardRank.K);
        Card trumpCard2 = new Card(CardShape.CLOVER, CardRank.J);
        Card trumpCard3 = new Card(CardShape.CLOVER, CardRank.FIVE);
        CardHand cardDeck = new CardHand();

        cardDeck.add(trumpCard1);
        cardDeck.add(trumpCard2);
        cardDeck.add(trumpCard3);

        // when
        boolean isOver = cardDeck.checkOverScore();

        // then
        Assertions.assertThat(isOver).isTrue();
    }

    @DisplayName("카드에 ACE가 있다면 가능한 max값을 계산하여 판단한다")
    @ParameterizedTest
    @MethodSource("hasAce")
    void test5(List<Card> cards, boolean isExpectedOver) {
        //given
        CardHand cardDeck = new CardHand();
        for (Card card : cards) {
            cardDeck.add(card);
        }

        // when & then
        boolean isRealOver = cardDeck.checkOverScore();
        Assertions.assertThat(isRealOver).isEqualTo(isExpectedOver);
    }

    private static Stream<Arguments> hasAce() {
        return Stream.of(
                Arguments.arguments(List.of(
                        new Card(CardShape.DIA, CardRank.ACE),
                        new Card(CardShape.CLOVER, CardRank.ACE),
                        new Card(CardShape.HEART, CardRank.ACE)), false),
                Arguments.arguments(List.of(
                        new Card(CardShape.DIA, CardRank.K),
                        new Card(CardShape.CLOVER, CardRank.J),
                        new Card(CardShape.HEART, CardRank.ACE)), false),
                Arguments.arguments(List.of(
                        new Card(CardShape.DIA, CardRank.ACE),
                        new Card(CardShape.CLOVER, CardRank.Q)), false)
        );
    }

    @DisplayName("카드의 총합을 계산한다.")
    @ParameterizedTest
    @MethodSource("cardDeck")
    void test6(List<Card> cards, int expectedSum) {
        // given
        CardHand cardDeck = new CardHand();
        for (Card card : cards) {
            cardDeck.add(card);
        }

        // when
        int realSum = cardDeck.calculateScore();

        // then
        Assertions.assertThat(realSum).isEqualTo(expectedSum);
    }

    private static Stream<Arguments> cardDeck() {
        return Stream.of(
                Arguments.arguments(List.of(
                        new Card(CardShape.DIA, CardRank.ACE),
                        new Card(CardShape.CLOVER, CardRank.ACE),
                        new Card(CardShape.HEART, CardRank.ACE)), 13),
                Arguments.arguments(List.of(
                        new Card(CardShape.DIA, CardRank.K),
                        new Card(CardShape.CLOVER, CardRank.J),
                        new Card(CardShape.HEART, CardRank.ACE)), 21),
                Arguments.arguments(List.of(
                        new Card(CardShape.HEART, CardRank.ACE),
                        new Card(CardShape.CLOVER, CardRank.Q)), 21),
                Arguments.arguments(List.of(
                        new Card(CardShape.DIA, CardRank.J),
                        new Card(CardShape.CLOVER, CardRank.Q)), 20),
                Arguments.arguments(List.of(
                        new Card(CardShape.SPADE, CardRank.K),
                        new Card(CardShape.CLOVER, CardRank.J),
                        new Card(CardShape.CLOVER, CardRank.Q)), 30)
        );
    }

    @DisplayName("블랙잭인지 아닌지 판단한다")
    @Test
    void test7() {
        // given
        Card trumpCard1 = new Card(CardShape.CLOVER, CardRank.ACE);
        Card trumpCard2 = new Card(CardShape.CLOVER, CardRank.J);
        CardHand cardDeck = new CardHand();

        cardDeck.add(trumpCard1);
        cardDeck.add(trumpCard2);

        // when
        boolean isBlackjack = cardDeck.isBlackjack();

        // then
        Assertions.assertThat(isBlackjack).isEqualTo(true);
    }
}
