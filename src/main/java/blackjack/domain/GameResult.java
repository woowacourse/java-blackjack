package blackjack.domain;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

  private final int dealerScore;
  private final Map<String, Integer> gamerScore;

  public GameResult(int dealerScore, Map<String, Integer> gamerScore) {
    this.dealerScore = dealerScore;
    this.gamerScore = gamerScore;
  }

  public Map<String, Boolean> getGamerResult() {
    Map<String, Boolean> gamerResult = new HashMap<>();

    gamerScore.forEach((name, score) -> gamerResult.put(name, score > dealerScore));

    return gamerResult;
  }

  public List<Boolean> getDealerResult() {
    return getGamerResult().values().stream()
        .map(result -> !result)
        .collect(toList());
  }
}
