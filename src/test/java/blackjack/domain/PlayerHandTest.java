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

class PlayerHandTest {

    @DisplayName("카드와 지갑을 가진다.")
    @Test
    void test1() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);

        Hand hand = Hand.of(card1, card2);
        Wallet wallet = Wallet.bet(1000);

        // when & then
        assertThatCode(() -> new PlayerHand(hand, wallet))
                .doesNotThrowAnyException();
    }

    @DisplayName("모든 카드를 가져온다.")
    @Test
    void test2() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);

        Hand hand = Hand.of(card1, card2);
        Wallet wallet = Wallet.bet(1000);

        PlayerHand playerHand = new PlayerHand(hand, wallet);

        List<Card> expect = List.of(card1, card2);

        // when & then
        assertThat(playerHand.getAllCards()).isEqualTo(expect);
    }

    @DisplayName("카드를 가져올 수 있다.")
    @Test
    void test3() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);

        Hand hand = Hand.of(card1, card2);
        Wallet wallet = Wallet.bet(1000);

        PlayerHand playerHand = new PlayerHand(hand, wallet);

        Card newCard = new Card(CardSuit.SPADE, CardRank.KING);

        List<Card> expect = List.of(card1, card2, newCard);

        // when
        playerHand.takeCard(newCard);

        // then
        assertThat(playerHand.getAllCards()).isEqualTo(expect);
    }

    private static Stream<Arguments> canTakeCardArgument() {
        return Stream.of(
                Arguments.arguments(HandFixture.createHandWithOptimisticValue15(), true),
                Arguments.arguments(HandFixture.busted(), false)
        );
    }

    @DisplayName("플레이어의 카드가 21을 넘지 않는다면 카드를 받을 수 있다.")
    @ParameterizedTest
    @MethodSource("canTakeCardArgument")
    void test8(Hand hand, boolean expect) {
        //given
        Wallet wallet = Wallet.bet(1000);
        PlayerHand playerHand = new PlayerHand(hand, wallet);

        // when & then
        assertThat(playerHand.canTakeCard()).isEqualTo(expect);
    }

    @DisplayName("카드가 블랙잭이라면 1.5배 더 받는다.")
    @Test
    void test10() {
        // given
        Hand hand = HandFixture.blackjack();
        PlayerHand playerHand = new PlayerHand(hand, Wallet.bet(1000));

        // when
        playerHand.tryReceiveBlackjackBonus();

        // then
        assertThat(playerHand.getRevenue()).isEqualTo(1500);
    }

    public static Stream<Arguments> betArguments() {
        return Stream.of(
                Arguments.of(1000, GameResultType.WIN, 1000),
                Arguments.of(1000, GameResultType.LOSE, -1000),
                Arguments.of(1000, GameResultType.TIE, 0)
        );
    }

    @DisplayName("초기 금액과 베팅 후 금액에 대한 수익을 반환한다.")
    @ParameterizedTest
    @MethodSource("betArguments")
    void test11(int money, GameResultType gameResultType, int expect) {
        // given
        Wallet wallet = Wallet.bet(money);
        new PlayerHand(new Hand(), wallet);

        // when
        wallet.calculate(gameResultType);

        // then
        assertThat(wallet.getRevenue()).isEqualTo(expect);
    }

    @DisplayName("블랙잭 덱이면 true를 반환한다.")
    @Test
    void test12() {
        // given
        Hand hand = HandFixture.blackjack();
        PlayerHand playerHand = new PlayerHand(hand, Wallet.bet(1000));

        // when & then
        assertThat(playerHand.isBlackjack()).isTrue();
    }
}
