package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {
    private static final int FIRST_CARD_INDEX = 0;
    private static final int INIT_CARD_COUNT = 2;
    private static final int MORE_CARD_LIMIT_SCORE = 17;

    private final Map<Player, Result> playerResultMap = new LinkedHashMap<>();

    public Dealer(PlayerName playerName) {
        super(playerName);
    }

    public void decideDealerResultsAgainst(Players players) {
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

        return competeByScore(player.calculateBlackjackScore());
    }

    private Result competeByScore(int playerScore) {
        int dealerScore = this.calculateBlackjackScore();

        if (playerScore > dealerScore) {
            return Result.LOSE;
        }
        if (playerScore < dealerScore) {
            return Result.WIN;
        }

        return Result.DRAW;
    }

    public boolean isAbleToReceiveCard() {
        return calculateBlackjackScore() < MORE_CARD_LIMIT_SCORE;
    }

    public Map<Player, Result> getPlayerResultMap() {
        return Collections.unmodifiableMap(playerResultMap);
    }

    public int getHitCardCount() {
        return getCards().size() - INIT_CARD_COUNT;
    }

    public int getResultCount(Result result) {
        return (int) playerResultMap.values().stream()
                .filter(result::equals)
                .count();
    }

    @Override
    public List<Card> getInitialCards() {
        Card card = cards.getCards().get(FIRST_CARD_INDEX);
        return List.of(card);
    }
}
