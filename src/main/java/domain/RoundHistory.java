package domain;

import java.util.HashMap;
import java.util.Map;

public record RoundHistory(Map<String, Boolean> history) {

  public RoundHistory() {
    this(new HashMap<>());
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

  public void put(String name, boolean result) {
    history.put(name, result);
  }
}
