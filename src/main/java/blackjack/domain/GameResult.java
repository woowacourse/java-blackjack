package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int BLACKJACK = 21;

    private final Map<String, Integer> dealerResult;
    private final Map<String, String> playersResult;

    public GameResult(Dealer dealer, Players players) {
        this.dealerResult = initializeDealerResult();
        this.playersResult = new LinkedHashMap<>();
        calculateVictoryOrDefeat(dealer, players);
    }

    private Map<String, Integer> initializeDealerResult() {
        Map<String, Integer> dealerResult = new LinkedHashMap<>();

        dealerResult.put(ResultType.WIN.getMessage(), 0);
        dealerResult.put(ResultType.LOSE.getMessage(), 0);
        dealerResult.put(ResultType.DRAW.getMessage(), 0);

        return dealerResult;
    }

    private void calculateVictoryOrDefeat(Dealer dealer, Players players) {
        int dealerScore = dealer.getScore();

        for (Player player : players.getPlayers()) {
            int playerScore = player.getScore();
            String playerName = player.getName();
            calculateVictoryOrDefeatWithDealerAndPlayer(dealerScore, playerScore, playerName);
        }
    }

    public void calculateVictoryOrDefeatWithDealerAndPlayer(int dealerScore, int playerScore, String playerName) {
        if (playerScore == BLACKJACK) {
            playerScoreIsBlackJack(dealerScore, playerName);
            return;
        }
        if (playerScore < BLACKJACK) {
            playerScoreLessThanBlackJack(dealerScore, playerScore, playerName);
            return;
        }
        playerLose(playerName);
    }

    private void playerScoreIsBlackJack(int dealerScore, String playerName) {
        if (dealerScore == BLACKJACK) {
            draw(playerName);
            return;
        }
        playerWin(playerName);
    }

    private void playerScoreLessThanBlackJack(int dealerScore, int playerScore, String playerName) {
        if (dealerScore == BLACKJACK) {
            playerLose(playerName);
            return;
        }
        if (dealerScore > BLACKJACK) {
            playerWin(playerName);
            return;
        }
        dealerScoreLessThanBlackJack(dealerScore, playerScore, playerName);
    }

    private void dealerScoreLessThanBlackJack(int dealerScore, int playerScore, String playerName) {
        if (dealerScore < playerScore) {
            playerWin(playerName);
            return;
        }
        playerLose(playerName);
    }

    private void playerWin(String playerName) {
        playersResult.put(playerName, ResultType.WIN.getMessage());
        dealerResult.put(ResultType.LOSE.getMessage(), dealerResult.get(ResultType.LOSE.getMessage()) + 1);
    }

    private void playerLose(String playerName) {
        playersResult.put(playerName, ResultType.LOSE.getMessage());
        dealerResult.put(ResultType.WIN.getMessage(), dealerResult.get(ResultType.WIN.getMessage()) + 1);
    }

    private void draw(String playerName) {
        playersResult.put(playerName, ResultType.DRAW.getMessage());
        dealerResult.put(ResultType.DRAW.getMessage(), dealerResult.get(ResultType.DRAW.getMessage()) + 1);
    }

    public Map<String, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, String> getPlayersResult() {
        return playersResult;
    }
}
