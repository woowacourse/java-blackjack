package blackjack.domain;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {

  private final Player dealer;
  private final List<Player> players;


  public GameResult(Player dealer, List<Player> players) {
    this.dealer = dealer;
    this.players = players;
  }

  public Map<String, Boolean> getGamerResult() {
    return players.stream().collect(toMap(Player::getName, this::calculateWinning));
  }

  private boolean calculateWinning(Player player) {
    if(dealer.getStatus() == Status.BURST && player.getStatus() != Status.BURST) {
      return true;
    }

    return player.getStatus() != Status.BURST && dealer.getScore() < player.getScore();
  }

  public List<Boolean> getDealerResult() {
    return getGamerResult().values().stream()
        .map(result -> !result)
        .collect(toList());
  }
}
