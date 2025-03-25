package domain.betting;

import domain.participant.PlayerName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BetMonies {
    private final List<PlayerBetMoney> betMonies;

    public BetMonies(List<PlayerBetMoney> betMonies) {
        this.betMonies = new ArrayList<>(betMonies);
    }

    public BetMonies(Map<String, Integer> betMonies) {
        this.betMonies = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : betMonies.entrySet()) {
            PlayerBetMoney playerBetMoney = new PlayerBetMoney(
                    new PlayerName(entry.getKey()),
                    new Money(entry.getValue()));
            this.betMonies.add(playerBetMoney);
        }
    }

    public PlayerBetMoney findByPlayerName(PlayerName playerName) {
        return betMonies.stream()
                .filter(betMoney -> betMoney.isSameName(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 이름의 배팅 정보가 없습니다."));
    }
}
