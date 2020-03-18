package domain.user;

import domain.card.Card;
import domain.card.Cards;
import domain.result.DealerResult;
import domain.result.Result;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Dealer extends User {
    public Card getFirstCard() {
        return this.cards
            .getFirstCard();
    }

    public boolean isScoreSame(int score) {
        return this.cards.sumScores() == score;
    }

    @Override
    public boolean canReceiveCard() {
        return !this.isLargerThan(Cards.MAX_SUM_FOR_DEALER_MORE_CARD);
    }

    public double calculateRevenueAbout(Players players) {
        double totalRevenue = 0;

        for (Player player : players.getPlayers()) {
            totalRevenue += this.calculateRevenueAbout(player);
        }
        return totalRevenue;
    }

    public double calculateRevenueAbout(Player player) {
        Result result = this.calculateResult(player);

        if (result.equals(Result.WIN)) {
            return player.getBetAmount();
        }
        if (result.equals(Result.DRAW)) {
            return 0;
        }
        return calculateRevenueWhenPlayerWin(player);
    }

    private double calculateRevenueWhenPlayerWin(Player player) {
        if (player.isBlackJack()) {
            return (-player.getBetAmount() * 1.5);
        }
        return -player.getBetAmount();
    }

    public DealerResult calculateResult(Players players) {
        Map<Result, Integer> dealerResult = new HashMap<>();
        Arrays.stream(Result.values())
            .forEach(result -> dealerResult.put(result, 0));

        for (Player player : players.getPlayers()) {
            Result playerResult = calculateResult(player);
            dealerResult.replace(playerResult, dealerResult.get(playerResult) + 1);
        }
        return new DealerResult(dealerResult);
    }

    public Result calculateResult(Player player) {
        if (draw(player)) {
            return Result.DRAW;
        }
        if (win(player)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private boolean draw(Player player) {
        boolean isBothOverBlackJack = this.isLargerThan(Cards.BLACKJACK_SCORE)
            && player.isLargerThan(Cards.BLACKJACK_SCORE);

        return isBothOverBlackJack || this.isScoreSame(player.getTotalScore());
    }

    private boolean win(Player player) {
        /*
         * 딜러는 파산이 아닐 때
         * 플레이어가 파산했거나, 딜러가 플레이어보다 점수가 높으면
         * 딜러가 이김
         * */
        return !this.isLargerThan(Cards.BLACKJACK_SCORE)
            && (player.isLargerThan(Cards.BLACKJACK_SCORE)
            || this.isLargerThan(player.getTotalScore()));
    }
}
