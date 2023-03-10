package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int BLACKJACK = 21;

    private final Map<String, Integer> dealerResult;
    private final Map<String, String> playersResult;

    public GameResult(Game game) {
        this.dealerResult = initializeDealerResult();
        this.playersResult = new LinkedHashMap<>();
        calculateVictoryOrDefeat(game);
    }

    private Map<String, Integer> initializeDealerResult() {
        Map<String, Integer> dealerResult = new LinkedHashMap<>();

        dealerResult.put(ResultType.WIN.message(), 0);
        dealerResult.put(ResultType.LOSE.message(), 0);
        dealerResult.put(ResultType.DRAW.message(), 0);

        return dealerResult;
    }

    private void calculateVictoryOrDefeat(Game game) {
        int dealerScore = game.getDealer().calculateScore();

        for (int i = 0; i < game.getPlayersCount(); i++) {
            int playerScore = game.getPlayers().getPlayer(i).calculateScore();
            String playerName = game.getPlayers().getPlayer(i).getName();

            calculateVictoryOrDefeatByPlayerIndex(dealerScore, playerScore, playerName);
        }
    }

    private void calculateVictoryOrDefeatByPlayerIndex(int dealerScore, int playerScore, String playerName) {
        if (playerScore == BLACKJACK) {
            playerScoreIsBlackJack(dealerScore, playerName);
        }
        if (playerScore < BLACKJACK) {
            playerScoreLessThanBlackJack(dealerScore, playerScore, playerName);
        }
        if (playerScore > BLACKJACK) {
            playerLose(playerName);
        }
    }

    private void playerScoreIsBlackJack(int dealerScore, String playerName) {
        if (dealerScore == BLACKJACK) {
            draw(playerName);
        }
        playerWin(playerName);
    }

    private void playerScoreLessThanBlackJack(int dealerScore, int playerScore, String playerName) {
        if (dealerScore == BLACKJACK) {
            playerLose(playerName);
        }
        if (dealerScore > BLACKJACK) {
            playerWin(playerName);
        }
        if (dealerScore < BLACKJACK) {
            dealerScoreLessThanBlackJack(dealerScore, playerScore, playerName);
        }
    }

    private void dealerScoreLessThanBlackJack(int dealerScore, int playerScore, String playerName) {
        if (dealerScore < playerScore) {
            playerWin(playerName);
        }
        playerLose(playerName);
    }

    private void playerWin(String playerName) {
        playersResult.put(playerName, ResultType.WIN.message());
        dealerResult.put(ResultType.LOSE.message(), dealerResult.get(ResultType.LOSE.message()) + 1);
    }

    private void playerLose(String playerName) {
        playersResult.put(playerName, ResultType.LOSE.message());
        dealerResult.put(ResultType.WIN.message(), dealerResult.get(ResultType.WIN.message()) + 1);
    }

    private void draw(String playerName) {
        playersResult.put(playerName, ResultType.DRAW.message());
        dealerResult.put(ResultType.DRAW.message(), dealerResult.get(ResultType.DRAW.message()) + 1);
    }

    public Map<String, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, String> getPlayersResult() {
        return playersResult;
    }
}
