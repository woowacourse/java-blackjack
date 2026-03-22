package domain.participant;

import constant.PolicyConstant;
import domain.card.Card;
import exception.ErrorMessage;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = List.copyOf(players);
    }

    private void validate(List<Player> players) {
        validatePlayerCountOutOfRange(players.size());
    }

    private void validatePlayerCountOutOfRange(int playerCount) {
        if (!(PolicyConstant.PLAYER_MIN_COUNT <= playerCount
            && playerCount <= PolicyConstant.PLAYER_MAX_COUNT)) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE.getMessage());
        }
    }

    public void addCardPlayer(Name name, Card card) {
        getPlayerByName(name).addCard(List.of(card));
    }

    public int calculateScore(Name name) {
        return getPlayerByName(name).calculateScore();
    }

    public Player getPlayerByName(Name name) {
        return players.stream()
            .filter(player -> player.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.PLAYER_NOT_FOUND.getMessage()));
    }

    public List<Player> getAllPlayers() {
        return List.copyOf(players);
    }
}
