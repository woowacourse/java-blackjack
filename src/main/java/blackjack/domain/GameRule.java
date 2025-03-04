package blackjack.domain;

public class GameRule {

    public GameResult evaluateDealerWin(Player player, Dealer dealer) {
        int playerScore = player.calculateTotalCardScore();
        int dealerScore = dealer.calculateTotalCardScore();

        //딜러 혹은 플레이어가 21이 넘어가면 결과가 나와야 됨
        if (player.isBust()) {
            return GameResult.WIN;
        }
        if (dealer.isBust()) {
            return GameResult.LOSE;
        }

        //21에 가까운 숫자를 가진 딜러가 이김
        if (dealerScore > playerScore) {
            return GameResult.WIN;
        }
        if (dealerScore < playerScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}
