package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum BlackjackMatch {

    WIN_BLACKJACK("승", true),
    WIN("승", false),
    DRAW("무", false),
    LOSE("패", false),
    LOSE_BLACK_JACK("패", true);

    private final String result;
    private final boolean blackjack;

    BlackjackMatch(String result, boolean blackjack) {
        this.result = result;
        this.blackjack = blackjack;
    }

    public static BlackjackMatch of(Player player, Dealer dealer) {
        if (player.isBust() || dealer.isBust()) {
            return getWinLoseByBust(player);
        }
        if (player.isBlackjack() || dealer.isBlackjack()) {
            return getWinDrawByBlackjack(player, dealer);
        }
        return getWinDrawLoseByScore(player, dealer);
    }

    private static BlackjackMatch getWinLoseByBust(Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        return WIN;
    }

    private static BlackjackMatch getWinDrawByBlackjack(Player player, Dealer dealer) {
        if (isWinBlackjack(player, dealer)) {
            return WIN_BLACKJACK;
        }
        if (isDrawBlackjack(player, dealer)) {
            return DRAW;
        }
        return LOSE_BLACK_JACK;
    }

    private static boolean isWinBlackjack(Player player, Dealer dealer) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private static boolean isDrawBlackjack(Player player, Dealer dealer) {
        return player.isBlackjack() && dealer.isBlackjack();
    }

    private static BlackjackMatch getWinDrawLoseByScore(Player player, Dealer dealer) {
        if (player.getScore() > dealer.getScore()) {
            return WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return DRAW;
        }
        return LOSE;
    }

    public BlackjackMatch swapResult() {
        if (blackjack) {
            return swapResultBlackjack();
        }
        if (result.equals(WIN.getResult())) {
            return LOSE;
        }
        if (result.equals(LOSE.getResult())) {
            return WIN;
        }
        return DRAW;
    }

    private BlackjackMatch swapResultBlackjack() {
        if (result.equals(WIN_BLACKJACK.result)) {
            return LOSE_BLACK_JACK;
        }
        return WIN_BLACKJACK;
    }

    public String getResult() {
        return result;
    }

    public boolean isBlackjack() {
        return blackjack;
    }
}
