package domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BettingManager {

    private final Map<Name, BettingAmount> playersBettingAmount;

    public BettingManager(List<Name> playerNames, List<BettingAmount> bets) {
        this.playersBettingAmount = new HashMap<>();
        int playerCount = playerNames.size();
        for (int index = 0; index < playerCount; index++) {
            playersBettingAmount.put(playerNames.get(index), bets.get(index));
        }
    }

    public Map<String, Double> calculateTotalRevenue(Map<Name, GameResult> winLossResult) {
        Map<Name, BettingAmount> participantRevenues = createInitialParticipantRevenues();
        Set<Entry<Name, GameResult>> playerResults = winLossResult.entrySet();
        for (Entry<Name, GameResult> playerResult : playerResults) {
            updateParticipantRevenue(participantRevenues, playerResult);
        }
        return getParticipantRevenuesToString(participantRevenues);
    }

    private Map<Name, BettingAmount> createInitialParticipantRevenues() {
        Map<Name, BettingAmount> participantsRevenue = new LinkedHashMap<>();
        participantsRevenue.put(Name.DEALER, BettingAmount.getDealerInitialAmount());
        return participantsRevenue;
    }

    private void updateParticipantRevenue(Map<Name, BettingAmount> participantRevenues,
                                          Entry<Name, GameResult> playerResult) {
        Name name = playerResult.getKey();
        BettingAmount playerRevenue = calculatePlayerRevenue(name, playerResult.getValue());
        participantRevenues.put(name, playerRevenue);
        participantRevenues.put(Name.DEALER,
                participantRevenues.get(Name.DEALER).subtractFromOtherPlayer(playerRevenue));
    }

    private BettingAmount calculatePlayerRevenue(Name playerName, GameResult result) {
        BettingAmount initialBetting = playersBettingAmount.get(playerName);
        return initialBetting.calculateMultiple(result);
    }

    private Map<String, Double> getParticipantRevenuesToString(Map<Name, BettingAmount> participantRevenues) {
        Map<String, Double> revenues = new LinkedHashMap<>();
        Set<Entry<Name, BettingAmount>> revenuesEntry = participantRevenues.entrySet();
        for (Entry<Name, BettingAmount> revenue : revenuesEntry) {
            revenues.put(revenue.getKey().getValue(), revenue.getValue().getRevenue());
        }
        return revenues;
    }

}
