package domain.betting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BettingLogsTest {
    @Test
    @DisplayName("bettingLogs 생성")
    void create() {
        List<BettingLog> bettingLogsList = new ArrayList<>();

        bettingLogsList.add(new BettingLog("userA", new BettingMoney("1000")));
        bettingLogsList.add(new BettingLog("userB", new BettingMoney("2000")));

        assertThat(new BettingLogs(bettingLogsList)).isInstanceOf(BettingLogs.class);
    }

    @Test
    @DisplayName("getUserLog 확인")
    void getUserLog() {
        List<BettingLog> bettingLogsList = new ArrayList<>();

        bettingLogsList.add(new BettingLog("userA", new BettingMoney("1000")));
        bettingLogsList.add(new BettingLog("userB", new BettingMoney("2000")));

        BettingLogs bettingLogs = new BettingLogs(bettingLogsList);

        assertThat(bettingLogs.getUserLog("userA")).isInstanceOf(BettingLog.class);
        assertThat(bettingLogs.getUserLog("userA").getName()).isEqualTo("userA");
        assertThat(bettingLogs.getUserLog("userA").getBettingMoney().toString()).isEqualTo("1000");
    }
}
