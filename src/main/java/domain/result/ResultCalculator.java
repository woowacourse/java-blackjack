package domain.result;

import domain.card.Cards;
import domain.user.Dealer;
import domain.user.Player;

public class ResultCalculator {

    private DealerResult dealerResult;

    public ResultCalculator() {
        dealerResult = new DealerResult();
    }

//    public void calculateDealerAndPlayersResult(Dealer dealer, Players players) {
//        for(Player player : players.getPlayers()) {
//            player.setResult(calculateResult(dealer, player));
//        }
//        return new DealerResult(this.dealerResult);
//    }

    public double calculateResult(Dealer dealer, Player player) {
        if (isDraw(dealer, player)) {
            double drawMoney = Result.DRAW.calculateResultMoney(
                    player.getBettingMoney(), player.getCards().isBlackJack()
            );
            dealerResult.resultContributor(drawMoney);
            return drawMoney;
        }
        if (isPlayerLose(dealer, player)) {
            double loseMoney = Result.LOSE.calculateResultMoney(
                    player.getBettingMoney(), player.getCards().isBlackJack()
            );
            dealerResult.resultContributor(loseMoney);
            return loseMoney;
        }
        double winMoney = Result.WIN.calculateResultMoney(
                player.getBettingMoney(), player.getCards().isBlackJack()
        );
        dealerResult.resultContributor(winMoney);
        return winMoney;
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
         * 플레이어의 점수가 무조건 21 초과면 패
         * 21 이하인 경우 더 작은 경우 패
         * */
        return player.isLargerThan(Cards.BLACKJACK_SCORE)
                || (!(player.isLargerThan(Cards.BLACKJACK_SCORE) || dealer.isLargerThan(Cards.BLACKJACK_SCORE))
                        && player.isSmallerThan(dealer.getTotalScore()));
    }

    public double getDealerResult() {
        return dealerResult.getDealerResult();
    }

}
