package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {

    private static final int MAX_PLAYERS = 5;
    private static final int MIN_PLAYERS = 1;

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<Player> players) {
        validatePlayersNumber(players);
        validateDistinctName(players);
        return new Players(players);
    }

    private static void validatePlayersNumber(List<Player> players) {
        if (players.size() > MAX_PLAYERS || players.size() < MIN_PLAYERS) {
            throw new IllegalArgumentException("참여 가능한 플레이어는 최소 1명, 최대 5명 입니다.");
        }
    }

    private static void validateDistinctName(List<Player> players) {
        Set<Player> distinct = new HashSet<>(players);
        if (distinct.size() != players.size()) {
            throw new IllegalArgumentException("플레이어의 이름이 중복일 수 없습니다.");
        }
    }

    public void receiveCards(CardDeck cardDeck) {
        for (Player player : players) {
            player.receive(cardDeck.popCard());
        }
    }

    public void passCardByName(String name, Card card) {
        findByName(name).receive(card);
    }

    public boolean canPlayerReceive(String name) {
        return findByName(name).canReceive();
    }

    public int getScoreOf(String name) {
        return findByName(name).calculateScore();
    }

    public Player findByName(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("예기치 못한 에러가 발생했습니다."));
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
