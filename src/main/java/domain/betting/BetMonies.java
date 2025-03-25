package domain.betting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BetMonies {
    private final List<BetMoney> betMonies;

    public BetMonies(List<BetMoney> betMonies) {
        this.betMonies = new ArrayList<>(betMonies);
    }

    public BetMonies(Map<String, Integer> betMonies) {
        this.betMonies = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : betMonies.entrySet()) {
            BetMoney betMoney = new BetMoney(entry.getKey(), entry.getValue());
            this.betMonies.add(betMoney);
        }
    }

    public BetMoney findByPlayerName(String playerName) {
        return betMonies.stream()
                .filter(betMoney -> betMoney.isSameName(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 이름의 배팅 정보가 없습니다."));
    }
}
