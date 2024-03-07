package blackjack.model;

import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Player;

public class GameRule {

    private static final int BUST_STANDARD = 21;
    private static final int PLAYER_HIT_MAX_SCORE = 21;
    private static final int DEALER_HIT_MAX_SCORE = 16;

    public static void adjustAceValue(HandDeck handDeck) {
        int aceCount = handDeck.countElevenAce();

        while (aceCount > 0 && handDeck.calculateTotalScore() > BUST_STANDARD) {
            handDeck.switchAceValueInRow();
            aceCount--;
        }
    }

    private static boolean isBusted(int cardScore) {
        return cardScore > BUST_STANDARD;
    }

    private static boolean isBlackjack(int cardScore) {
        return cardScore == BUST_STANDARD;
    }

    public static boolean playerHitRule(int cardScore) {
        return cardScore <= PLAYER_HIT_MAX_SCORE;
    }

    public static boolean dealerHitRule(int cardScore) {
        return cardScore <= DEALER_HIT_MAX_SCORE;
    }

    public static int selectWinner(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateTotalScore();
        int playerScore = player.calculateTotalScore();

        boolean isDealerBust = isBusted(dealerScore);
        boolean isPlayerBust = isBusted(playerScore);

        if (isDealerBust && !isPlayerBust) {
            return -1;
        }

        if (!isDealerBust && isPlayerBust) {
            return 1;
        }

        if (isDealerBust && isPlayerBust) {
            return 1;
        }

        if (dealerScore == playerScore && isBlackjack(dealerScore)) {
            return -1;
        }

        if (dealerScore == playerScore) {
            return 0;
        }

        if (dealerScore > playerScore) {
            return 1;
        }

        return -1;
    }
}
