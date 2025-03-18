package blackjack.domain.participant;

import static blackjack.fixture.TestFixture.provideBiggerAceCards;
import static blackjack.fixture.TestFixture.provideBiggerAndSmallerAceCards;
import static blackjack.fixture.TestFixture.provideCards;
import static blackjack.fixture.TestFixture.provideEmptyCards;
import static blackjack.fixture.TestFixture.providePlayer;
import static blackjack.fixture.TestFixture.provideSmallerAceCards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Suit;
import blackjack.domain.result.ProfitResult;
import blackjack.domain.result.ResultStatus;
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
        assertThat(dealer).isEqualTo(new Dealer(hand));
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

    @DisplayName("딜러가 가진 카드의 합이 16 이하면 true를 반환한다.")
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
                        new Card(Suit.SPADE, Denomination.NINE)
                )), false),
                Arguments.of(new Hand(List.of(
                        new Card(Suit.SPADE, Denomination.A),
                        new Card(Suit.SPADE, Denomination.K)
                )), true),
                Arguments.of(new Hand(List.of(
                        new Card(Suit.SPADE, Denomination.K),
                        new Card(Suit.SPADE, Denomination.Q),
                        new Card(Suit.SPADE, Denomination.A)
                )), false)
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

    @DisplayName("우승 결과를 계산한다")
    @ParameterizedTest
    @MethodSource
    void calculateWinningResult(final ResultStatus playerResult, final int dealerProfit, final int playerProfit) {
        // Given
        dealer = new Dealer();
        final Player player = providePlayer("밍트", 10_000);
        final Map<Player, ResultStatus> playerScores = Map.of(player, playerResult);

        // When
        final ProfitResult profitResult = dealer.calculateProfit(playerScores);

        // Then
        assertThat(profitResult.getResult()).isEqualTo(Map.of(dealer, dealerProfit, player, playerProfit));
    }

    private static Stream<Arguments> calculateWinningResult() {
        return Stream.of(
                Arguments.of(ResultStatus.PUSH, 0, 0),
                Arguments.of(ResultStatus.LOSE, 10_000, -10_000),
                Arguments.of(ResultStatus.BLACKJACK, -15_000, 15_000),
                Arguments.of(ResultStatus.WIN, -10_000, 10_000)
        );
    }
}
