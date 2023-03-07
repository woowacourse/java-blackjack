package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResults {
    private final Map<Name, BlackJackResult> participants = new LinkedHashMap<>();

    public BlackJackResults(final Dealer dealer, final List<Player> players) {
        final List<ResultType> dealerResults = createDealerResults(dealer, players);
        participants.put(dealer.getName(), new BlackJackResult(dealerResults));
        for (int index = 0; index < dealerResults.size(); index++) {
            final Name playerName = players.get(index).getName();
            final ResultType dealerResult = dealerResults.get(index);
            final ResultType reverseType = ResultType.getReverseType(dealerResult);
            participants.put(playerName, new BlackJackResult(reverseType));
        }
    }

    private List<ResultType> createDealerResults(final Dealer dealer, final List<Player> players) {
        return players.stream()
                .map(dealer::judgeResult)
                .collect(Collectors.toList());
    }

    public Map<Name, BlackJackResult> getParticipants() {
        return new LinkedHashMap<>(participants);
    }
}
