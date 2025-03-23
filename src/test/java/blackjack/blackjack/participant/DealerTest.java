package blackjack.blackjack.participant;

import static blackjack.fixture.TestFixture.provideBiggerAceCards;
import static blackjack.fixture.TestFixture.provideBiggerAndSmallerAceCards;
import static blackjack.fixture.TestFixture.provideCards;
import static blackjack.fixture.TestFixture.provideEmptyCards;
import static blackjack.fixture.TestFixture.provideSmallerAceCards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.blackjack.card.Card;
import blackjack.blackjack.card.Denomination;
import blackjack.blackjack.card.Hand;
import blackjack.blackjack.card.Suit;
import blackjack.blackjack.result.ProfitResult;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(provideEmptyCards());
    }

    @DisplayName("카드들을 받는다.")
    @Test
    void receiveCards() {
        // given
        final Hand hand = provideCards(2);

        // when
        dealer.receiveCards(hand);

        // then
        assertThat(dealer.showAllCards()).isEqualTo(hand);
    }

    @DisplayName("딜러는 카드 2개 중 1개만 보여준다.")
    @Test
    void showDealerCards() {
        // given
        final Hand hand = provideCards(2);
        dealer.receiveCards(hand);

        // when
        final Hand dealerHand = dealer.showInitialCards();

        // then
        assertThat(dealerHand.getHand()).hasSize(1);
    }

    @DisplayName("딜러의 카드의 합이 블랙잭이 아니면서 16 이하면 추가로 뽑을 수 있다.")
    @ParameterizedTest
    @MethodSource
    void canHit(final Hand hand, final boolean expected) {
        // given
        final Dealer dealer = new Dealer(hand);

        // when & then
        assertThat(dealer.canHit()).isEqualTo(expected);
    }

    private static Stream<Arguments> canHit() {
        return Stream.of(
                Arguments.of(new Hand(List.of(
                        new Card(Suit.SPADE, Denomination.TEN),
                        new Card(Suit.SPADE, Denomination.SEVEN)
                )), false),
                Arguments.of(new Hand(List.of(
                        new Card(Suit.SPADE, Denomination.A),
                        new Card(Suit.SPADE, Denomination.K)
                )), false),
                Arguments.of(new Hand(List.of(
                        new Card(Suit.SPADE, Denomination.TEN),
                        new Card(Suit.SPADE, Denomination.SIX)
                )), true)
        );
    }

    @DisplayName("카드 합을 구한다")
    @ParameterizedTest
    @MethodSource
    void calculateProfit(final Hand hand, final int expected) {
        // given
        dealer.receiveCards(hand);

        // when & then
        assertThat(dealer.calculateScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> calculateProfit() {
        return Stream.of(
                Arguments.of(provideSmallerAceCards(), 18),
                Arguments.of(provideBiggerAceCards(), 21),
                Arguments.of(provideBiggerAndSmallerAceCards(), 17)
        );
    }

    @DisplayName("플레이어가 블랙잭이 아닌 경우의 우승 수익을 계산한다")
    @ParameterizedTest
    @MethodSource
    void calculateWinningResultExceptBlackjack(final Hand playerHand, final BigDecimal dealerProfit,
                                               final BigDecimal playerProfit) {
        // Given
        final Hand dealerHand = new Hand(
                List.of(new Card(Suit.DIAMOND, Denomination.FIVE), new Card(Suit.HEART, Denomination.THREE)));
        dealer = new Dealer(dealerHand);
        final Player player = new Player(playerHand, "밍트", new BigDecimal("10000"));
        dealer.stayIfRunning();
        player.stayIfRunning();

        // When
        final ProfitResult profitResult = dealer.calculateProfit(new Players(List.of(player)));

        // Then
        assertThat(profitResult.getResult()).isEqualTo(Map.of(dealer, dealerProfit, player, playerProfit));
    }

    private static Stream<Arguments> calculateWinningResultExceptBlackjack() {
        return Stream.of(
                Arguments.of(new Hand(
                                List.of(new Card(Suit.CLOB, Denomination.FIVE), new Card(Suit.SPADE, Denomination.THREE))),
                        BigDecimal.ZERO, BigDecimal.ZERO),
                Arguments.of(new Hand(
                                List.of(new Card(Suit.CLOB, Denomination.FIVE), new Card(Suit.SPADE, Denomination.TWO))),
                        new BigDecimal(10000), new BigDecimal(-10000)),
                Arguments.of(new Hand(
                                List.of(new Card(Suit.CLOB, Denomination.FIVE), new Card(Suit.SPADE, Denomination.FOUR))),
                        new BigDecimal(-10000), new BigDecimal(10000))
        );
    }

    @DisplayName("플레이어가 블랙잭인 경우 우승 수익을 계산한다")
    @Test
    void calculateWinningResultWhenBlackjack() {
        // Given
        final Hand dealerHand = new Hand(
                List.of(new Card(Suit.DIAMOND, Denomination.FIVE), new Card(Suit.HEART, Denomination.THREE)));
        final Hand playerHand = new Hand(
                List.of(new Card(Suit.CLOB, Denomination.TEN), new Card(Suit.SPADE, Denomination.A)));
        dealer = new Dealer(dealerHand);
        final Player player = new Player(playerHand, "밍트", new BigDecimal("10000"));

        // When
        final ProfitResult profitResult = dealer.calculateProfit(new Players(List.of(player)));

        // Then
        assertThat(profitResult.getResult()).isEqualTo(
                Map.of(dealer, new BigDecimal("-15000.0"), player, new BigDecimal("15000.0")));
    }
}
