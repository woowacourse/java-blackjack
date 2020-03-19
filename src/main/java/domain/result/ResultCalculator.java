package domain.result;

import domain.card.Cards;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultCalculator {

    private DealerResult dealerResult;
    private PlayersResult playersResult;

    public ResultCalculator() {
        dealerResult = new DealerResult();
    }

    public void calculateDealerAndPlayersResult(Dealer dealer, Players players) {
        Map<String, Double> result = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), calculateResult(dealer, player));
        }
        this.playersResult = new PlayersResult(result);
    }

    double calculateResult(Dealer dealer, Player player) {
        if (isDraw(dealer, player)) {
            return calculateMoney(player, Result.DRAW);
        }
        if (isPlayerLose(dealer, player)) {
            return calculateMoney(player, Result.LOSE);
        }
        return calculateMoney(player, Result.WIN);
    }

    private double calculateMoney(Player player, Result result) {
        double resultMoney = result.calculateResultMoney(
                player.getBettingMoney(), player.getCards().isBlackJack()
        );
        dealerResult.resultContributor(resultMoney);
        return resultMoney;
    }

    private boolean isDraw(Dealer dealer, Player player) {
        /*
         * 딜러와 플레이어 모두 21 이하
         * 딜러와 플레이어의 숫자가 같은 경우만 무승부
         * */
        return !(player.isLargerThan(Cards.BLACKJACK_SCORE)
                || dealer.isLargerThan(Cards.BLACKJACK_SCORE))
                && dealer.isScoreSame(player.getTotalScore());
    }

    private boolean isPlayerLose(Dealer dealer, Player player) {
        /*
         * 플레이어의 점수가 21 초과면 무조건 패
         * 플레어와 딜러 모두 21 이하인 경우에 더 작은 경우 패
         * */
        return player.isLargerThan(Cards.BLACKJACK_SCORE)
                || (!(player.isLargerThan(Cards.BLACKJACK_SCORE) || dealer.isLargerThan(Cards.BLACKJACK_SCORE))
                && player.isSmallerThan(dealer.getTotalScore()));
    }

    public double getDealerResult() {
        return dealerResult.getDealerResult();
    }

    public Map<String, Double> getPlayersResult() {
        return playersResult.getPlayerResult();
    }
}
