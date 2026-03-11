import static org.assertj.core.api.Assertions.assertThat;

import domain.Result;
import domain.card.Card;
import domain.card.Rank;
import domain.player.Bet;
import domain.player.Dealer;
import domain.player.Hand;
import domain.player.Money;
import domain.player.PlayerStatus;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerStatusTest {


    @Nested
    @DisplayName("calculateResult(): ")
    class CalculateResult {
        @DisplayName("플레이어 상태와 딜러 상태에 따라 결과를 계산한다")
        @ParameterizedTest()
        @MethodSource("calculateResultCases")
        void calculateResult(
                Hand playerHand,
                Hand dealerHand,
                Result expected
        ) {
            PlayerStatus playerStatus = new PlayerStatus(playerHand, bet(1000));
            Dealer dealer = new Dealer(dealerHand);

            // when
            Result result = playerStatus.calculateResult(dealer);

            assertThat(result).isEqualTo(expected);
        }

        private static Stream<Arguments> calculateResultCases() {
            return Stream.of(
                    Arguments.of(
//                        "플레이어와 딜러가 모두 블랙잭이면 무승부이다"
                            hand(Rank.ACE, Rank.KING),
                            hand(Rank.ACE, Rank.QUEEN),
                            Result.DRAW
                    ),
                    Arguments.of(
//                        "플레이어만 블랙잭이면 승리한다"
                            hand(Rank.ACE, Rank.KING),
                            hand(Rank.TEN, Rank.NINE),
                            Result.WIN
                    ),
                    Arguments.of(
//                        "딜러 점수가 더 높으면 플레이어는 패배한다",
                            hand(Rank.TEN, Rank.EIGHT),
                            hand(Rank.TEN, Rank.NINE),
                            Result.LOSE
                    ),
                    Arguments.of(
//                        "플레이어와 딜러 점수가 같으면 무승부이다",
                            hand(Rank.TEN, Rank.EIGHT),
                            hand(Rank.NINE, Rank.NINE),
                            Result.DRAW
                    ),
                    Arguments.of(
//                        "플레이어 점수가 더 높으면 승리한다",
                            hand(Rank.TEN, Rank.NINE),
                            hand(Rank.TEN, Rank.EIGHT),
                            Result.WIN
                    )
            );
        }

        @DisplayName("플레이어가 버스트이면 패배한다")
        @Test
        void calculateResultLoseWhenPlayerBust() {
            Hand playerHand = hand(Rank.KING, Rank.QUEEN);
            playerHand.add(TestDefaults.getCardByRank(Rank.TWO));

            PlayerStatus playerStatus = new PlayerStatus(playerHand, bet(1000));
            Dealer dealer = new Dealer(hand(Rank.TEN, Rank.NINE));

            //when
            Result result = playerStatus.calculateResult(dealer);

            assertThat(result).isEqualTo(Result.LOSE);
        }

        @DisplayName("딜러가 버스트이면 플레이어가 승리한다")
        @Test
        void calculateResultWinWhenDealerBust() {
            PlayerStatus playerStatus = new PlayerStatus(hand(Rank.TEN, Rank.NINE), bet(1000));

            Hand dealerHand = hand(Rank.KING, Rank.QUEEN);
            dealerHand.add(TestDefaults.getCardByRank(Rank.TWO));
            Dealer dealer = new Dealer(dealerHand);

            //when
            Result result = playerStatus.calculateResult(dealer);

            assertThat(result).isEqualTo(Result.WIN);
        }
    }


    @Nested
    @DisplayName("calculateProfit(): ")
    class CalculateProfit {

        @DisplayName("게임 결과에 따라 수익을 계산한다")
        @ParameterizedTest()
        @MethodSource("calculateProfitCases")
        void calculateProfit(
                Hand playerHand,
                Hand dealerHand,
                int expected
        ) {
            Bet bet = bet(1000);
            PlayerStatus playerStatus = new PlayerStatus(playerHand, bet);
            Dealer dealer = new Dealer(dealerHand);

            // when
            int profit = playerStatus.calculateProfit(dealer);

            assertThat(profit).isEqualTo(expected);
        }

        private static Stream<Arguments> calculateProfitCases() {
            Bet bet = bet(1000);

            return Stream.of(
                    Arguments.of(
//                        "블랙잭으로 승리하면 블랙잭 승리 수익을 반환한다"
                            hand(Rank.ACE, Rank.KING),
                            hand(Rank.TEN, Rank.NINE),
                            bet.blackjackWinProfit()
                    ),
                    Arguments.of(
//                        "일반 승리이면 승리 수익을 반환한다"
                            hand(Rank.TEN, Rank.NINE),
                            hand(Rank.TEN, Rank.EIGHT),
                            bet.winProfit()
                    ),
                    Arguments.of(
//                        "무승부이면 베팅 금액을 반환한다"
                            hand(Rank.TEN, Rank.EIGHT),
                            hand(Rank.NINE, Rank.NINE),
                            bet.refundProfit()
                    ),
                    Arguments.of(
//                        "패배이면 잃은 금액을 반환한다"
                            hand(Rank.TEN, Rank.EIGHT),
                            hand(Rank.TEN, Rank.NINE),
                            bet.loseProfit()
                    ),
                    Arguments.of(
//                        "플레이어와 딜러가 모두 블랙잭이면 무승부 수익을 반환한다"
                            hand(Rank.ACE, Rank.KING),
                            hand(Rank.ACE, Rank.QUEEN),
                            bet.refundProfit()
                    )
            );
        }

    }


    private static Bet bet(int amount) {
        return new Bet(new Money(amount));
    }

    private static Hand hand(Rank... ranks) {
        List<Card> cards = TestDefaults.getCardsByRanks(List.of(ranks));
        return new Hand(cards);
    }
}