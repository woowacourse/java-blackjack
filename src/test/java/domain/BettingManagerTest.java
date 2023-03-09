package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingManagerTest {

    @Test
    @DisplayName("딜러와 플레이어의 결과에 따른 수익을 계산한다.")
    void createPlayerBettingAmounts() {
        List<Name> playerNames = List.of(new Name("aa"), new Name("bb"), new Name("cc"), new Name("dd"));
        List<BettingAmount> bettingAmounts = List.of(BettingAmount.fromPlayer(10000), BettingAmount.fromPlayer(10000),
                BettingAmount.fromPlayer(10000), BettingAmount.fromPlayer(10000));

        BettingManager bettingManager = new BettingManager(playerNames, bettingAmounts);

        assertThat(bettingManager.calculateTotalRevenue(createMapForTest())).containsExactlyInAnyOrderEntriesOf(
                getExpectedResult());
    }

    private Map<Name, GameResult> createMapForTest() {
        Map<Name, GameResult> gameResult = new LinkedHashMap<>();
        gameResult.put(new Name("aa"), GameResult.BLACKJACK);
        gameResult.put(new Name("bb"), GameResult.WIN);
        gameResult.put(new Name("cc"), GameResult.LOSE);
        gameResult.put(new Name("dd"), GameResult.DRAW);
        return gameResult;
    }

    private Map<String, Double> getExpectedResult() {
        Map<String, Double> revenues = new LinkedHashMap<>();
        revenues.put("딜러", (double) -15000);
        revenues.put("aa", (double) 15000);
        revenues.put("bb", (double) 10000);
        revenues.put("cc", (double) -10000);
        revenues.put("dd", (double) 0);
        return revenues;

    }

}