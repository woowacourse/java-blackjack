package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.enums.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetAmountsTest {
    BetAmounts betAmounts = new BetAmounts(List.of("피즈", "이삭", "러키", "쿠다"));
    Map<String, Result> results = new HashMap<>();
    Map<String, Integer> expectedProfits = new HashMap<>();

    @BeforeEach
    void setUp() {
        betAmounts.addBetAmount("피즈", 10000);
        betAmounts.addBetAmount("이삭", 20000);
        betAmounts.addBetAmount("러키", 10000);
        betAmounts.addBetAmount("쿠다", 15000);

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
        Map<String, Integer> playerProfits = betAmounts.calculatePlayerProfits(results);
        assertThat(playerProfits).isEqualTo(expectedProfits);
    }

    @DisplayName("딜러의 수익을 정확히 계산한다.")
    @Test
    void 딜러의_수익을_정확히_계산한다() {
        Map<String, Integer> playerProfits = betAmounts.calculatePlayerProfits(results);
        assertThat(-25000).isEqualTo(betAmounts.calculateDealerProfit(playerProfits));
    }
}
