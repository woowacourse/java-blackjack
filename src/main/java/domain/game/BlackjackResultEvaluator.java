package domain.game;

import java.util.List;

public class BlackjackResultEvaluator {

    private static final int BLACK_JACK_CONDITION_COUNT = 2;
    private static final int BLACK_JACK_NUMBER = 21;

    public List<GameResult> judgeGameResult(List<Player> players, Dealer dealer) {
        return players.stream()
                .map(player -> judgePlayerVsDealerResult(dealer, player))
                .toList();
    }

    private GameResult judgePlayerVsDealerResult(Dealer dealer, Player player) {
        GameResult playerGameResult = judgeWin(dealer, player);
        calculateBettingResult(dealer, player, playerGameResult);
        return playerGameResult;
    }

    private void calculateBettingResult(Dealer dealer, Player player, GameResult playerGameResult) {
        Chip playerBettingResult = player.calculateBettingAmount(playerGameResult);
        dealer.calculateDealerProfit(playerBettingResult);
    }

    private GameResult judgeWin(Dealer dealer, Player player) {
        if (player.isOverBurstBound()) {
            return GameResult.LOSE;
        }
        if (dealer.isOverBurstBound()) {
            return GameResult.WIN;
        }
        return judgeTotalCardNumber(dealer, player);
    }

    private GameResult judgeTotalCardNumber(Dealer dealer, Player player) {
        int playerTotalCardNumber = player.calculateTotalCardNumber();
        int dealerTotalCardNumber = dealer.calculateTotalCardNumber();
        if (playerTotalCardNumber < dealerTotalCardNumber) {
            return GameResult.LOSE;
        }
        if (playerTotalCardNumber > dealerTotalCardNumber) {
            return judgePlayerBlackJackWin(playerTotalCardNumber, player.getCardsCount());
        }
        if (playerTotalCardNumber == BLACK_JACK_NUMBER) {
            return judgeBlackJack(dealer, player);
        }
        return GameResult.DRAW;
    }

    private GameResult judgePlayerBlackJackWin(int playerTotalCardNumber, int playerCardCount) {
        if (playerTotalCardNumber == BLACK_JACK_NUMBER && playerCardCount == BLACK_JACK_CONDITION_COUNT) {
            return GameResult.BLACKJACK_WIN;
        }
        return GameResult.WIN;
    }

    private GameResult judgeBlackJack(Dealer dealer, Player player) {
        int playerCardCount = player.getCardsCount();
        int dealerCardCount = dealer.getCardsCount();
        if (playerCardCount != BLACK_JACK_CONDITION_COUNT) {
            return GameResult.LOSE;
        }
        if (dealerCardCount != BLACK_JACK_CONDITION_COUNT) {
            return GameResult.BLACKJACK_WIN;
        }
        return GameResult.DRAW;
    }
}
