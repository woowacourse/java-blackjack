package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.GamerState;
import blackjack.domain.player.Player;
import blackjack.exception.PlayerNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Game {

  private static final int CARD_INIT_COUNT = 2;
  private final Cards cards;
  private final Dealer dealer;
  private final List<Gamer> gamers;

  public Game(Cards cards, Dealer dealer, List<Gamer> gamers) {
    initialize(cards, dealer, gamers);

    this.cards = cards;
    this.dealer = dealer;
    this.gamers = gamers;
  }

  private void initialize(Cards cards, Dealer dealer, List<Gamer> gamers) {
    for (int i = 0; i < CARD_INIT_COUNT; i++) {
      dealer.addCardToDeck(cards.next());
      gamers.forEach(gamer -> gamer.addCardToDeck(cards.next()));
    }
  }

  public Dealer getDealer() {
    return dealer;
  }

  public List<Gamer> getGamers() {
    return Collections.unmodifiableList(gamers);
  }

  public GamerState drawTo(String name) {
    Gamer gamer = findGamer(name);
    gamer.addCardToDeck(cards.next());

    return new GamerState(name, gamer.getStatus(), gamer.getDeck());
  }

  private Gamer findGamer(String name) {
    return gamers.stream()
        .filter(gamer -> gamer.isSameName(name))
        .findAny()
        .orElseThrow(PlayerNotFoundException::new);
  }
}
