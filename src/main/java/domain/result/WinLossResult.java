package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

public enum WinLossResult {

    WIN("승"),
    LOSS("패"),
    DRAW("무"),
    BUST("버스트"),
    BLACKJACK("블랙잭"),
    NONE("");

    private final String winLossMessage;

    WinLossResult(final String winLossMessage) {
        this.winLossMessage = winLossMessage;
    }

    public static WinLossResult of(final int winLossOption) {
        if (winLossOption == 1) {
            return WIN;
        }
        if (winLossOption == -1) {
            return LOSS;
        }
        if (winLossOption == 0) {
            return DRAW;
        }
        return NONE;
    }

    public static WinLossResult from(final Dealer dealer, final Player player) {
        if(dealer.getHandTotal() > player.getHandTotal()){
            return LOSS;
        }
        return NONE;
    }

    public String getWinLossMessage() {
        return winLossMessage;
    }
}
