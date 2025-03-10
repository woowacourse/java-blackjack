package blackjack.model;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;

public enum MatchResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    private final String label;

    MatchResult(String label) {
        this.label = label;
    }

    public static MatchResult judge(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        if (isBothBlackjack(player, dealer)) {
            return DRAW;
        }
        if (player.isBlackjack()) {
            return WIN;
        }
        if (dealer.isBlackjack()) {
            return LOSE;
        }
        return determineByCompareTotal(player.getTotal(), dealer.getTotal());
    }

    private static boolean isBothBlackjack(Player player, Dealer dealer) {
        return player.isBlackjack() && dealer.isBlackjack();
    }

    private static MatchResult determineByCompareTotal(int playerTotal, int dealerTotal) {
        if (playerTotal > dealerTotal) {
            return WIN;
        }
        if (playerTotal < dealerTotal) {
            return LOSE;
        }
        return DRAW;
    }

    public static MatchResult reverse(MatchResult matchResult) {
        if (matchResult == WIN) {
            return LOSE;
        }
        if (matchResult == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getLabel() {
        return label;
    }
}
