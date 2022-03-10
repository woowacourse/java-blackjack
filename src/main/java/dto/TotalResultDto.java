package dto;

import java.util.Map;

public class TotalResultDto {
    private final long playerWinCount;
    private final long playerLoseCount;
    private final long playerDrawCount;
    private final Map<String, String> playersMatchResult;

    public TotalResultDto(long playerWinCount, long playerLoseCount, long playerDrawCount,
                          Map<String, String> playersMatchResult) {
        this.playerWinCount = playerWinCount;
        this.playerLoseCount = playerLoseCount;
        this.playerDrawCount = playerDrawCount;
        this.playersMatchResult = playersMatchResult;
    }

    public long getPlayerWinCount() {
        return playerWinCount;
    }

    public long getPlayerLoseCount() {
        return playerLoseCount;
    }

    public long getPlayerDrawCount() {
        return playerDrawCount;
    }

    public Map<String, String> getPlayersMatchResult() {
        return playersMatchResult;
    }
}
