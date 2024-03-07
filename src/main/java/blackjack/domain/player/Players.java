package blackjack.domain.player;

import java.util.List;

public class Players {
    private static final int DEALER_SIZE = 1;
    private final List<Player> players;

    public Players(List<Player> players) {
        validateDealer(players);
        this.players = players;
    }

    private void validateDealer(List<Player> players) {
        long count = players.stream()
                            .filter(player -> player instanceof Dealer)
                            .count();

        if (count != DEALER_SIZE) {
            throw new IllegalStateException(String.format("딜러가 %d명이 아닙니다.", DEALER_SIZE));
        }
    }

    public Dealer getDealer() {
        return players.stream()
                      .filter(player -> player instanceof Dealer)
                      .findAny()
                      .map(Dealer.class::cast)
                      .get();
    }

    public List<GamePlayer> getGamePlayers() {
        return players.stream()
                      .filter(player -> player instanceof GamePlayer)
                      .map(GamePlayer.class::cast)
                      .toList();
    }
}
