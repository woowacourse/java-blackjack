package domain;

import static domain.GameResult.BLACKJACK;
import static domain.GameResult.LOSE;
import static domain.GameResult.TIE;
import static domain.GameResult.WIN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingCalculatorTest {
    Map<PlayerName, GameResult> gameResult;
    Map<PlayerName, BettingMoney> bettingResult;
    BettingCalculator bettingCalculator;

    @BeforeEach
    void makeTest() {
        gameResult = Map.of(
                new PlayerName("김"), WIN,
                new PlayerName("이"), TIE,
                new PlayerName("박"), LOSE,
                new PlayerName("조"), BLACKJACK
        );

        bettingResult = Map.of(
                new PlayerName("김"), new BettingMoney(10000),
                new PlayerName("이"), new BettingMoney(10000),
                new PlayerName("박"), new BettingMoney(10000),
                new PlayerName("조"), new BettingMoney(10000)
        );

        bettingCalculator = new BettingCalculator(gameResult, bettingResult);
    }

    @Test
    @DisplayName("게임 결과에 따라 플레이어의 수익률을 계산합니다.")
    void calculatePlayerProfitTest() {
        SoftAssertions softAssertions = new SoftAssertions();
        Map<PlayerName, Integer> playerBettingBenefits = bettingCalculator.getTotalPlayerProfit();

        softAssertions.assertThat(playerBettingBenefits.get(new PlayerName("김")))
                .isEqualTo(10000);
        softAssertions.assertThat(playerBettingBenefits.get(new PlayerName("이")))
                .isEqualTo(0);
        softAssertions.assertThat(playerBettingBenefits.get(new PlayerName("박")))
                .isEqualTo(-10000);
        softAssertions.assertThat(playerBettingBenefits.get(new PlayerName("조")))
                .isEqualTo(15000);
    }

    @Test
    @DisplayName("게임 결과에 따라 딜러의 수익률을 계산합니다.")
    void calculateDealerProfitTest() {
        BettingCalculator bettingCalculator = new BettingCalculator(gameResult, bettingResult);
        assertThat(bettingCalculator.getTotalDealerProfit()).isEqualTo(-15000);
    }
}