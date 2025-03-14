package domain;

import domain.participant.Player;
import domain.participant.Role;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class RoundHistory {

  private final Map<Role, RoundResult> history;

  public RoundHistory(Map<Role, RoundResult> history) {
    this.history = new HashMap<>(history);
  }

  public List<Role> allocate() {
    return history.entrySet().stream()
        .map(this::getAllocatedPlayer)
        .collect(Collectors.toUnmodifiableList());
  }

  private Player getAllocatedPlayer(Entry<Role, RoundResult> entry) {
    final Role past = entry.getKey();
    final String pastName = past.getName();
    final Bet pastBet = past.getBet();
    final Bet allocated = entry.getValue().allocate(pastBet);

    return new Player(pastName, allocated.minus(pastBet));
  }
}
