package domain;

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
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 게임에 참여한 플레이어만 배팅이 가능합니다."));
    }

    private void validateBettingMoney(int bettingMoney) {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 양수값만 가능합니다.");
        }
    }

    public Map<Name, Integer> getBetHistory() {
        return Map.copyOf(betHistory);
    }
}
