package domain.betting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BettingLogTest {
    @Test
    @DisplayName("BettingLog 생성")
    void create() {
        assertThat(new BettingLog("name", new BettingMoney("1000"))).isInstanceOf(BettingLog.class);
    }
}
