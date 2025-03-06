package model;

import java.util.function.Consumer;

public enum MatchResultType {
  WIN(1),
  LOSE(-1),
  DRAW(0);

  private final int condition;

  MatchResultType(int condition) {
    this.condition = condition;
  }

}
