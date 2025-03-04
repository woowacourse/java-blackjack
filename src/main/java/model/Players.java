package model;

import java.util.List;

public class Players {

  private final List<Player> values;

  public Players(List<Player> values) {
    this.values = values;
  }

  public List<Player> getPlayers() {
    return values;
  }
}
