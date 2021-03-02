package blackjack.domain.player;

import blackjack.exception.GamerDuplicateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Gamers {

  private final List<Gamer> gamers;

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

  public List<Gamer> getGamers() {
    return Collections.unmodifiableList(gamers);
  }
}

