package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Dealer extends Participant {
    private static final int FIRST_CARD_INDEX = 0;

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

    public Map<Player, Result> getPlayerResultMap() {
        return playerResultMap;
    }

    public Card getDealerFirstCard() {
        return getCards().get(FIRST_CARD_INDEX);
    }
    public int getHitCardCount() {
        return getCards().size() - 2;
    }


}
