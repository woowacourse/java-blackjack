package blackjack.view.display;

import blackjack.domain.WinningStatus;

import java.util.Arrays;

public enum WinningStatusDisplay {
    BLACKJACK_WIN(WinningStatus.BLACKJACK_WIN, "승리"),
    WIN(WinningStatus.WIN, "승리"),
    DRAW(WinningStatus.DRAW, "무승부"),
    LOSE(WinningStatus.LOSE, "패배"),
    ;
    
    private final WinningStatus winningStatus;
    private final String winningStatusDisplay;
    
    WinningStatusDisplay(final WinningStatus winningStatus, final String winningStatusDisplay) {
        this.winningStatus = winningStatus;
        this.winningStatusDisplay = winningStatusDisplay;
    }
    
    public static String parseWinningStatus(WinningStatus winningStatus) {
        return Arrays.stream(WinningStatusDisplay.values())
                .filter(status -> status.winningStatus.equals(winningStatus))
                .findAny()
                .map(status -> status.winningStatusDisplay)
                .orElseThrow();
    }
}
