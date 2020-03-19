package domain.betting;

import java.util.List;

public class BettingLogs {
    private final List<BettingLog> bettingLogs;

    public BettingLogs(final List<BettingLog> bettingLogs) {
        this.bettingLogs = bettingLogs;
    }

    public BettingLog getUserLog(String name) {
        return bettingLogs.stream()
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
