package domain.bet;

import static org.assertj.core.api.Assertions.assertThat;

import domain.enums.GameResult;
import domain.participant.Name;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BetProfitTest {

    private Name firstPlayer;
    private Name secondPlayer;
    private BetProfit betProfit;

    @BeforeEach
    void set_up() {
        firstPlayer = new Name("피즈");
        secondPlayer = new Name("스타크");
        betProfit = new BetProfit(List.of(firstPlayer, secondPlayer));
    }

    @DisplayName("플레이어 승리 시 1배의 수익이 난다.")
    @Test
    void 플레이어_승리_시_1배의_수익() {
        //given
        Map<Name, Integer> betHistory = Map.of(firstPlayer, 10_000);
        //when
        betProfit.calculateProfit(Map.of(firstPlayer, GameResult.WIN), betHistory);
        //then
        assertThat(betProfit.getPlayerBetProfit().get(firstPlayer)).isEqualTo(10_000);
    }

    @DisplayName("플레이어 패배 시 전액 손실이 된다.")
    @Test
    void 플레이어_패배_시_전액_손실() {
        //given
        Map<Name, Integer> betHistory = Map.of(firstPlayer, 10_000);
        //when
        betProfit.calculateProfit(Map.of(firstPlayer, GameResult.LOSE), betHistory);
        //then
        assertThat(betProfit.getPlayerBetProfit().get(firstPlayer)).isEqualTo(-10_000);
    }

    @DisplayName("플레이어는 무승부 시 0원의 수익을 가진다.")
    @Test
    void 무승부_0원_수익() {
        //given
        Map<Name, Integer> betHistory = Map.of(firstPlayer, 10_000);
        //when
        betProfit.calculateProfit(Map.of(firstPlayer, GameResult.DRAW), betHistory);
        //then
        assertThat(betProfit.getPlayerBetProfit().get(firstPlayer)).isEqualTo(0);
    }

    private static Stream<Arguments> blackjackProfitTest() {
        return Stream.of(
                Arguments.of(10_000, 15_000),
                Arguments.of(33_333, 49_999)
        );
    }

    @DisplayName("첫 카드 2장 블랙잭으로 승리 시 1.5배 수익이 나는지 확인한다.")
    @ParameterizedTest
    @MethodSource("blackjackProfitTest")
    void 첫_카드_2장_승리_시_1_5배_수익(int bettingMoney, int expectedProfit) {
        //given
        Map<Name, Integer> betHistory = Map.of(firstPlayer, bettingMoney);
        //when
        betProfit.calculateProfit(Map.of(firstPlayer, GameResult.BLACKJACK_WIN), betHistory);
        //then
        assertThat(betProfit.getPlayerBetProfit().get(firstPlayer)).isEqualTo(expectedProfit);
    }

    private static Stream<Arguments> dealerProfitTest() {
        return Stream.of(
                Arguments.of(List.of(10_000, 20_000), List.of(GameResult.WIN, GameResult.LOSE), 10_000),
                Arguments.of(List.of(20_000, 20_000), List.of(GameResult.WIN, GameResult.WIN), -40_000),
                Arguments.of(List.of(20_000, 20_000), List.of(GameResult.BLACKJACK_WIN, GameResult.WIN), -50_000),
                Arguments.of(List.of(20_000, 20_000), List.of(GameResult.LOSE, GameResult.DRAW), 20_000)
        );
    }

    @DisplayName("딜러의 수익금은 플레이어의 수익에서 음수가 된 값인지 확인한다.")
    @ParameterizedTest
    @MethodSource("dealerProfitTest")
    void 딜러_수익금_계산_테스트(List<Integer> bettingMoneys, List<GameResult> gameResults, int expectedProfit) {
        //given
        //when
        betProfit.calculateProfit(
                Map.of(
                        firstPlayer, gameResults.getFirst(),
                        secondPlayer, gameResults.getLast()
                ),
                Map.of(
                        firstPlayer, bettingMoneys.getFirst(),
                        secondPlayer, bettingMoneys.getLast()
                )
        );
        //then
        int profit = betProfit.getDealerBetProfit();
        assertThat(profit).isEqualTo(expectedProfit);
    }
}
