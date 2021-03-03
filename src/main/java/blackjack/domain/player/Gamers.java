package blackjack.domain.player;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.exception.GamerDuplicateException;
import blackjack.exception.PlayerNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Gamers {

  private final List<Player> gamers;

  public Gamers(List<String> names) {
    validateDuplicate(names);
    this.gamers = names.stream()
        .map(Gamer::new)
        .collect(Collectors.toList());
  }

  public Gamers(String ... input) {
    this(Arrays.asList(input));
  }

  private void validateDuplicate(List<String> names) {
    if (names.size() != names.stream().distinct().count()) {
      throw new GamerDuplicateException();
    }
  }

  public List<Player> getGamers() {
    return Collections.unmodifiableList(gamers);
  }

  public void drawToGamers(Cards cards) {
    for (Player gamer : gamers) {
      gamer.addCardToDeck(cards.next());
    }
  }

  public Player findGamer(String name) {
    return gamers.stream()
        .filter(gamer -> gamer.isSameName(name))
        .findAny()
        .orElseThrow(PlayerNotFoundException::new);
  }

  public Map<Player, Integer> getGamersScore() {
    return gamers.stream()
        .collect(toMap(Function.identity(), Player::getScore));
  }

}

