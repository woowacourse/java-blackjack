package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    @DisplayName("딜러의 승 수는 플레이어가 진 수와 같다.")
    void getDealerWin() {
        Dealer dealer = Dealer.create();
        Map<String, MatchResult> playerResults = Map.of("pobi", MatchResult.LOSE, "jason", MatchResult.WIN);

        Result result = new Result(dealer, playerResults);

        assertThat(result.getDealerWin()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러의 패 수는 플레이어가 이긴 수와 같다.")
    void getDealerLose() {
        Dealer dealer = Dealer.create();
        Map<String, MatchResult> playerResults = Map.of("pobi", MatchResult.WIN, "jason", MatchResult.LOSE);

        Result result = new Result(dealer, playerResults);

        assertThat(result.getDealerLose()).isEqualTo(1);
    }
}
