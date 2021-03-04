package blackjack.domain;

public enum WinnerFlag {
    WIN("승 "),
    DRAW("무 "),
    LOSE("패 ");

    private final String flagOutput;

    WinnerFlag(String flagOutput) {
        this.flagOutput = flagOutput;
    }

    public static void calculateResult(Dealer dealer, Player player) {
        int playerResult = player.calculateMaximumPoint();
        if (dealer.isBurst() && player.isBurst() || dealer.isSameThan(playerResult)) {
            player.matchResult(DRAW);
        }
        if (player.isBurst() || (!dealer.isBurst() && dealer.isBiggerThan(playerResult))) {
            player.matchResult(LOSE);
        }
        if ((dealer.isSmallerThan(playerResult) && !player.isBurst()) || dealer.isBurst()) {
            player.matchResult(WIN);
        }
    }

    public String getFlagOutput() {
        return flagOutput;
    }
}
