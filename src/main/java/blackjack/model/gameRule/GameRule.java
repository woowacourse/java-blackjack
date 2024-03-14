package blackjack.model.gameRule;

import blackjack.model.deck.Deck;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Gamer;
import blackjack.model.gamer.Player;
import java.util.ArrayList;
import java.util.List;

public class GameRule {

    private static final PlayersResult playersResult = new PlayersResult();

    public static final int BUST_STANDARD_SCORE = 22;
    public static final int BLACKJACK_STANDARD_SCORE = 21;
    public static final int BLACKJACK_STANDARD_DECK_SIZE = 2;
    public static final int PLAYER_HIT_MAX_SCORE = 20;
    public static final int DEALER_HIT_MAX_SCORE = 16;

    private GameRule() {
    }

    public static void initialSetting(List<Player> players) {
        for (Player player : players) {
            playersResult.add(player, Result.NONE);
        }
    }

    public static void cardScoringRule(Deck deck) {
        int aceCount = deck.countElevenAce();

        while (aceCount > 0 && deck.calculateCardScore() >= BUST_STANDARD_SCORE) {
            deck.switchAceValueInRow();
            aceCount--;
        }
    }

    public static void initialResultRule(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            playerBlackjackRule(dealer, player);
        }
    }

    public static void finalResultRule(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            playerBustRule(dealer, player);
            gamerNotBustRule(dealer, player);
        }
    }

    public static List<Player> hitStayTargetPlayerDecisionRule(List<Player> players) {
        List<Player> hitStayTargetPlayer = new ArrayList<>();
        for (Player player : players) {
            addTargetPlayer(player, hitStayTargetPlayer);
        }

        return hitStayTargetPlayer;
    }

    public static void applyGameResultProfit(Dealer dealer, Player player) {
        Result playerResult = playersResult.findPlayerResult(player);
        player.applyResult(playerResult);
        dealer.payPlayerProfit(player);
    }

    private static void addTargetPlayer(Player player, List<Player> hitStayTargetPlayer) {
        Result playerResult = playersResult.findPlayerResult(player);
        if (playerResult == Result.NONE) {
            hitStayTargetPlayer.add(player);
        }
    }

    private static void playerBlackjackRule(Dealer dealer, Player player) {
        if (isBlackjack(player) && isBlackjack(dealer)) {
            playersResult.add(player, Result.PUSH);
        }
        if (isBlackjack(player) && !isBlackjack(dealer)) {
            playersResult.add(player, Result.BLACKJACK);
        }
        if (!isBlackjack(player) && isBlackjack(dealer)) {
            playersResult.add(player, Result.LOSE);
        }
    }

    private static void playerBustRule(Dealer dealer, Player player) {
        if (isBust(player)) {
            playersResult.add(player, Result.LOSE);
        }
        if (!isBust(player) && isBust(dealer)) {
            playersResult.add(player, Result.WIN);
        }
    }

    private static void gamerNotBustRule(Dealer dealer, Player player) {
        int dealerScore = dealer.totalScore();
        int playerScore = player.totalScore();
        if (!isBust(dealer) && dealerScore > playerScore) {
            playersResult.add(player, Result.LOSE);
        }
        if (!isBust(player) && dealerScore < playerScore) {
            playersResult.add(player, Result.WIN);
        }
        if (!isBust(player) && dealerScore == playerScore) {
            playersResult.add(player, Result.PUSH);
        }
    }

    private static boolean isBust(Gamer gamer) {
        int totalScore = gamer.totalScore();
        return totalScore >= BUST_STANDARD_SCORE;
    }

    private static boolean isBlackjack(Gamer gamer) {
        int deckSize = gamer.deckSize();
        int totalScore = gamer.totalScore();
        return deckSize == BLACKJACK_STANDARD_DECK_SIZE && totalScore == BLACKJACK_STANDARD_SCORE;
    }

    public static PlayersResult getPlayersResult() {
        return playersResult;
    }
}
