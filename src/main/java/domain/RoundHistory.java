package domain;

import domain.participant.Participant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class RoundHistory {

  private final Map<String, Boolean> history;

  private RoundHistory(Map<String, Boolean> history) {
    this.history = new HashMap<>(history);
  }

  public static RoundHistory of(
      final Participant dealer,
      final List<Participant> players
  ) {
    final Map<String, Boolean> history = players.stream()
        .collect(Collectors.toMap(
            Participant::getName,
            player -> player.round(dealer)
        ));
    return new RoundHistory(history);
  }


  public Map<Boolean, Integer> getDealerResult() {
    final Map<Boolean, Integer> result = new HashMap<>();

    for (final Boolean value : history.values()) {
      boolean key = !value;
      result.put(key, result.getOrDefault(key, 0) + 1);
    }
    return result;
  }

  public Map<String, Boolean> getHistory() {
    return history;
  }
}
