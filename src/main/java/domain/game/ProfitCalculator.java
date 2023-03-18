package domain.game;

import domain.player.Dealer;
import domain.player.Participant;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public final class ProfitCalculator {

    private final Map<String, Integer> results;

    private ProfitCalculator(final Map<String, Integer> results) {
        this.results = results;
    }

    public static ProfitCalculator of(final List<Participant> participants, final Dealer dealer) {
        final Map<String, Integer> results = participants.stream()
                .collect(toMap(Participant::getName, participant -> ResultType.of(dealer, participant)));
        return new ProfitCalculator(results);
    }

    public int getProfit(final String name) {
        return results.getOrDefault(name, 0);
    }

    public int getDealerProfit() {
        return results.values().stream()
                .reduce(0, Integer::sum) * -1;
    }

}
