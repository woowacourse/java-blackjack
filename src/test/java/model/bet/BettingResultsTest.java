package model.bet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingResultsTest {

    @DisplayName("플레이어의 이름을 통해 베팅 수익 결과를 확인한다.")
    @Test
    void getBettingResultByNameTest() {
        BettingResults bettingResults = new BettingResults(Map.of(
                "pobi", 10_000,
                "jason", -30_000
        ));

        assertAll(
                () -> assertThat(bettingResults.getBettingResultByName("pobi")).isEqualTo(10_000),
                () -> assertThat(bettingResults.getBettingResultByName("jason")).isEqualTo(-30_000)
        );
    }
}
