package domain;

import domain.enums.GameResult;
import domain.participant.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bet {

    private final Map<Name, Integer> betHistory = new HashMap<>();
    private final Map<Name, Integer> betProfit = new HashMap<>();

    public Bet(List<Name> playerNames) {
        playerNames.forEach(
                playerName -> {
                    betHistory.put(playerName, 0);
                    betProfit.put(playerName, 0);
                }
        );
    }

    public void bettingMoney(Name playerName, int bettingMoney) {
        Name foundPlayer = validatePlayer(playerName);
        validateBettingMoney(bettingMoney);
        betHistory.put(playerName, bettingMoney);
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

    public int calculateProfit(Name firstPlayer, GameResult gameResult) {
        Name foundPlayer = validatePlayer(firstPlayer);
        int betAmount = betHistory.get(foundPlayer);

        if (gameResult == GameResult.LOSE) {
            betAmount = negateBetAmount(betAmount);
        }

        if (gameResult == GameResult.DRAW) {
            betAmount = 0;
        }

        betProfit.put(foundPlayer, betAmount);
        return betAmount;
    }

    private int negateBetAmount(int betAmount) {
        return -betAmount;
    }
}
