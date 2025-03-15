package blackjack.domain.gambler;

import static blackjack.domain.card.CardType.ACE;
import static blackjack.domain.card.CardType.EIGHT;
import static blackjack.domain.card.CardType.FIVE;
import static blackjack.domain.card.CardType.SIX;
import static blackjack.domain.card.CardType.TEN;
import static blackjack.domain.card.CardType.THREE;
import static blackjack.domain.card.CardType.TWO;
import static blackjack.domain.fixture.CardFixture.createCards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Hands;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class HandsTest {
    @DisplayName("카드의_개수가_주어진_사이즈와_일치하는_지_여부를_반환한다")
    @Test
    void hasSize() {
        // given
        Hands hands = new Hands();
        hands.addCard(new Card(CardShape.CLOVER, TEN));
        hands.addCard(new Card(CardShape.HEART, EIGHT));

        // when
        boolean result = hands.hasSize(2);

        // then
        assertThat(result).isTrue();
    }
    
    @DisplayName("카드의 합이 특정 값 초과인 지 여부를 반환한다")
    @CsvSource(value = {"21:False", "18:False", "17:True"}, delimiterString = ":")
    @ParameterizedTest
    void isScoreExceedTest(int score, boolean expected) {
        // given
        Hands hands = new Hands();
        hands.addCard(new Card(CardShape.CLOVER, TEN));
        hands.addCard(new Card(CardShape.HEART, EIGHT));

        // when
        boolean result = hands.isScoreExceed(score);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("카드의 합을 계산한다")
    @Test
    void calculateScoreTest() {
        // given
        Hands hands = new Hands();
        hands.addCard(new Card(CardShape.CLOVER, TEN));
        hands.addCard(new Card(CardShape.HEART, EIGHT));

        // when
        int result = hands.calculateScore();

        // then
        assertThat(result).isEqualTo(18);
    }

    @DisplayName("에이스를 고려하여 카드의 합을 계산한다")
    @MethodSource(value = {"returnAceShouldBeConsideredAsMinValue", "returnAceShouldBeConsideredAsMaxValue"})
    @ParameterizedTest
    void calculateScoreConsiderAceTest(List<Card> cards, int sum) {
        // given
        Hands hands = new Hands();
        for (Card card : cards) {
            hands.addCard(card);
        }

        // when
        int result = hands.calculateScore();

        // then
        assertThat(result).isEqualTo(sum);
    }

    static Stream<Arguments> returnAceShouldBeConsideredAsMaxValue() {
        Arguments arguments1 = Arguments.arguments(createCards(ACE, EIGHT), 19);
        Arguments arguments2 = Arguments.arguments(createCards(ACE, TEN), 21);
        Arguments arguments3 = Arguments.arguments(createCards(TWO, THREE, ACE), 16);
        return Stream.of(arguments1, arguments2, arguments3);
    }

    static Stream<Arguments> returnAceShouldBeConsideredAsMinValue() {
        Arguments arguments1 = Arguments.arguments(createCards(ACE, ACE), 12);
        Arguments arguments2 = Arguments.arguments(createCards(FIVE, SIX, ACE), 12);
        Arguments arguments3 = Arguments.arguments(createCards(FIVE, ACE, ACE), 17);
        Arguments arguments4 = Arguments.arguments(createCards(ACE, ACE, ACE), 13);
        return Stream.of(arguments1, arguments2, arguments3, arguments4);
    }
}
