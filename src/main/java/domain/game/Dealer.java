package domain.game;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    public static final int DEALER_DRAW_BOUND = 16;
    private static final int BLACK_JACK_CONDITION_COUNT = 2;
    private static final int BLACK_JACK_NUMBER = 21;

    private int totalBettingAmount;

    public Dealer() {
        this.totalBettingAmount = 0;
    }

    public List<GameResult> judgeGameResult(List<Player> players) {
        List<GameResult> gameResult = new ArrayList<>();
        for (Player player : players) {
            GameResult playerGameResult = judgeWin(player);
            gameResult.add(playerGameResult);
            calculateBettingResultFromDealer(player, playerGameResult);
        }
        return gameResult;
    }

    private void calculateBettingResultFromDealer(Player player, GameResult gameResult) {
        calculateBettingAmount(player.calculateBettingAmount(gameResult));
    }

    private void calculateBettingAmount(int bettingAmount) {
        this.totalBettingAmount -= bettingAmount;
    }

    public GameResult judgeWin(Player player) {
        if (player.isOverBurstBound()) {
            return GameResult.LOSE;
        }
        if (this.isOverBurstBound()) {
            return GameResult.WIN;
        }
        return judgeTotalCardNumber(player);
    }

    private GameResult judgeTotalCardNumber(Player player) {
        int playerTotalCardNumber = player.calculateTotalCardNumber();
        int dealerTotalCardNumber = this.calculateTotalCardNumber();
        if (playerTotalCardNumber < dealerTotalCardNumber) {
            return GameResult.LOSE;
        }
        if (playerTotalCardNumber > dealerTotalCardNumber) {
            return GameResult.WIN;
        }
        if (playerTotalCardNumber == BLACK_JACK_NUMBER) {
            return judgeBlackJack(player);
        }
        return GameResult.DRAW;
    }

    private GameResult judgeBlackJack(Player player) {
        int playerCardCount = player.getCardsCount();
        int dealerCardCount = this.getCardsCount();
        if (playerCardCount != BLACK_JACK_CONDITION_COUNT) {
            return GameResult.LOSE;
        }
        if (dealerCardCount != BLACK_JACK_CONDITION_COUNT) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    public boolean isUnderDrawBound() {
        return calculateTotalCardNumber() <= DEALER_DRAW_BOUND;
    }

    public Card openSingleCard() {
        return cards.getFirst();
    }

    public int getTotalBettingAmount() {
        return totalBettingAmount;
    }
}
