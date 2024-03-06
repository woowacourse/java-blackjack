package blackjack;

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
            throw new IllegalStateException("딜러가 1명이 아닙니다.");
        }
    }
}
