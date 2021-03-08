package blackjack.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {
    public static final int DEALER_HIT = 16;

    public Dealer(List<Card> cards) {
        super("딜러", cards);
    }

    @Override
    public boolean isAvailableToTake() {
        return sumCards() <= DEALER_HIT;
    }

    public Map<Outcome, Integer> calculateOutcomes(Players players) {
        Map<Outcome, Integer> outcomes = new EnumMap<>(Outcome.class);
        Arrays.stream(Outcome.values())
                .forEach(outcome -> outcomes.put(outcome, 0));
        for (Player player : players.values()) {
            Outcome outcome = result(player.sumCardsForResult());
            outcomes.put(outcome, outcomes.get(outcome) + 1);
        }
        return outcomes;
    }
}
