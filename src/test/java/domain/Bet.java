package domain;

import domain.participant.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bet {

    private final Map<Name, Integer> betHistory = new HashMap<>();

    public Bet(List<Name> playerNames) {
        playerNames.forEach(
                playerName -> betHistory.put(playerName, 0)
        );
    }

    public void bettingMoney(Name firstPlayer, int bettingMoney) {
        validatePlayer(firstPlayer);
        validateBettingMoney(bettingMoney);
    }

    private void validatePlayer(Name firstPlayer) {
        boolean notMatch = betHistory.keySet().stream()
                .noneMatch(name -> name.equals(firstPlayer));

        if (notMatch) {
            throw new IllegalArgumentException("[ERROR] 게임에 참여한 플레이어만 배팅이 가능합니다.");
        }
    }

    private void validateBettingMoney(int bettingMoney) {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 양수값만 가능합니다.");
        }
    }
}
