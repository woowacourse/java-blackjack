package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.List;

public class Game {

  private static final int CARD_INIT_COUNT = 2;
  private final Cards cards;
  private final Dealer dealer;
  private final List<Player> players;

  public Game(Cards cards, Dealer dealer, List<Player> players) {
    initialize(cards, dealer, players);

    this.cards = cards;
    this.dealer = dealer;
    this.players = players;
  }

  private void initialize(Cards cards, Dealer dealer, List<Player> players) {
    for (int i = 0; i < CARD_INIT_COUNT; i++) {
      dealer.addCardToDeck(cards.next());
      players.forEach(player -> player.addCardToDeck(cards.next()));
    }
  }

  public Dealer getDealer() {
    return dealer;
  }

  public List<Player> getPlayers() {
    return players;
  }
}
