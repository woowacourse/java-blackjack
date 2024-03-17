package blackjack.domain.profit;

import java.util.LinkedHashMap;
import java.util.Map;
import blackjack.domain.judgement.Judgement;
import blackjack.domain.judgement.JudgementResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerMoney;

public class Profit {

    private static final int DEALER_PROFIT_MULTIPLIER = -1;

    private final Participants participants;
    private final Judgement judgement;

    public Profit(Participants participants) {
        this.participants = participants;
        this.judgement = new Judgement();
    }

    public Map<String, Double> createAllProfit() {
        Map<String, Double> allProfit = new LinkedHashMap<>();
        Map<String, Double> playersProfit = createPlayersProfit();

        Dealer dealer = participants.getDealer();
        allProfit.put(dealer.getName(), calculateDealerProfit(playersProfit));
        allProfit.putAll(playersProfit);

        return allProfit;
    }

    private Map<String, Double> createPlayersProfit() {
        Map<String, Double> playersProfit = new LinkedHashMap<>();
        Dealer dealer = participants.getDealer();

        for (Player player : participants.getPlayers()) {
            JudgementResult result = judgement.judgePlayer(dealer, player);
            PlayerMoney playerProfit = result.calculateProfit(player.getMoney());

            playersProfit.put(player.getName(), playerProfit.getAmount());
        }

        return playersProfit;
    }

    private double calculateDealerProfit(Map<String, Double> playersProfitResult) {
        double totalPlayersProfit = playersProfitResult.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        return totalPlayersProfit * DEALER_PROFIT_MULTIPLIER;
    }
}
