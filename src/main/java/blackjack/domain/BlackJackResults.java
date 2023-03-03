package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResults {
    private final Map<Name, BlackJackResult> participants = new LinkedHashMap<>();

    public BlackJackResults(final Dealer dealer, final List<Player> players) {
        participants.put(dealer.getName(), createDealerResults(dealer, players));
        players.forEach(player -> participants.put(player.getName(), new BlackJackResult(player.compare(dealer))));
    }

    private BlackJackResult createDealerResults(final Dealer dealer, final List<Player> players) {
        return new BlackJackResult(players.stream()
                .map(dealer::compare)
                .collect(Collectors.toList()));
    }

    public Map<Name, BlackJackResult> getParticipants() {
        return new LinkedHashMap<>(participants);
    }
}
