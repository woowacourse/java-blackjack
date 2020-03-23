package domain.profit;

import domain.player.Dealer;
import domain.player.Player;

import java.util.Arrays;
import java.util.List;

public class ProfitFactory {
    private static final List<ProfitStrategy> strategies = Arrays.asList(
            new BothBlackJack(), new PlayerBlackJack(), new PlayerLoose(), new PlayerWin()
    );

    private ProfitFactory() {
    }

    public static ProfitStrategy create(Player player, Dealer dealer) {
        if (player == null || dealer == null) {
            throw new NullPointerException("플레이어 또는 딜러를 입력하지 않았습니다.");
        }

        return strategies.stream()
                .filter(ps -> ps.condition(player, dealer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("결과를 반영할 규칙이 없습니다."));
    }
}
