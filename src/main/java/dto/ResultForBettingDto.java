package dto;

import domain.participant.WinStatus;
import domain.vo.Name;

public class ResultForBettingCalculateDto {
    private final Name name;
    private final WinStatus winStatus;
    private final boolean isBlackjack;

    public ResultForBettingCalculateDto(Name name, WinStatus winStatus, boolean isBlackjack) {
        this.name = name;
        this.winStatus = winStatus;
        this.isBlackjack = isBlackjack;
    }

    public Name getName() {
        return name;
    }

    public WinStatus getWinStatus() {
        return winStatus;
    }

    public boolean isBlackjack() {
        return isBlackjack;
    }
}
