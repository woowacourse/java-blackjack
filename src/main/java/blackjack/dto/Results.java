package blackjack.dto;

import blackjack.domain.card.Cards;
import blackjack.domain.Outcome;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.*;
import java.util.stream.Collectors;

public class Results {
    private final Players players;
    private final Participant dealer;

    public Results(Players players, Participant dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public List<String> names() {
        return players.getUnmodifiableList().stream()
            .map(Participant::getName)
            .collect(Collectors.toList());
    }

    public String dealerName(){
        return dealer.getName();
    }
    public Map<String, Cards> cards() {
        final Map<String, Cards> results = new LinkedHashMap<>();

        results.put(dealer.getName(), dealer.getCards());
        for (Player player : players.getUnmodifiableList()) {
            results.put(player.getName(), player.getCards());
        }

        return results;
    }

    public Map<String, String> getPlayersFinalOutcome() {
        final Map<String, String> results = new LinkedHashMap<>();

        for (Player player : players.getUnmodifiableList()) {
            final Outcome playerOutcome = Outcome
                .getInstance(player.sumCardsForResult(), dealer.sumCardsForResult());
            results.put(player.getName(), playerOutcome.toString());
        }

        return Collections.unmodifiableMap(results);
    }

    public Map<Outcome, Integer> getDealerFinalOutcome() {
        final Map<Outcome, Integer> outcomes = new EnumMap<>(Outcome.class);
        for (Player player : players.getUnmodifiableList()) {
            Outcome dealerOutcome = Outcome
                .getInstance(dealer.sumCardsForResult(), player.sumCardsForResult());
            outcomes.put(dealerOutcome, outcomes.getOrDefault(dealerOutcome, 0) + 1);
        }
        return Collections.unmodifiableMap(outcomes);
    }

}
