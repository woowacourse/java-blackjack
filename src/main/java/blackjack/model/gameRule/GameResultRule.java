package blackjack.model.gameRule;

import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Gamer;
import blackjack.model.gamer.Player;
import blackjack.model.result.PlayersResult;
import blackjack.model.result.Result;
import java.util.ArrayList;
import java.util.List;

public class GameResultRule {

    private final PlayersResult playersResult = new PlayersResult();

    protected GameResultRule() {
    }

    public void initializePlayerResults(List<Player> players) {
        for (Player player : players) {
            playersResult.add(player, Result.NONE);
        }
    }

    public void InitialResultRule(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            playerBlackjackRule(dealer, player);
        }
    }

    public void finalResultRule(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            playerBustRule(dealer, player);
            gamerNotBustRule(dealer, player);
        }
    }

    public List<Player> hitStayTargetPlayerDecisionRule(List<Player> players) {
        List<Player> hitStayTargetPlayer = new ArrayList<>();
        for (Player player : players) {
            addTargetPlayer(player, hitStayTargetPlayer);
        }

        return hitStayTargetPlayer;
    }

    public void applyGameResultProfit(Dealer dealer, Player player) {
        Result playerResult = playersResult.findPlayerResult(player);
        player.applyResult(playerResult);
        dealer.payPlayerProfit(player);
    }

    private void addTargetPlayer(Player player, List<Player> hitStayTargetPlayer) {
        Result playerResult = playersResult.findPlayerResult(player);
        if (playerResult == Result.NONE) {
            hitStayTargetPlayer.add(player);
        }
    }

    private void playerBlackjackRule(Dealer dealer, Player player) {
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

    private void playerBustRule(Dealer dealer, Player player) {
        if (isBust(player)) {
            playersResult.add(player, Result.LOSE);
        }
        if (!isBust(player) && isBust(dealer)) {
            playersResult.add(player, Result.WIN);
        }
    }

    private void gamerNotBustRule(Dealer dealer, Player player) {
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

    private boolean isBust(Gamer gamer) {
        int totalScore = gamer.totalScore();
        return totalScore >= GameRule.BUST_STANDARD_SCORE;
    }

    private boolean isBlackjack(Gamer gamer) {
        int deckSize = gamer.deckSize();
        int totalScore = gamer.totalScore();
        return deckSize == GameRule.BLACKJACK_STANDARD_DECK_SIZE && totalScore == GameRule.BLACKJACK_STANDARD_SCORE;
    }

    public PlayersResult getPlayersResult() {
        return playersResult;
    }
}
