package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.GamerState;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import blackjack.exception.PlayerNotFoundException;
import java.util.Collections;
import java.util.List;

public class Game {

  private static final int CARD_INIT_COUNT = 2;
  private final Cards cards;
  private final Player dealer;
  private final Gamers gamers;

  public Game(Cards cards, Player dealer, Gamers gamers) {
    initialize(cards, dealer, gamers);

    this.cards = cards;
    this.dealer = dealer;
    this.gamers = gamers;
  }

  private void initialize(Cards cards, Player dealer, Gamers gamers) {
    for (int i = 0; i < CARD_INIT_COUNT; i++) {
      dealer.addCardToDeck(cards.next());
      gamers.drawToGamers(cards);
    }
  }

  public Player getDealer() {
    return dealer;
  }

  public List<Player> getGamers() {
    return Collections.unmodifiableList(gamers.getGamers());
  }

  public GamerState drawTo(String name) {
    Player gamer = findGamer(name);
    gamer.addCardToDeck(cards.next());

    return new GamerState(name, gamer.getStatus(), gamer.getDeck());
  }

  public GameResult getResult() {
    return new GameResult(dealer, gamers.getGamers());
  }

  private Player findGamer(String name) {
    return gamers.findGamer(name);
  }
}
