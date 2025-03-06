package domain.participant;

import domain.card.CardDeck;
import java.util.List;

public class Players {

    private static final int MAX_PLAYERS = 5;
    private static final int MIN_PLAYERS = 1;

    private final List<Participant> players;

    private Players(List<Participant> players) {
        this.players = players;
    }

    public static Players of(List<Participant> players) {
        validatePlayersNumber(players);
        return new Players(players);
    }

    private static void validatePlayersNumber(List<Participant> players) {
        if (players.size() > MAX_PLAYERS || players.size() < MIN_PLAYERS) {
            throw new IllegalArgumentException("참여 가능한 플레이어는 최소 1명, 최대 5명 입니다.");
        }
    }

    public void receiveCards(CardDeck cardDeck) {
        for (Participant participant : players) {
            participant.receive(cardDeck.popCard());
        }
    }

    public Player findByName(String name) {
        return players.stream()
                .filter(participant -> participant instanceof Player)
                .map(player -> (Player) player)
                .filter(player -> player.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("예기치 못한 에러가 발생했습니다."));
    }

    public List<String> getPlayersName() {
        return players.stream()
                .filter(participant -> participant instanceof Player)
                .map(player -> (Player) player)
                .map(Player::getName)
                .toList();
    }
}
