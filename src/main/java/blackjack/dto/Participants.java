package blackjack.dto;

import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.Outcome;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.*;
import java.util.stream.Collectors;

public class Participants {
    private final Players players;
    private final Dealer dealer;

    public Participants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public String names() {
        return players.getUnmodifiableList().stream().map(Participant::getName)
            .collect(Collectors.joining(", "));
    }

    public Map<String, Cards> cards() {
        final Map<String, Cards> results = new LinkedHashMap<>();

        results.put(dealer.getName(), dealer.getCards());
        for (Player player : players.getUnmodifiableList()) {
            results.put(player.getName(), player.getCards());
        }

        return results;
    }

    public Map<String, String> finalResult() {
        final Map<String, String> results = new LinkedHashMap<>();
        final Map<Outcome, Integer> outcomes = new EnumMap<>(Outcome.class);

        results.put(dealer.getName(), "");
        for (Player player : players.getUnmodifiableList()) {
            Outcome outcome = dealer.resultOfPlayer(player.sumCardsForResult());
            outcomes.put(outcome, outcomes.getOrDefault(outcome, 0) + 1);
            results.put(player.getName(), outcome.getWord());
        }
        results.put(dealer.getName(), summarizeDealerOutcome(outcomes));

        return results;
    }

    private String summarizeDealerOutcome(final Map<Outcome, Integer> outcomes) {
        return outcomes.getOrDefault(Outcome.LOSE, 0) + "승 "
            + outcomes.getOrDefault(Outcome.WIN, 0) + "패 "
            + outcomes.getOrDefault(Outcome.DRAW, 0) + "무";
    }

}
