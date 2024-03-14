package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Players {

    public static final int INITIAL_CARD_COUNT = 2;

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final Map<String, Double> bettings) {
        final List<Player> players = new ArrayList<>();
        bettings.forEach((name, bettingMoney) -> players.add(Player.of(name, bettingMoney)));
        return new Players(players);
    }

    public void drawInitialHand(final Dealer dealer) {
        for (final Player player : players) {
            drawInitialHand(dealer, player);
        }
    }

    private void drawInitialHand(final Dealer dealer, final Player player) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            player.draw(dealer.drawPlayerCard());
        }
    }

    public Player findBy(final String name) {
        final List<Player> foundPlayer = players.stream()
                .filter(player -> Objects.equals(player.getName().value(), name))
                .toList();
        if (foundPlayer.size() != 1) {
            throw new IllegalStateException("찾으려는 이름의 플레이어가 중복하여 존재합니다.");
        }
        return foundPlayer.get(0);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
