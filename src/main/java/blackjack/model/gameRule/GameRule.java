package blackjack.model.gameRule;

import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Gamer;
import blackjack.model.gamer.Gamers;
import blackjack.model.gamer.Player;
import blackjack.model.betting.BettingInfo;
import java.util.ArrayList;
import java.util.List;

public class GameRule {

    public static final int BUST_STANDARD_SCORE = 22;
    public static final int BLACKJACK_STANDARD_SCORE = 21;
    public static final int BLACKJACK_STANDARD_DECK_SIZE = 2;
    public static final int PLAYER_HIT_MAX_SCORE = 20;
    public static final int DEALER_HIT_MAX_SCORE = 16;

    private final PlayerResult playersResult = new PlayerResult();

    public List<Player> decideHitOrStayPlayers(Gamers gamers) {
        settingInitialResult(gamers.getPlayers());
        return decideHitOrStayPlayerByBlackjackRule(gamers);
    }

    public void applyGameResultProfit(BettingInfo bettingInfo, Gamers gamers) {
        settingFinalResult(gamers);
        applyPlayerResultProfit(bettingInfo, gamers.getPlayers());
    }

    private void settingInitialResult(List<Player> players) {
        for (Player player : players) {
            playersResult.add(player, Result.NONE);
        }
    }

    private List<Player> decideHitOrStayPlayerByBlackjackRule(Gamers gamers) {
        List<Player> hitOrStayTargetPlayer = new ArrayList<>();
        for (Player player : gamers.getPlayers()) {
            applyBlackjackRule(gamers.getDealer(), player);
            addTargetPlayer(player, hitOrStayTargetPlayer);
        }

        return hitOrStayTargetPlayer;
    }

    private void addTargetPlayer(Player player, List<Player> hitStayTargetPlayer) {
        Result playerResult = playersResult.findPlayerResult(player);
        if (playerResult == Result.NONE) {
            hitStayTargetPlayer.add(player);
        }
    }

    private void settingFinalResult(Gamers gamers) {
        List<Player> players = gamers.getPlayers();
        Dealer dealer = gamers.getDealer();
        for (Player player : players) {
            playerBustRule(dealer, player);
            gamerNotBustRule(dealer, player);
        }
    }

    private void applyPlayerResultProfit(BettingInfo bettingInfo, List<Player> players) {
        for (Player player : players) {
            Result playerResult = playersResult.findPlayerResult(player);
            bettingInfo.addPlayerProfitAmount(player, playerResult);
        }
    }

    private void applyBlackjackRule(Dealer dealer, Player player) {
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
        if (playersResult.findPlayerResult(player) != Result.NONE) {
            return;
        }
        if (isBust(player)) {
            playersResult.add(player, Result.LOSE);
        }
        if (!isBust(player) && isBust(dealer)) {
            playersResult.add(player, Result.WIN);
        }
    }

    private void gamerNotBustRule(Dealer dealer, Player player) {
        if (playersResult.findPlayerResult(player) != Result.NONE) {
            return;
        }

        int dealerScore = dealer.calculateScore().getScore();
        int playerScore = player.calculateScore().getScore();
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
        int totalScore = gamer.calculateScore().getScore();
        return totalScore >= BUST_STANDARD_SCORE;
    }

    private boolean isBlackjack(Gamer gamer) {
        int deckSize = gamer.deckSize();
        int totalScore = gamer.calculateScore().getScore();
        return deckSize == BLACKJACK_STANDARD_DECK_SIZE && totalScore == BLACKJACK_STANDARD_SCORE;
    }

    public PlayerResult getPlayersResult() {
        return playersResult;
    }
}
