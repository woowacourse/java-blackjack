package domain.user;

import java.util.Map;

public class AllWinningAmountDto {
    private final int winningAmountOfDealer;
    private final Map<String, Integer> playerNameWinningAmounts;

    public AllWinningAmountDto(int winningAmountOfDealer, Map<String, Integer> playerNameWinningAmounts) {
        this.winningAmountOfDealer = winningAmountOfDealer;
        this.playerNameWinningAmounts = playerNameWinningAmounts;
    }

    public int getWinningAmountOfDealer() {
        return this.winningAmountOfDealer;
    }

    public Map<String, Integer> getPlayerNameWinningAmounts() {
        return this.playerNameWinningAmounts;
    }
}
