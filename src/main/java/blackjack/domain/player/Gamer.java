package blackjack.domain.player;

public class Gamer extends Player {

  private final String name;

  public Gamer(String name) {
    this.name = name;
  }

  public boolean isSameName(String name) {
    return this.name.equals(name);
  }

}
