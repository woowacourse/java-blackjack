package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum WinnerFlag {
    WIN("승 "),
    DRAW("무 "),
    LOSE("패 ");

    private final String flagOutput;

    WinnerFlag(String flagOutput) {
        this.flagOutput = flagOutput;
    }

    public static void calculateResult(Dealer dealer, Player player) {
        int playerPoints = player.makeFinalPoint();
        int dealerPoints = dealer.makeFinalPoint();
        if (dealer.isBurst(dealerPoints) && player.isBurst(playerPoints) || dealer.isSameThan(playerPoints)) {
            player.matchResult(DRAW);
        }
        if (player.isBurst(playerPoints) || (!dealer.isBurst(dealerPoints) && dealer.isBiggerThan(playerPoints))) {
            player.matchResult(LOSE);
        }
        if ((dealer.isSmallerThan(playerPoints) && !player.isBurst(playerPoints)) || dealer.isBurst(dealerPoints)) {
            player.matchResult(WIN);
        }
    }

    public String getFlagOutput() {
        return flagOutput;
    }
}
