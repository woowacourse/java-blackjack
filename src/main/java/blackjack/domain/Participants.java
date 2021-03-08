package blackjack.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Participants {
    private final List<Playable> participants;

    public Participants(Players players, Dealer dealer) {
        participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.values());
    }

    public String names() {
        return participants.stream()
                .filter(playable -> playable instanceof Player)
                .map(Playable::getName)
                .collect(Collectors.joining(", "));
    }

    public List<String> cards() {
        List<String> results = new ArrayList<>();
        for (Playable playable : participants) {
            results.add(playable.getName() + cardsToString(playable.getUnmodifiableCards()));
        }
        return results;
    }

    public List<String> result() {
        List<String> results = new ArrayList<>();
        for (Playable playable : participants) {
            results.add(playable.getName() + cardsToString(playable.getUnmodifiableCards()) + " - 결과: " + playable.sumCardsForResult());
        }
        return results;
    }

    private String cardsToString(List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", ", "카드: ", ""));
    }

    public Map<String, String> finalResult() {
        Playable dealer = participants.stream()
                .filter(playable -> playable instanceof Dealer)
                .findFirst()
                .orElse(null);
        List<Playable> players = participants.stream()
                .filter(playable -> playable instanceof Player)
                .collect(Collectors.toList());
        final Map<String, String> results = new LinkedHashMap<>();
        results.put(dealer.getName(), summarizeDealerOutcome(dealer, players));
        for (Playable player : players) {
            Outcome outcome = player.result(dealer.sumCardsForResult());
            results.put(player.getName(), outcome.getWord());
        }
        return results;
    }

    private String summarizeDealerOutcome(Playable dealer, List<Playable> players) {
        Map<Outcome, Integer> results = new EnumMap(Outcome.class);
        Arrays.stream(Outcome.values()).forEach(outcome -> results.put(outcome, 0));

        for (Playable player : players) {
            Outcome outcome = dealer.result(player.sumCardsForResult());
            results.put(outcome, results.get(outcome) + 1);
        }

        return results.get(Outcome.WIN) + "승 " + results.get(Outcome.LOSE) + "패 " + results.get(Outcome.DRAW) + "무";
    }
}
