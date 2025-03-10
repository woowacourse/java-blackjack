package domain;

import domain.participant.Participant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoundHistory {

  private final Map<String, Boolean> history;

  private RoundHistory(Map<String, Boolean> history) {
    this.history = new HashMap<>(history);
  }

  public static RoundHistory of(final Participant dealer, final List<Participant> players) {
    final Map<String, Boolean> history = players.stream()
        .collect(Collectors.toMap(
            Participant::getName,
            player -> player.round(dealer)
        ));
    return new RoundHistory(history);
  }


  public Map<Boolean, Integer> getDealerResult() {
    Map<Boolean, Integer> result = new HashMap<>(Map.of(true, 0, false, 0));

    for (Boolean value : history.values()) {
      result.put(value, result.get(value) + 1);
    }
    return result;
  }

  public Map<String, Boolean> getHistory() {
    return history;
  }
}
