package domain.betting;

import java.util.ArrayList;
import java.util.List;

public class BatMonies {
    private final List<BatMoney> batMonies;

    public BatMonies(List<BatMoney> batMonies) {
        this.batMonies = new ArrayList<>(batMonies);
    }

    public BatMoney findByPlayerName(String playerName) {
        return batMonies.stream()
                .filter(batMoney -> batMoney.isSameName(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 이름의 배팅 정보가 없습니다."));
    }
}
