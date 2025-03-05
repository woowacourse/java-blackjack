package model;

import java.util.List;

public class GameManager {

  private final Dealer dealer;
  private final Players players;
  private final CardDeck deck = new CardDeck();

  public GameManager(Dealer dealer,Players players) {
    this.dealer = dealer;
    this.players = players;
  }

  public void divideCard(int amount) {
    for (Player player : players.getPlayers()) {
      List<Card> pickCards = deck.pickCard(amount);
      player.addCards(pickCards);
    }
  }
}
