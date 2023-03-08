package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Dealer extends Participant {
    private static final int SIGN_REVERSE = -1;

    private final Map<Player, Result> resultMap = new LinkedHashMap<>();

    public Dealer() {
        super(Name.from(DEALER_NAME));
    }

    public void decideResults(Players players) {
        for (Player player : players.getPlayers()) {
            Result dealerResult = competeWith(player);
            resultMap.put(player, dealerResult);
        }
    }

    private Result competeWith(Player player) {
        if (player.isBusted()) {
            return Result.WIN;
        }

        if (this.isBusted()) {
            return Result.LOSE;
        }

        return competeByBlackjack(player);
    }

    private Result competeByBlackjack(Player player) {
        if (this.isBlackjack() && player.isBlackjack()) {
            return Result.DRAW;
        }
        if (this.isBlackjack()) {
            return Result.WIN;
        }
        if (player.isBlackjack()) {
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
        return Collections.unmodifiableMap(resultMap);
    }

    public Card getFirstCard() {
        return cards.getFirstCard();
    }

    public int getHitCardCount() {
        return cards.size() - BlackjackRule.INITIAL_CARD_COUNT.getValue();
    }

    public int getResultCount(Result result) {
        return (int) resultMap.values().stream()
                .filter(result::equals)
                .count();
    }

    // TODO: 2023-03-07 수익률 계산 iterator 사용 ?
    public int getProfit() {
        int playersProfit = calculatePlayersProfit();

        return playersProfit * SIGN_REVERSE;
    }

    private int calculatePlayersProfit() {
        return resultMap.keySet().stream()
                .mapToInt(player -> player.getProfit(resultMap.get(player).convertToOpposite()))
                .sum();
    }

    // TODO: 2023-03-07 사용자의 배팅 금액을 업데이트할 필요가 있는가 ?
//    public void updateBalance() {
//        for (Player player : resultMap.keySet()) {
//            Result resultOfPlayer = resultMap.get(player).convertToOpposite();
//
//            player.updateBetAmount(resultOfPlayer);
//        }
//    }
}
