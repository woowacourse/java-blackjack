package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RuleTest {

    @Test
    @DisplayName("카드의 점수 총합을 계산한다. (에이스가 없는 경우)")
    void calculateCardsSum() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        List<Card> cards = List.of(card1, card2);

        // when
        int actual = Rule.calculateScore(cards);

        // then
        assertThat(actual).isEqualTo(6);
    }

    @Test
    @DisplayName("카드의 점수 총합을 계산한다. (에이스가 있는 경우)")
    void calculateCardsSumWithACE() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.ACE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.ACE);
        Card card3 = new Card(Pattern.HEART, Denomination.ACE);
        Card card4 = new Card(Pattern.SPADE, Denomination.ACE);
        List<Card> cards = List.of(card1, card2, card3, card4);

        // when
        int actual = Rule.calculateScore(cards);

        // then
        assertThat(actual).isEqualTo(14);
    }

    @Test
    @DisplayName("카드의 점수 총합을 계산한다. (총 합이 21이 넘는 경우)")
    void calculateCardsSumOver21() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Pattern.CLOVER, Denomination.TEN);
        Card card3 = new Card(Pattern.HEART, Denomination.TWO);
        List<Card> cards = List.of(card1, card2, card3);

        // when
        int actual = Rule.calculateScore(cards);

        // then
        assertThat(actual).isEqualTo(22);
    }

    @ParameterizedTest
    @MethodSource("provideBustTest")
    @DisplayName("카드의 총합이 21이 넘는 경우 버스트")
    void bust(List<Card> cards, boolean expected) {
        // when
        boolean actual = Rule.isBust(cards);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideBustTest() {
        Card DIAMOND_TEN = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card CLOVER_TEN = new Card(Pattern.CLOVER, Denomination.TEN);
        Card HEART_TWO = new Card(Pattern.HEART, Denomination.TWO);
        Card SPADE_ACE = new Card(Pattern.SPADE, Denomination.ACE);
        return Stream.of(
            Arguments.of(List.of(DIAMOND_TEN, CLOVER_TEN, HEART_TWO), true),
            Arguments.of(List.of(DIAMOND_TEN, CLOVER_TEN, SPADE_ACE), false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideBlackJackTest")
    @DisplayName("점수의 총합이 21이면서 2장이면 블랙잭이다.")
    void blackJack(List<Card> cards, boolean expected) {
        // when
        boolean actual = Rule.isBlackJack(cards);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideBlackJackTest() {
        Card DIAMOND_TEN = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card CLOVER_TEN = new Card(Pattern.CLOVER, Denomination.TEN);
        Card SPADE_ACE = new Card(Pattern.SPADE, Denomination.ACE);
        return Stream.of(
            Arguments.of(List.of(DIAMOND_TEN, SPADE_ACE), true),
            Arguments.of(List.of(DIAMOND_TEN, CLOVER_TEN, SPADE_ACE), false)
        );
    }
}
