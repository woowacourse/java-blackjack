package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.Map;

public enum Score {
    WIN("승 "),
    DRAW("무 "),
    LOSE("패");

    private final String value;

    Score(String value) {
        this.value = value;
    }

    public static Score comparePlayerDealer(Player player, Dealer dealer) {
        if (player.isBust() || player.getScoreSum() - dealer.getScoreSum() < 0) {
            return Score.LOSE;
        }
        if (dealer.isBust() || player.getScoreSum() - dealer.getScoreSum() > 0) {
            return Score.WIN;
        }
        return Score.DRAW;
    }

    public static void playerWin(Map<Score, Integer> playerResult, Map<Score, Integer> dealerResult) {
        playerResult.computeIfPresent(Score.WIN, (k, v) -> v + 1);
        dealerResult.computeIfPresent(Score.LOSE, (k, v) -> v + 1);
    }

    public static void dealerWin(Map<Score, Integer> playerResult, Map<Score, Integer> dealerResult) {
        playerResult.computeIfPresent(Score.LOSE, (k, v) -> v + 1);
        dealerResult.computeIfPresent(Score.WIN, (k, v) -> v + 1);
    }

    public static void draw(Map<Score, Integer> playerResult, Map<Score, Integer> dealerResult) {
        playerResult.computeIfPresent(Score.DRAW, (k, v) -> v + 1);
        dealerResult.computeIfPresent(Score.DRAW, (k, v) -> v + 1);
    }

    public static void playerWinBetting(BettingResults bettingResults, Player player, Dealer dealer) {
        if (player.isBlackJack()) {
            bettingResults.plusBettingResult(player, bettingResults.getParticipantBet(player).getAmount() / 2);
        }
        bettingResults.plusBettingResult(dealer, -bettingResults.getParticipantBet(player).getAmount());
    }

    public static void playerLoseBetting(BettingResults bettingResults, Player player, Dealer dealer) {
        bettingResults.plusBettingResult(dealer, bettingResults.getParticipantBet(player).getAmount());
        bettingResults.plusBettingResult(player, -(2 * bettingResults.getParticipantBet(player).getAmount()));
    }

    public static void drawBetting(BettingResults bettingResults, Player player) {
        bettingResults.plusBettingResult(player, -bettingResults.getParticipantBet(player).getAmount());
    }

    public String getValue() {
        return value;
    }
}
