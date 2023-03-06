package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResults {
    private final Map<Name, MatchResults> participants = new LinkedHashMap<>();

    public BlackJackResults(final Dealer dealer, final List<Player> players) {
        List<ResultType> dealerResults = createDealerResults(dealer, players);
        participants.put(dealer.getName(), new MatchResults(dealerResults));
        for (int index = 0; index < dealerResults.size(); index++) {
            Name playerName = players.get(index).getName();
            ResultType dealerResult = dealerResults.get(index);
            ResultType reverseType = ResultType.getReverseType(dealerResult);
            participants.put(playerName, new MatchResults(reverseType));
        }
    }

    private List<ResultType> createDealerResults(final Dealer dealer, final List<Player> players) {
        return players.stream()
                .map(dealer::judgeResult)
                .collect(Collectors.toList());
    }

    public Map<Name, MatchResults> getParticipants() {
        return new LinkedHashMap<>(participants);
    }
}
