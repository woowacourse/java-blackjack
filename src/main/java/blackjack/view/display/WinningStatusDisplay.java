package blackjack.view.display;

import blackjack.domain.WinningStatus;

import java.util.EnumMap;

public enum WinningStatusDisplay {
    BLACKJACK_WIN(WinningStatus.BLACKJACK_WIN, "승리"),
    WIN(WinningStatus.WIN, "승리"),
    DRAW(WinningStatus.DRAW, "무승부"),
    LOSE(WinningStatus.LOSE, "패배"),
    ;
    
    private static final EnumMap<WinningStatus, String> WINNING_STATUS = new EnumMap<>(WinningStatus.class);
    
    static {
        for (WinningStatusDisplay winningStatus : WinningStatusDisplay.values()) {
            WINNING_STATUS.put(winningStatus.winningStatus, winningStatus.winningStatusDisplay);
        }
    }
    
    private final WinningStatus winningStatus;
    private final String winningStatusDisplay;
    
    WinningStatusDisplay(final WinningStatus winningStatus, final String winningStatusDisplay) {
        this.winningStatus = winningStatus;
        this.winningStatusDisplay = winningStatusDisplay;
    }
    
    public static String parseWinningStatus(WinningStatus winningStatus) {
        return WINNING_STATUS.get(winningStatus);
    }
}
