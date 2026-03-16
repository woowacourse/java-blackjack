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

    public void bettingMoney(Name playerName, int bettingMoney) {
        Name foundPlayer = validatePlayer(playerName);
        betHistory.put(foundPlayer, new Money(bettingMoney));
    }

    private Name validatePlayer(Name playerName) {
        return betHistory.keySet().stream()
                .filter(name -> name.equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_NOT_IN_GAME.getMessage()));
    }

    public Map<Name, Money> getBetHistory() {
        return Map.copyOf(betHistory);
    }
}
