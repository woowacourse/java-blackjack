package domain.betting;

import java.util.List;

import domain.participant.Dealer;
import domain.participant.Hand;
import domain.participant.Player;
import domain.profit.FinalProfit;
import domain.profit.FinalProfitByParticipant;
import domain.profit.FinalProfitDto;

public class BettingManager {

    private static final int LOSE_VALUE = -1;
    private static final double BLACKJACK_VALUE = 1.5;

    private final BettingMoneyByPlayer bettingMoneyByPlayer;
    private final FinalProfitByParticipant finalProfitByParticipant;

    public BettingManager() {
        this.bettingMoneyByPlayer = new BettingMoneyByPlayer();
        this.finalProfitByParticipant = new FinalProfitByParticipant();
    }

    public void savePlayerBettingMoney(Player player, BettingMoney bettingMoney) {
        bettingMoneyByPlayer.putPlayerBettingMoney(player, bettingMoney);
    }

    public void calculateParticipantFinalProfit(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            calculatePlayerFinalProfit(player, dealer);
        }
    }

    private void calculatePlayerFinalProfit(Player player, Dealer dealer) {
        Hand playerHand = player.getHand();
        BettingMoney bettingMoney = bettingMoneyByPlayer.findBettingMoneyByPlayer(player);
        if (dealer.isBlackjack()) {
            saveLoseProfit(player, bettingMoney);
        }
        if (playerHand.isBlackjack()) {
            saveBlackjackProfit(player, bettingMoney);
        }
        if (playerHand.isBust()) {
            saveLoseProfit(player, bettingMoney);
        }
        if (playerHand.isStay()) {
            saveProfitWhenPlayerStay(player, dealer, bettingMoney);
        }
    }

    private void saveBlackjackProfit(Player player, BettingMoney bettingMoney) {
        finalProfitByParticipant.putParticipantFinalProfit(player, new FinalProfit(bettingMoney.getValue() * BLACKJACK_VALUE));
    }

    private void saveLoseProfit(Player player, BettingMoney bettingMoney) {
        finalProfitByParticipant.putParticipantFinalProfit(player, new FinalProfit(bettingMoney.getValue() * LOSE_VALUE));
    }


    private void saveProfitWhenPlayerStay(Player player, Dealer dealer, BettingMoney bettingMoney) {
        Hand dealerHand = dealer.getHand();
        if (dealerHand.isBust()) {
            saveWinProfit(player, bettingMoney);
        }
        if (dealerHand.isStay()) {
            compareCardValueSum(player, dealer, bettingMoney);
        }
    }

    private void compareCardValueSum(Player player, Dealer dealer, BettingMoney bettingMoney) {
        Hand playerHand = player.getHand();
        Hand dealerHand = dealer.getHand();
        if (dealerHand.calculateOptimalCardValueSum() > playerHand.calculateOptimalCardValueSum()) {
            saveLoseProfit(player, bettingMoney);
        }
        if (dealerHand.calculateOptimalCardValueSum() <= playerHand.calculateOptimalCardValueSum()) {
            saveWinProfit(player, bettingMoney);
        }
    }

    private void saveWinProfit(Player player, BettingMoney bettingMoney) {
        finalProfitByParticipant.putParticipantFinalProfit(player, new FinalProfit(bettingMoney.getValue()));
    }

    public FinalProfitDto generateFinalProfitDto() {
        return new FinalProfitDto(
                finalProfitByParticipant.calculateDealerFinalProfit(),
                finalProfitByParticipant.getFinalProfitByParticipant());
    }
}
