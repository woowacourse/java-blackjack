package domain;

import domain.participant.Participant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class RoundHistory {

  private final Map<String, RoundResult> history;

  private RoundHistory(Map<String, RoundResult> history) {
    this.history = new HashMap<>(history);
  }

  public static RoundHistory of(
      final Participant dealer,
      final List<Participant> players
  ) {
    final Map<String, RoundResult> history = players.stream()
        .collect(Collectors.toMap(
            Participant::getName,
            player -> RoundResult.round(player, dealer)
        ));
    return new RoundHistory(history);
  }


  public Map<RoundResult, Integer> getDealerResult() {
    final Map<RoundResult, Integer> result = new HashMap<>();

    for (final RoundResult value : history.values()) {
      result.put(value, result.getOrDefault(value, 0) + 1);
    }
    return result;
  }

  public Map<String, RoundResult> getHistory() {
    return history;
  }
}
