package domain.participant;

import constant.PolicyConstant;
import domain.card.Card;
import exception.ErrorMessage;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
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

    public static Players from(Names names) {
        List<Player> players = names.value().stream()
            .map(Player::new)
            .toList();
        return new Players(players);
    }

    public void addCardPlayer(int playerIndex, Card card) {
        players.get(playerIndex).addCard(List.of(card));
    }

    public Player getPlayerByIndex(int playerIndex) {
        return players.get(playerIndex);
    }

    public List<Player> getAllPlayers() {
        return players.stream().toList();
    }
}
