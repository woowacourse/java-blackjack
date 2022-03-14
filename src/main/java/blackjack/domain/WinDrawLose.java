package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum WinDrawLose {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String name;

    WinDrawLose(String name) {
        this.name = name;
    }

    public static WinDrawLose judgeDealerWinDrawLose(Player dealer, Player guest) {
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
            return WIN;
        }
        if (dealer) {
            return LOSE;
        }
        return null;
    }

    private static WinDrawLose existBlackjack(Player dealer, Player guest) {
        if (dealer.isBlackjack() && guest.isBlackjack()) {
            return DRAW;
        }
        if (dealer.isBlackjack()) {
            return WIN;
        }
        if (guest.isBlackjack()) {
            return LOSE;
        }
        return null;
    }

    private static WinDrawLose compareScore(Player dealer, Player guest) {
        int dealerScore = dealer.getCards().calculateScore();
        int guestScore = guest.getCards().calculateScore();
        if (dealerScore > guestScore) {
            return WIN;
        }
        if (guestScore > dealerScore) {
            return LOSE;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }

    public WinDrawLose reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
