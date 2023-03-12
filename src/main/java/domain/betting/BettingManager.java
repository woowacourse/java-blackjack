package domain.betting;

import java.util.List;

import domain.participant.Dealer;
import domain.participant.Hand;
import domain.participant.Player;
import domain.profit.FinalProfit;
import domain.profit.FinalProfitByParticipant;
import domain.profit.FinalProfitDto;

public class BettingManager {

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
        Hand dealerHand = dealer.getHand();
        BettingMoney bettingMoney = bettingMoneyByPlayer.findBettingMoneyByPlayer(player);
        PlayerBettingResult playerBettingResult = player.calculateBettingResult(dealerHand);
        saveProfit(player, bettingMoney, playerBettingResult);
    }

    private void saveProfit(Player player, BettingMoney bettingMoney, PlayerBettingResult playerBettingResult) {
        finalProfitByParticipant.putParticipantFinalProfit(player, new FinalProfit(playerBettingResult.calculateFinalProfit(bettingMoney.getMoney())));
    }

    public FinalProfitDto generateFinalProfitDto() {
        return new FinalProfitDto(
                finalProfitByParticipant.calculateDealerFinalProfit(),
                finalProfitByParticipant.getFinalProfitByParticipant());
    }
}
