package domain;

public class Judge {

    public static PlayerScore judgeScore(Player player, Dealer dealer) {
        if (dealer.isBurst()) {
            return new PlayerScore(player.getName(), Profit.winnerProfit(player));
        }
        if (player.isBurst()) {
            return new PlayerScore(player.getName(), Profit.looserProfit(player));
        }
        if (dealer.getSumOfCards() > player.getSumOfCards()) {
            return new PlayerScore(player.getName(), Profit.looserProfit(player));
        }
        if (dealer.getSumOfCards() < player.getSumOfCards()) {
            return new PlayerScore(player.getName(), Profit.winnerProfit(player));
        }
        return new PlayerScore(player.getName(), Profit.tieProfit());
    }
//    private Judge() {}
//
//    public static GameResult of(Dealer dealer, Player player) {
//        int dealerScore = dealer.getCards().getScore();
//        int playerScore = player.getCards().getScore();
//        return getResult(dealerScore, playerScore);
//    }
//
//    private static GameResult getResult(int dealerScore, int playerScore) {
//        if (playerScore > Cards.BLACKJACK_NUMBER) {
//            return GameResult.LOSE;
//        }
//        if (dealerScore > Cards.BLACKJACK_NUMBER || playerScore > dealerScore) {
//            return GameResult.WIN;
//        }
//        if (dealerScore > playerScore) {
//            return GameResult.LOSE;
//        }
//        return GameResult.DRAW;
//    }
}
