package domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import domain.BetAmount;
import domain.enums.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.BlackjackService;

public class BlackjackServiceTest {

    private final BlackjackService blackjackService = new BlackjackService();
    Map<String, BetAmount> betAmounts = new HashMap<>();
    Map<String, Result> results = new HashMap<>();
    Map<String, Integer> expectedProfits = new HashMap<>();

    @BeforeEach
    void setUp() {
        betAmounts.put("피즈", new BetAmount(10000));
        betAmounts.put("이삭", new BetAmount(20000));
        betAmounts.put("러키", new BetAmount(10000));
        betAmounts.put("쿠다", new BetAmount(15000));

        results.put("피즈", Result.LOSE);
        results.put("이삭", Result.WIN);
        results.put("러키", Result.WIN_BLACKJACK);
        results.put("쿠다", Result.DRAW);

        expectedProfits.put("피즈", -10000);
        expectedProfits.put("이삭", 20000);
        expectedProfits.put("러키", 15000);
        expectedProfits.put("쿠다", 0);
    }

    @DisplayName("모든 플레이어의 수익을 정확히 계산한다.")
    @Test
    void 모든_플레이어의_수익을_정확히_계산한다() {
        Map<String, Integer> playerProfits = blackjackService.calculateAllPlayerProfits(results, betAmounts);
        assertThat(playerProfits).isEqualTo(expectedProfits);
    }

    @DisplayName("딜러의 수익을 정확히 계산한다.")
    @Test
    void 딜러의_수익을_정확히_계산한다() {
        Map<String, Integer> playerProfits = blackjackService.calculateAllPlayerProfits(results, betAmounts);
        List<Integer> allProfits = playerProfits.values()
                .stream()
                .toList();
        assertThat(-25000).isEqualTo(blackjackService.calculateDealerProfit(allProfits));
    }
}
