package domain;

import domain.participant.Participant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundHistory {

  private Map<String, Boolean> history;

  private RoundHistory(Map<String, Boolean> history) {
    this.history = new HashMap<>(history);
  }

  public static RoundHistory of(final Participant dealer, final List<Participant> players) {
    final Map<String, Boolean> history = new HashMap<>();
    for (Participant player : players) {
      var name = player.getName();
      var result = player.round(dealer);
      history.put(name, result);
    }
    return new RoundHistory(history);
  }


  public Map<Boolean, Integer> getDealerResult() {
    Map<Boolean, Integer> result = new HashMap<>();
    result.put(true, 0);
    result.put(false, 0);
    for (Boolean value : history.values()) {
      result.put(value, result.get(value) + 1);
    }
    return result;
  }

  public Map<String, Boolean> getHistory() {
    return history;
  }
}
