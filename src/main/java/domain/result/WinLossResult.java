package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

public enum WinLossResult {

    WIN("승"),
    LOSS("패"),
    DRAW("무"),
    BLACKJACK_WIN("블랙잭"),
    NONE("");

    private final String winLossMessage;

    WinLossResult(final String winLossMessage) {
        this.winLossMessage = winLossMessage;
    }

    public static WinLossResult from(final Dealer dealer, final Player player) {
        return computePlayerResult(dealer, player);
    }

    private static WinLossResult computePlayerResult(final Dealer dealer, final Player player) {
        if (player.isHandBust()) return LOSS;
        if (player.isBlackJack() && dealer.isBlackJack()) return DRAW;
        if (player.isBlackJack() && !dealer.isBlackJack()) return BLACKJACK_WIN;
        if (!player.isBlackJack() && dealer.isBlackJack()) return LOSS;
        if (player.getHandTotal() > dealer.getHandTotal()) return WIN;
        if (player.getHandTotal() < dealer.getHandTotal()) return LOSS;
        return DRAW;
    }

    public String getWinLossMessage() {
        return winLossMessage;
    }
}
