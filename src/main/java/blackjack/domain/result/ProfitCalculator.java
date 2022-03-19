package blackjack.domain.result;

import blackjack.domain.betting.Money;
import blackjack.domain.player.Player;

import java.util.*;

public class ProfitCalculator {

    public static Map<Player, Integer> calculateParticipantsProfit(final Map<Player, Money> bettings, final Player dealer) {
        Map<Player, Integer> profits = new HashMap<>();
        for (Player participant : bettings.keySet()) {
            int profit = calculateParticipantProfit(bettings, dealer, participant);
            profits.put(participant, profit);
        }
        return profits;
    }

    private static int calculateParticipantProfit(Map<Player, Money> bettings, Player dealer, Player participant) {
        Result participantResult = Result.calculateResult(dealer, participant);
        return Result.calculateProfit(bettings.get(participant), participantResult);
    }

    public static int calculateDealerProfit(Collection<Integer> profits) {
        return profits.stream().mapToInt(Result::calculateOppositeProfit).sum();
    }

}
