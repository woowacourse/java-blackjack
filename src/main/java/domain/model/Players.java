package domain.model;

import domain.vo.Score;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players {

    private static final String DUPLICATE_PLAYER_ERROR_MESSAGE = "중복된 플레이어는 들어올 수 없습니다.";
    private static final String SIZE_NOT_MATCH_ERROR_MESSAGE = "플레이어와 카드의 개수가 일치하지 않습니다.";
    private final List<Player> players;

    private Players(final List<Player> players) {
        validate(players);
        this.players = players;
    }

    public static Players from(final List<String> names) {
        final List<Player> players = names.stream()
            .map(name -> new Player(Cards.makeEmpty(), name))
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

    public int size() {
        return players.size();
    }

    public List<String> getNames() {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.toUnmodifiableList());
    }

    public List<Score> getScores() {
        return players.stream()
            .map(Player::getScore)
            .collect(Collectors.toUnmodifiableList());
    }

    public Player get(final int index) {
        Player player = players.get(index);
        return new Player(new Cards(player.getCards()), player.getName());
    }
}
