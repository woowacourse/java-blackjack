package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DealerTest {

    @Test
    @DisplayName("딜러는 생성될 때 두 장의 카드를 받는다.")
    void startWithDraw() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        Cards cards = new Cards(List.of(card1, card2));

        // when
        Dealer dealer = new Dealer(cards);

        // then
        assertThat(dealer.getCards()).containsOnly(card1, card2);
    }

    @Test
    @DisplayName("딜러를 생성할 때 카드는 null일 수 없다.")
    void cardsNotNull() {
        // then
        assertThatThrownBy(() -> new Dealer(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("딜러가 카드 한 장을 더 받는 경우")
    void addCard() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        Cards cards = new Cards(List.of(card1, card2));

        Dealer dealer = new Dealer(cards);

        // when
        dealer.hit(new Card(Pattern.HEART, Denomination.THREE));

        // then
        assertThat(dealer.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러의 카드의 총합이 16 이하면 hit이 가능하다.")
    void hittable() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Pattern.CLOVER, Denomination.SIX);
        Cards cards = new Cards(List.of(card1, card2));

        Dealer dealer = new Dealer(cards);

        // when
        boolean actual = dealer.isHittable();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드의 총합이 17 이상이면 hit이 불가능하다.")
    void notHittable() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Pattern.CLOVER, Denomination.SEVEN);
        Cards cards = new Cards(List.of(card1, card2));

        Dealer dealer = new Dealer(cards);

        // when
        boolean actual = dealer.isHittable();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("카드의 점수 총합을 계산한다. (에이스가 없는 경우)")
    void calculateCardsSum() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        Cards cards = new Cards(List.of(card1, card2));

        Dealer dealer = new Dealer(cards);

        // when
        int actual = dealer.calculateScore();

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
        Cards cards = new Cards(List.of(card1, card2));

        Dealer dealer = new Dealer(cards);
        dealer.hit(card3);
        dealer.hit(card4);

        // when
        int actual = dealer.calculateScore();

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
        Cards cards = new Cards(List.of(card1, card2));

        Dealer dealer = new Dealer(cards);
        dealer.hit(card3);

        // when
        int actual = dealer.calculateScore();

        // then
        assertThat(actual).isEqualTo(22);
    }

    @ParameterizedTest
    @MethodSource("provideBustTest")
    @DisplayName("카드의 총합이 21이 넘는 경우 버스트이다.")
    void bust(Card card, boolean expected) {
        // given
        Card DIAMOND_TEN = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card CLOVER_TEN = new Card(Pattern.CLOVER, Denomination.TEN);
        Cards cards = new Cards(List.of(DIAMOND_TEN, CLOVER_TEN));

        Dealer dealer = new Dealer(cards);
        dealer.hit(card);

        // when
        boolean actual = dealer.isBust();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideBustTest() {
        Card HEART_TWO = new Card(Pattern.HEART, Denomination.TWO);
        Card SPADE_ACE = new Card(Pattern.SPADE, Denomination.ACE);
        return Stream.of(
            Arguments.of(HEART_TWO, true),
            Arguments.of(SPADE_ACE, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideBlackJackTest")
    @DisplayName("점수의 총합이 21이면서 2장이면 블랙잭이다.")
    void blackJack(List<Card> initCards, List<Card> hitCards, boolean expected) {
        // given
        Cards cards = new Cards(initCards);
        Dealer dealer = new Dealer(cards);
        for (Card hitCard : hitCards) {
            dealer.hit(hitCard);
        }

        // when
        boolean actual = dealer.isBlackJack();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideBlackJackTest() {
        Card DIAMOND_TEN = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card CLOVER_TEN = new Card(Pattern.CLOVER, Denomination.TEN);
        Card SPADE_ACE = new Card(Pattern.SPADE, Denomination.ACE);
        return Stream.of(
            Arguments.of(List.of(DIAMOND_TEN, SPADE_ACE), List.of(), true),
            Arguments.of(List.of(DIAMOND_TEN, CLOVER_TEN), List.of(SPADE_ACE), false)
        );
    }
}
