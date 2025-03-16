package blackjack.domain.participant;

import static blackjack.fixture.TestFixture.provideBiggerAceCards;
import static blackjack.fixture.TestFixture.provideBiggerAndSmallerAceCards;
import static blackjack.fixture.TestFixture.provideBlackjack;
import static blackjack.fixture.TestFixture.provideCards;
import static blackjack.fixture.TestFixture.provideEmptyCards;
import static blackjack.fixture.TestFixture.providePlayer;
import static blackjack.fixture.TestFixture.provideSmallerAceCards;
import static blackjack.fixture.TestFixture.provideUnder16Cards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardScore;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Shape;
import blackjack.domain.result.DealerWinningResult;
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
                        new Card(Shape.SPADE, CardScore.TEN),
                        new Card(Shape.SPADE, CardScore.NINE)
                )), false),
                Arguments.of(new Hand(List.of(
                        new Card(Shape.SPADE, CardScore.A),
                        new Card(Shape.SPADE, CardScore.K)
                )), true),
                Arguments.of(new Hand(List.of(
                        new Card(Shape.SPADE, CardScore.K),
                        new Card(Shape.SPADE, CardScore.Q),
                        new Card(Shape.SPADE, CardScore.A)
                )), false)
        );
    }

    @DisplayName("카드 합을 구한다")
    @ParameterizedTest
    @MethodSource
    void makeDealerWinningResult(final Hand hand, final int expected) {
        // given
        dealer.receiveCards(hand);

        // when & then
        assertThat(dealer.calculateScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> makeDealerWinningResult() {
        return Stream.of(
                Arguments.of(provideSmallerAceCards(), 18),
                Arguments.of(provideBiggerAceCards(), 21),
                Arguments.of(provideBiggerAndSmallerAceCards(), 17)
        );
    }

    @ParameterizedTest
    @MethodSource
    void 우승_결과를_계산한다(final Hand dealerHand, final Hand playerHand, final ResultStatus expected) {
        // Given
        Player player = providePlayer("밍트", 10_000);
        final Map<Player, Hand> playerScores = Map.of(player, playerHand);
        Dealer dealer1 = new Dealer(dealerHand);

        // When
        DealerWinningResult dealerWinningResult = dealer1.makeDealerWinningResult(playerScores);

        // Then
        assertThat(dealerWinningResult).isEqualTo(new DealerWinningResult(Map.of(player, expected)));
    }

    private static Stream<Arguments> 우승_결과를_계산한다() {
        return Stream.of(
                Arguments.of(provideBlackjack(), provideBlackjack(), ResultStatus.PUSH),
                Arguments.of(provideBlackjack(), provideUnder16Cards(), ResultStatus.WIN),
                Arguments.of(provideUnder16Cards(), provideBlackjack(), ResultStatus.BLACKJACK),

                Arguments.of(provideUnder16Cards(), provideEmptyCards(), ResultStatus.WIN),
                Arguments.of(provideEmptyCards(), provideUnder16Cards(), ResultStatus.LOSE),
                Arguments.of(provide16Cards(), provide16Cards(), ResultStatus.PUSH)
        );
    }

    private static Hand provide16Cards() {
        return new Hand(List.of(new Card(Shape.CLOB, CardScore.A), new Card(Shape.CLOB, CardScore.SIX)));
    }
}
