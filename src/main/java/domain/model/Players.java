package domain.model;

import domain.vo.Bet;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players {

    private static final String DUPLICATE_PLAYER_ERROR_MESSAGE = "중복된 플레이어는 들어올 수 없습니다.";
    private static final String SIZE_NOT_MATCH_ERROR_MESSAGE = "플레이어와 카드의 개수가 일치하지 않습니다.";
    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = players;
    }

    public static Players from(final List<String> names, final List<Double> battings) {
        final List<Player> players = IntStream.range(0, names.size())
            .mapToObj(i -> new Player(Cards.makeEmpty(), names.get(i), Bet.of(battings.get(i))))
            .collect(Collectors.toList());
        return new Players(players);
    }

    private void validate(final List<Player> players) {
        if (isSizeNotMatch(players)) {
            throw new IllegalArgumentException(DUPLICATE_PLAYER_ERROR_MESSAGE);
        }
    }

    private boolean isSizeNotMatch(final List<Player> players) {
        return players.stream().distinct().count() != players.size();
    }

    public void addAll(final List<Card> cards) {
        if (players.size() != cards.size()) {
            throw new IllegalArgumentException(SIZE_NOT_MATCH_ERROR_MESSAGE);
        }
        IntStream.range(0, cards.size())
            .forEach(i -> players.get(i).addCard(cards.get(i)));
    }

    public int count() {
        return players.size();
    }

    public List<String> getNames() {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.toUnmodifiableList());
    }

    public void set(final int index, final Player element) {
        players.set(index, element);
    }

    public Player get(final int index) {
        final Player player = players.get(index);
        Set<Card> cards = new HashSet<>(player.getCards());
        return new Player(new Cards(cards), player.getName(), player.getBet());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
