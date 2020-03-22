package domain.result;

import domain.card.Cards;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

import java.util.ArrayList;
import java.util.List;

public class ResultCalculator {

    private DealerResult dealerResult;
    private PlayersResult playersResult;

    public ResultCalculator() {
        dealerResult = new DealerResult();
    }

    public void calculateDealerAndPlayersResult(Dealer dealer, Players players) {
        List<PlayerResult> result = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            result.add(new PlayerResult(player.getName(), calculateResult(dealer, player)));
        }
        this.playersResult = new PlayersResult(result);
    }

    public static boolean isDraw(int dealerScore, int playerScore) {
        /*
         * 딜러와 플레이어 모두 21 이하
         * 딜러와 플레이어의 숫자가 같은 경우만 무승부
         * */
        return dealerScore <= Cards.BLACKJACK_SCORE && playerScore == dealerScore;
    }

    private double calculateMoney(Player player, Result result) {
        double resultMoney = result.calculateResultMoney(
                player.getBettingMoney(), player.getCards().isBlackJack()
        );
        dealerResult.resultContributor(resultMoney);
        return resultMoney;
    }

    public static boolean isPlayerLose(int dealerScore, int playerScore) {
        /*
         * 플레이어의 점수가 21 초과면 무조건 패
         * 플레어와 딜러 모두 21 이하인 경우에 더 작은 경우 패
         * */
        return playerScore > Cards.BLACKJACK_SCORE
                || (dealerScore <= Cards.BLACKJACK_SCORE && playerScore < dealerScore);
    }

    double calculateResult(Dealer dealer, Player player) {
        return calculateMoney(player, player.compare(dealer));
    }

    public double getDealerResult() {
        return dealerResult.getDealerResult();
    }

    public PlayersResult getPlayersResult() {
        return this.playersResult;
    }
}
