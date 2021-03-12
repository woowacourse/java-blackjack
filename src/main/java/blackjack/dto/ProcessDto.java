package blackjack.dto;

import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProcessDto {
    private final Players players;
    private final Participant dealer;

    public ProcessDto(Players players, Participant dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public List<String> names() {
        return players.getUnmodifiableList().stream()
            .map(participant -> participant.getName().toString())
            .collect(Collectors.toList());
    }

    public String dealerName() {
        return dealer.getName().toString();
    }

    public Map<String, Cards> cards() {
        final Map<String, Cards> results = new LinkedHashMap<>();

        results.put(dealer.getName().toString(), dealer.getCards());
        for (Player player : players.getUnmodifiableList()) {
            results.put(player.getName().toString(), player.getCards());
        }

        return results;
    }


}
