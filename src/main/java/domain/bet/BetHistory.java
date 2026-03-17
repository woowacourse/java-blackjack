package domain.bet;

import static message.ErrorMessage.PLAYER_NOT_IN_GAME;

import domain.participant.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BetHistory {
    private final Map<Name, Money> betHistory = new HashMap<>();

    public BetHistory(List<Name> playerNames) {
        playerNames.forEach(
                playerName -> {
                    betHistory.put(playerName, Money.zero());
                }
        );
    }

    public void bettingMoney(Name playerName, long bettingMoney) {
        validatePlayer(playerName);
        betHistory.put(playerName, new Money(bettingMoney));
    }

    private void validatePlayer(Name playerName) {
        if (!betHistory.containsKey(playerName)) {
            throw new IllegalArgumentException(PLAYER_NOT_IN_GAME.getMessage());
        }
    }

    public Map<Name, Money> getBetHistory() {
        return Map.copyOf(betHistory);
    }
}
