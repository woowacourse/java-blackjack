package domain.participant;

import constant.PolicyConstant;
import domain.card.Card;
import exception.ErrorMessage;
import java.util.List;

public record Players(
    List<Player> value
) {

    public Players {
        validate(value);
    }

    private static void validate(List<Player> players) {
        validatePlayerCountOutOfRange(players.size());
    }

    private static void validatePlayerCountOutOfRange(int playerCount) {
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
        value.get(playerIndex).addCard(List.of(card));
    }

    public Player getPlayerByIndex(int playerIndex) {
        return value.get(playerIndex);
    }

    public List<Player> getAllPlayers() {
        return value.stream().toList();
    }
}
