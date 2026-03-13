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
        Map<String, Boolean> playerResults = Map.of("pobi", false, "jason", false);

        Result result = new Result(dealer, playerResults);

        assertThat(result.getDealerWin()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 패 수는 플레이어가 이긴 수와 같다.")
    void getDealerLose() {
        Dealer dealer = Dealer.create();
        Map<String, Boolean> playerResults = Map.of("pobi", true, "jason", false);

        Result result = new Result(dealer, playerResults);

        assertThat(result.getDealerLose()).isEqualTo(1);
    }
}