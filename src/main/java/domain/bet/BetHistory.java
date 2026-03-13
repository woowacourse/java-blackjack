package domain.bet;

import static message.ErrorMessage.BETTING_MONEY_MUST_BE_MULTIPLE_OF_100;
import static message.ErrorMessage.BETTING_MONEY_NOT_AVAILABLE;
import static message.ErrorMessage.PLAYER_NOT_IN_GAME;

import domain.participant.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BetHistory {
    private final Map<Name, Integer> betHistory = new HashMap<>();

    public BetHistory(List<Name> playerNames) {
        playerNames.forEach(
                playerName -> {
                    betHistory.put(playerName, 0);
                }
        );
    }

    public void bettingMoney(Name playerName, int bettingMoney) {
        Name foundPlayer = validatePlayer(playerName);
        validateBettingMoney(bettingMoney);
        betHistory.put(foundPlayer, bettingMoney);
    }

    private Name validatePlayer(Name playerName) {
        return betHistory.keySet().stream()
                .filter(name -> name.equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_NOT_IN_GAME.getMessage()));
    }

    private void validateBettingMoney(int bettingMoney) {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException(BETTING_MONEY_NOT_AVAILABLE.getMessage());
        }

        if (bettingMoney % 100 != 0) {
            throw new IllegalArgumentException(BETTING_MONEY_MUST_BE_MULTIPLE_OF_100.getMessage());
        }
    }

    public Map<Name, Integer> getBetHistory() {
        return Map.copyOf(betHistory);
    }
}
