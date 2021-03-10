package blackjack.dto;

import blackjack.domain.Outcome;
import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    public String dealerName() {
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

    public Map<String, Outcome> getPlayersFinalOutcome() {
        final Map<String, Outcome> results = new LinkedHashMap<>();

        for (Player player : players.getUnmodifiableList()) {
            final Outcome playerOutcome = Outcome
                .getInstance(player.sumCardsForResult(), dealer.sumCardsForResult());
            results.put(player.getName(), playerOutcome);
        }

        return Collections.unmodifiableMap(results);
    }

    public Map<Outcome, Integer> getDealerFinalOutcome() {
        final Map<Outcome, Integer> outcomes = new EnumMap<>(Outcome.class);
        Arrays.stream(Outcome.values()).forEach(outcome -> outcomes.put(outcome, 0));

        for (Player player : players.getUnmodifiableList()) {
            Outcome dealerOutcome = Outcome
                .getInstance(dealer.sumCardsForResult(), player.sumCardsForResult());
            outcomes.put(dealerOutcome, outcomes.get(dealerOutcome) + 1);
        }
        return Collections.unmodifiableMap(outcomes);
    }

}
