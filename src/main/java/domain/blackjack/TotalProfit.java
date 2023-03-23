package domain.blackjack;

import domain.money.Profit;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class TotalProfit {
    private final Map<Participant, Profit> participantsProfit;

    private TotalProfit(Map<Participant, Profit> participantsProfit) {
        this.participantsProfit = participantsProfit;
    }

    public static TotalProfit from(BlackjackGame blackjackGame) {
        Map<Participant, Profit> betResultBook = new LinkedHashMap<>();
        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();

        for (Player player : players.getPlayers()) {
            Result dealerResult = dealer.competeWithPlayer(player);
            Result playerResult = dealerResult.convertToOpposite();
            Profit profit = player.getProfitByResult(playerResult);
            betResultBook.put(player, profit);
        }

        return new TotalProfit(betResultBook);
    }

    public Map<Participant, Profit> getParticipantsProfit() {
        return Collections.unmodifiableMap(participantsProfit);
    }

    public Profit getDealerProfit() {
        double dealerProfit = participantsProfit.values().stream()
                .mapToDouble(Profit::getAmount)
                .sum();

        return new Profit(dealerProfit * -1);
    }

}
