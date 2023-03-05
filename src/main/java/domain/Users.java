package domain;

import domain.card.Card;
import domain.participant.Player;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class Users {

    private final List<Player> players;

    public Users(final List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("유저는 최소 한 명 이상이여야 합니다.");
        }
        this.players = players;
    }

    public List<Player> getUsersGreaterThan(GamePoint point) {
        return getUserOf(user -> user.isGreaterThan(point));
    }

    public List<Player> getUsersEqualTo(final GamePoint point) {
        return getUserOf(user -> user.isEqualTo(point));
    }

    public List<Player> getUsersLowerThan(final GamePoint point) {
        return getUserOf(user -> user.isLowerThan(point));
    }

    private List<Player> getUserOf(Predicate<Player> method) {
        return players.stream()
                .filter(user -> method.test(user))
                .collect(Collectors.toList());
    }

    public void giveEachUser(final Deck deck, final int count) {
        for (Player player : players) {
            player.give(deck, count);
        }
    }

    public List<Card> getCardsOf(final Player player) {
        return finUserByName(player).openCards();
    }

    public void findUserAndGive(final Player player, final Card card) {
        final Player findPlayer = finUserByName(player);
        findPlayer.draw(card);
    }

    private Player finUserByName(final Player player) {
        return players.stream()
                .filter(u -> u.hasSameName(player))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
    }

    public List<Player> getUsers() {
        return List.copyOf(players);
    }

    public boolean isBusted(final Player player) {
        return finUserByName(player).isBusted();
    }
}
