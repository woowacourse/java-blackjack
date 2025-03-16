package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.utils.HandFixture;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @DisplayName("딜러는 카드를 가진다.")
    @Test
    void test1() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = Hand.of(card1, card2);

        // when & then
        assertThatCode(() -> new Dealer(hand)).doesNotThrowAnyException();
    }

    @DisplayName("딜러의 모든 카드를 가져온다.")
    @Test
    void test2() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = Hand.of(card1, card2);
        Dealer dealer = new Dealer(hand);

        List<Card> expect = List.of(card1, card2);

        // when & then
        assertThat(dealer.getAllCards()).isEqualTo(expect);
    }

    @DisplayName("딜러는 카드를 가져올 수 있다.")
    @Test
    void test3() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = Hand.of(card1, card2);
        Card newCard = new Card(CardSuit.SPADE, CardRank.KING);
        Dealer dealer = new Dealer(hand);

        List<Card> expect = List.of(card1, card2, newCard);

        // when
        dealer.takeCard(newCard);

        // then
        assertThat(dealer.getAllCards()).isEqualTo(expect);
    }

    @DisplayName("딜러는 첫번째 카드를 보여줄 수 있다.")
    @Test
    void test4() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = Hand.of(card1, card2);
        Dealer dealer = new Dealer(hand);

        // when
        Card revealedCard = dealer.revealFirstCard();

        // then
        assertThat(revealedCard).isEqualTo(card1);
    }

    @DisplayName("딜러의 카드의 총합이 21을 넘으면 busted 여부의 결과가 true 가 된다")
    @Test
    void test6() {
        // given
        Hand hand = new Hand();
        Dealer dealer = new Dealer(hand);
        hand.takeCard(new Card(CardSuit.SPADE, CardRank.KING));
        hand.takeCard(new Card(CardSuit.SPADE, CardRank.QUEEN));
        hand.takeCard(new Card(CardSuit.SPADE, CardRank.JACK));

        // when
        boolean actual = dealer.isBusted();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("딜러의 카드가 21을 넘지 않는다면 busted 여부 결과가 false가 된다")
    @Test
    void test7() {
        Hand hand = new Hand();
        Dealer dealer = new Dealer(hand);
        hand.takeCard(new Card(CardSuit.SPADE, CardRank.KING));
        hand.takeCard(new Card(CardSuit.SPADE, CardRank.QUEEN));
        hand.takeCard(new Card(CardSuit.SPADE, CardRank.ACE));

        // when
        boolean actual = dealer.isBusted();

        // then
        assertThat(actual).isFalse();
    }

    private static Stream<Arguments> canHitArgument() {
        return Stream.of(Arguments.arguments(HandFixture.createHandWithOptimisticValue15(), true),
                Arguments.arguments(HandFixture.createHandWithOptimisticValue20(), false));
    }

    @DisplayName("딜러의 카드가 16을 넘으면 카드를 받을 수 없다.")
    @ParameterizedTest
    @MethodSource("canHitArgument")
    void test8(Hand hand, boolean expect) {
        //given
        Dealer dealer = new Dealer(hand);

        // when & then
        assertThat(dealer.canHit()).isEqualTo(expect);
    }
}
