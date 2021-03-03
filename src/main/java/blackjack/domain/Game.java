package blackjack.domain;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import blackjack.GameResult;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    cards.shuffle();

    for (int i = 0; i < CARD_INIT_COUNT; i++) {
      dealer.addCardToDeck(cards.next());
      gamers.drawToGamers(cards);
    }
  }

  public boolean drawCardToGamer(String name) {
    Player gamer = findGamerByName(name);

    gamer.addCardToDeck(cards.next());

    return gamer.getStatus() == Status.HIT;
  }

  public boolean drawCardToDealer() {
    if(dealer.isDrawable()) {
      dealer.addCardToDeck(cards.next());
      return true;
    }

    return false;
  }

  public Map<String, GameResult> getGamerResult() {
    return gamers.getGamers().stream()
        .collect(toMap(Player::getName, this::calculateWinning));
  }

  private GameResult calculateWinning(Player player) {
    return GameResult.calculate(dealer, player);
  }

  public List<GameResult> getDealerResult() {
    return getGamerResult().values().stream()
        .map(GameResult::reverse)
        .collect(toList());
  }

  public Player findGamerByName(String name) {
    return gamers.findGamer(name);
  }

  public Player getDealer() {
    return dealer;
  }

  public List<Player> getGamersAsList() {
    return Collections.unmodifiableList(gamers.getGamers());
  }

  public List<String> getGamerNames() {
    return gamers.getGamerNames();
  }
}
