package domain.player;

import domain.blackjack.BlackjackRule;
import domain.blackjack.Result;
import domain.card.Card;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Dealer extends Participant {
    private static final int SIGN_REVERSE = -1;

    private final Map<Player, Result> playerResults = new LinkedHashMap<>();

    public Dealer() {
        super(Name.from(DEALER_NAME));
    }

    public void decideResults(Players players) {
        for (Player player : players.getPlayers()) {
            Result dealerResult = competeWith(player);
            playerResults.put(player, dealerResult);
        }
    }

    private Result competeWith(Player player) {
        if (this.isBlackjack() && player.isBlackjack()) {
            return Result.DRAW;
        }
        if (this.isBlackjack()) {
            return Result.LOSE;
        }
        if (player.isBlackjack()) {
            return Result.BLACKJACK;
        }

        return competeByBust(player);
    }

    private Result competeByBust(Player player) {
        if (player.isBusted()) {
            return Result.LOSE;
        }

        if (this.isBusted()) {
            return Result.WIN;
        }

        return competeByScore(player.calculateScore());

    }

    private Result competeByScore(int playerScore) {
        int dealerScore = this.calculateScore();

        if (playerScore > dealerScore) {
            return Result.WIN;
        }
        if (playerScore < dealerScore) {
            return Result.LOSE;
        }

        return Result.DRAW;
    }

    public int getProfit() {
        int playersProfit = calculatePlayersProfit();

        return playersProfit * SIGN_REVERSE;
    }

    private int calculatePlayersProfit() {
        return playerResults.keySet().stream()
                .mapToInt(player -> player.calculateProfitBy(playerResults.get(player)))
                .sum();
    }

    public Card getFirstCard() {
        return cards.getFirstCard();
    }

    public int getHitCardCount() {
        return cards.size() - BlackjackRule.INITIAL_CARD_COUNT.getValue();
    }

    public int getDealerResultCount(Result result) {
        return (int) playerResults.values().stream()
                .filter(value -> result.equals(value.convertToOpposite()))
                .count();
    }

    public Map<Player, Result> getGameResult() {
        return Collections.unmodifiableMap(playerResults);
    }
}
