package blackjack.model;

import blackjack.model.deck.HandDeck;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Player;
import blackjack.model.result.GameResult;

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

    public static boolean playerHitRule(int cardScore) {
        return cardScore <= PLAYER_HIT_MAX_SCORE;
    }

    public static boolean dealerHitRule(int cardScore) {
        return cardScore <= DEALER_HIT_MAX_SCORE;
    }

    public static void decideWinner(Dealer dealer, Player player, GameResult gameResult) {
        int dealerScore = dealer.calculateTotalScore();
        int playerScore = player.calculateTotalScore();

        if (dealerWinRule(dealerScore, playerScore)) {
            decideDealerWin(player, gameResult);
            return;
        }
        if (playerWinRule(dealerScore, playerScore)) {
            decidePlayerWin(player, gameResult);
            return;
        }
        decideTie(player, gameResult);
    }

    private static boolean isBusted(int cardScore) {
        return cardScore > BUST_STANDARD;
    }

    private static boolean isBlackjack(int cardScore) {
        return cardScore == BUST_STANDARD;
    }

    private static boolean playerWinRule(int dealerScore, int playerScore) {
        if (isBlackjack(playerScore) && isBlackjack(dealerScore)) {
            return true;
        }
        if (!isBusted(playerScore) && isBusted(dealerScore)) {
            return true;
        }
        if (!isBusted(playerScore) && playerScore > dealerScore) {
            return true;
        }
        return false;
    }

    private static boolean dealerWinRule(int dealerScore, int playerScore) {
        if (isBusted(playerScore) && isBusted(dealerScore)) {
            return true;
        }
        if (isBusted(playerScore) && !isBusted(dealerScore)) {
            return true;
        }
        if (!isBusted(dealerScore) && playerScore < dealerScore) {
            return true;
        }
        return false;
    }

    private static void decideDealerWin(Player player, GameResult gameResult) {
        gameResult.addDealerWin();
        gameResult.addPlayerLose(player);
    }

    private static void decidePlayerWin(Player player, GameResult gameResult) {
        gameResult.addDealerLose();
        gameResult.addPlayerWin(player);
    }

    private static void decideTie(Player player, GameResult gameResult) {
        gameResult.addDealerTie();
        gameResult.addPlayerTie(player);
    }
}
