package domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private static final int MAX_PLAYERS_COUNT = 8;
    private static final Name DEALER_NAME = new Name("딜러");
    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayerCount(players);
        validatePlayerName(players);
        validateDuplicatePlayerName(players);
        this.players = players;
    }

    private void validatePlayerCount(List<Player> players) {
        if (players.size() > MAX_PLAYERS_COUNT) {
            throw new IllegalArgumentException("플레이어의 수는 최대 8명 입니다.");
        }
    }

    private void validatePlayerName(List<Player> players) {
        boolean isDealerExist = players.stream()
                .anyMatch(player -> player.hasName(DEALER_NAME));

        if (isDealerExist) {
            throw new IllegalArgumentException("플레이어 이름을 \"딜러\"로 생성할 수 없습니다.");
        }
    }

    private void validateDuplicatePlayerName(List<Player> players) {
        long playerNameCount = getUniquePlayerCount(players);
        if (players.size() != playerNameCount) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    private long getUniquePlayerCount(List<Player> players) {
        return players.stream()
                .map(Gamer::getName)
                .distinct()
                .count();
    }

    public void pickCardsToPlayer(Deck deck, int count) {
        players.forEach(player -> player.pickCards(deck, count));
    }

    public void hitFromName(Name name, Deck deck) {
        searchFromName(name).hit(deck);
    }

    public boolean isBustFromName(Name name) {
        return searchFromName(name).isBust();
    }

    public List<Card> getCardsFromName(Name name) {
        return searchFromName(name).getCards();
    }

    public int getTotalScore(Name name) {
        return searchFromName(name).getTotalScore();
    }

    private Player searchFromName(Name name) {
        return players.stream().filter(player -> player.hasName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 참여자 입니다."));
    }

    public Map<Name, Integer> getPlayersTotalScores() {
        return Collections.unmodifiableMap(players.stream()
                .collect(Collectors.toMap(Gamer::getName, Gamer::getTotalScore)));
    }
}
