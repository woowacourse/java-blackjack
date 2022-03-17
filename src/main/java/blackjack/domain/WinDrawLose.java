package blackjack.domain;

import blackjack.domain.player.Player;

public enum WinDrawLose {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String name;

    WinDrawLose(String name) {
        this.name = name;
    }

    public static WinDrawLose judgePlayerWinDrawLose(Player dealer, Player guest) {
        WinDrawLose bust = existBust(guest.isBust(), dealer.isBust());
        if (bust != null) {
            return bust;
        }
        WinDrawLose blackjack = existBlackjack(dealer, guest);
        if (blackjack != null) {
            return blackjack;
        }
        return compareScore(dealer, guest);
    }

    private static WinDrawLose existBust(boolean guest, boolean dealer) {
        if (guest) {
            return LOSE;
        }
        if (dealer) {
            return WIN;
        }
        return null;
    }

    private static WinDrawLose existBlackjack(Player dealer, Player guest) {
        if (dealer.isBlackjack() && guest.isBlackjack()) {
            return DRAW;
        }
        if (dealer.isBlackjack()) {
            return LOSE;
        }
        if (guest.isBlackjack()) {
            return WIN;
        }
        return null;
    }

    private static WinDrawLose compareScore(Player dealer, Player guest) {
        int dealerScore = dealer.getCards().calculateScore();
        int guestScore = guest.getCards().calculateScore();
        if (dealerScore > guestScore) {
            return LOSE;
        }
        if (guestScore > dealerScore) {
            return WIN;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }
}
