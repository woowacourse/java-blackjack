package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Dealer extends Participant {
    private final Map<Player, Result> playerResultMap = new LinkedHashMap<>();

    public void decideResults(Players players) {
        for (Player player : players.getPlayers()) {
            Result dealerResult = competeWith(player);
            playerResultMap.put(player, dealerResult);
        }
    }

    private Result competeWith(Player player) {
        if (player.isBusted()) {
            return Result.WIN;
        }

        if (this.isBusted()) {
            return Result.LOSE;
        }

        return competeByScore(player.calculateScore());
    }

    private Result competeByScore(int playerScore) {
        int dealerScore = this.calculateScore();

        if (playerScore > dealerScore) {
            return Result.LOSE;
        }
        if (playerScore < dealerScore) {
            return Result.WIN;
        }

        return Result.DRAW;
    }

    public Map<Player, Result> getGameResult() {
        return Collections.unmodifiableMap(playerResultMap);
    }

    public Card getFirstCard() {
        return cards.getFirstCard();
    }

    public int getHitCardCount() {
        return cards.size() - BlackjackRule.INITIAL_CARD_COUNT.getValue();
    }

    public int getResultCount(Result result) {
        return (int) playerResultMap.values().stream()
                .filter(result::equals)
                .count();
    }
}
