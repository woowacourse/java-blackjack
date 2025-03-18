package domain.betting;

import java.util.ArrayList;
import java.util.List;

public class BetMonies {
    private final List<BetMoney> betMonies;

    public BetMonies(List<BetMoney> betMonies) {
        this.betMonies = new ArrayList<>(betMonies);
    }

    public BetMoney findByPlayerName(String playerName) {
        return betMonies.stream()
                .filter(betMoney -> betMoney.isSameName(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 이름의 배팅 정보가 없습니다."));
    }
}
