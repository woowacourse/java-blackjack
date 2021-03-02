package blackjack.domain.player;

import blackjack.domain.Status;
import blackjack.domain.card.Card;
import java.util.List;

public class GamerState {
  private final String name;
  private final Status status;
  private final List<Card> deck;

  public GamerState(String name, Status status, List<Card> deck) {
    this.name = name;
    this.status = status;
    this.deck = deck;
  }

  public String getName() {
    return name;
  }

  public Status getStatus() {
    return status;
  }

  public List<Card> getDeck() {
    return deck;
  }
}
