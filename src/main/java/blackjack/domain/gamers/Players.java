package blackjack.domain.gamers;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<String> playerNames) {
        final List<Player> players = playerNames.stream()
                .map(Player::from)
                .toList();
        return new Players(players);
    }

    public void drawInitialHand(final Dealer dealer) {
        for (final Player player : players) {
            player.drawInitialHand(dealer);
        }
    }

    public boolean canDraw(final Name name) {
        return findBy(name).canDraw();
    }

    public void draw(final Card card, final Name name) {
        findBy(name).draw(card);
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

    public Player findBy(final Name name) {
        return findBy(name.value());
    }

    public List<Name> names() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
