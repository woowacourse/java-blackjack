package model;

import java.util.ArrayList;
import java.util.List;

public class Players {

  private final List<Player> values;

  private Players(List<Player> values) {
    this.values = values;
  }

  public static Players from(List<String> input) {
    List<Player> inputPlayers = input.stream()
            .map(Player::from)
            .toList();
    return new Players(inputPlayers);
  }

  public List<Player> getPlayers() {
    return values;
  }

  public List<String> getNicknames() {
    return values.stream()
            .map(Participant::getNickname)
            .toList();
  }
}
