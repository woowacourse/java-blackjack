package blackjack.view;

import java.util.Arrays;

public enum DealerResultStatus {
    WIN(PlayerResultStatus.LOSE, "승"),
    LOSE(PlayerResultStatus.WIN, "패"),
    PUSH(PlayerResultStatus.PUSH, "무"),
    ;
    private final PlayerResultStatus playerResultStatus;
    private final String result;

    DealerResultStatus(PlayerResultStatus playerResultStatus, String result) {
        this.playerResultStatus = playerResultStatus;
        this.result = result;
    }

    public static DealerResultStatus from(PlayerResultStatus playerResultStatus) {
        return Arrays.stream(values())
                .filter(status -> status.playerResultStatus.equals(playerResultStatus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않는 인자입니다."));
    }

    public String getResult() {
        return result;
    }
}
