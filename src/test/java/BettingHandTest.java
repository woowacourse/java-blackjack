import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.game.Result;
import domain.player.BettingHand;
import domain.player.BettingProfit;
import domain.player.Dealer;
import domain.player.Hand;
import domain.player.Money;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BettingHandTest {

    @Nested
    @DisplayName("calculateResult(): ")
    class CalculateResult {

        @DisplayName("플레이어 상태와 딜러 상태에 따라 결과를 계산한다")
        @ParameterizedTest
        @MethodSource("calculateResultCases")
        void calculateResult(Hand playerHand, Hand dealerHand, Result expected) {
            BettingHand bettingHand = BettingHand.of(playerHand, bet(1000));
            Dealer dealer = Dealer.from(dealerHand);

            // when
            Result result = bettingHand.determineResult(dealer); // calculateResult -> determineResult 직관적 호출로 변경

            assertThat(result).isEqualTo(expected);
        }

        private static Stream<Arguments> calculateResultCases() {
            return Stream.of(
                    // "플레이어와 딜러가 모두 블랙잭이면 무승부이다"
                    Arguments.of(hand(Rank.ACE, Rank.KING), hand(Rank.ACE, Rank.QUEEN), Result.DRAW),
                    // "플레이어만 블랙잭이면 승리한다"
                    Arguments.of(hand(Rank.ACE, Rank.KING), hand(Rank.TEN, Rank.NINE), Result.WIN),
                    // "딜러 점수가 더 높으면 플레이어는 패배한다",
                    Arguments.of(hand(Rank.TEN, Rank.EIGHT), hand(Rank.TEN, Rank.NINE), Result.LOSE),
                    // "플레이어와 딜러 점수가 같으면 무승부이다",
                    Arguments.of(hand(Rank.TEN, Rank.EIGHT), hand(Rank.NINE, Rank.NINE), Result.DRAW),
                    // "플레이어 점수가 더 높으면 승리한다",
                    Arguments.of(hand(Rank.TEN, Rank.NINE), hand(Rank.TEN, Rank.EIGHT), Result.WIN)
            );
        }

        @DisplayName("플레이어가 버스트이면 패배한다")
        @Test
        void calculateResultLoseWhenPlayerBust() {
            Hand playerHand = hand(Rank.KING, Rank.QUEEN);
            playerHand.addCard(TestDefaults.getCardByRank(Rank.TWO));

            BettingHand bettingHand = BettingHand.of(playerHand, bet(1000));
            Dealer dealer = Dealer.from(hand(Rank.TEN, Rank.NINE));

            //when
            Result result = bettingHand.determineResult(dealer);

            assertThat(result).isEqualTo(Result.LOSE);
        }

        @DisplayName("딜러가 버스트이면 플레이어가 승리한다")
        @Test
        void calculateResultWinWhenDealerBust() {
            BettingHand bettingHand = BettingHand.of(hand(Rank.TEN, Rank.NINE), bet(1000));

            Hand dealerHand = hand(Rank.KING, Rank.QUEEN);
            dealerHand.addCard(TestDefaults.getCardByRank(Rank.TWO));
            Dealer dealer = Dealer.from(dealerHand);

            //when
            Result result = bettingHand.determineResult(dealer);

            assertThat(result).isEqualTo(Result.WIN);
        }
    }

    @Nested
    @DisplayName("calculateProfit(): ")
    class CalculateProfit {

        @DisplayName("게임 결과에 따라 수익을 계산한다")
        @ParameterizedTest
        @MethodSource("calculateProfitCases")
        void calculateProfit(Hand playerHand, Hand dealerHand, int expected) {
            BettingHand bettingHand = BettingHand.of(playerHand, bet(1000));
            Dealer dealer = Dealer.from(dealerHand);

            // when
            int profit = bettingHand.calculateProfit(dealer);

            assertThat(profit).isEqualTo(expected);
        }

        private static Stream<Arguments> calculateProfitCases() {
            BettingProfit bettingProfit = bet(1000);
            return Stream.of(
                    // "블랙잭으로 승리하면 블랙잭 승리 수익을 반환한다"
                    Arguments.of(hand(Rank.ACE, Rank.KING), hand(Rank.TEN, Rank.NINE),
                            bettingProfit.blackjackWinProfit()),
                    // "일반 승리이면 승리 수익을 반환한다"
                    Arguments.of(hand(Rank.TEN, Rank.NINE), hand(Rank.TEN, Rank.EIGHT), bettingProfit.winProfit()),
                    // "무승부이면 베팅 금액을 반환한다"
                    Arguments.of(hand(Rank.TEN, Rank.EIGHT), hand(Rank.NINE, Rank.NINE), bettingProfit.refundProfit()),
                    // "패배이면 잃은 금액을 반환한다"
                    Arguments.of(hand(Rank.TEN, Rank.EIGHT), hand(Rank.TEN, Rank.NINE), bettingProfit.loseProfit()),
                    // "플레이어와 딜러가 모두 블랙잭이면 무승부 수익을 반환한다"
                    Arguments.of(hand(Rank.ACE, Rank.KING), hand(Rank.ACE, Rank.QUEEN), bettingProfit.refundProfit())
            );
        }
    }

    private static BettingProfit bet(int amount) {
        return new BettingProfit(new Money(amount));
    }

    private static Hand hand(Rank... ranks) {
        List<Card> cards = new ArrayList<>(TestDefaults.getCardsByRanks(List.of(ranks)));
        return new Hand(cards);
    }
}
