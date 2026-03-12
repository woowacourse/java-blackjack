package blackjack.domain;

import java.util.Arrays;

public enum GameResult {
    WIN("승", 1),
    TIE("무", 0),
    LOSE("패", -1),
    BLACKJACK_WIN("블랙잭승", 1.5);

    private final String name;
    private final double earningRate;

    GameResult(String name, double earningRate) {
        this.name = name;
        this.earningRate = earningRate;
    }

    public String getName() {
        return name;
    }

    public double getEarningRate() {
        return earningRate;
    }

    public static GameResult fromName(String name) {
        return Arrays.stream(GameResult.values())
                .filter(result -> result.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 게임 결과를 찾을 수 없습니다: " + name));
    }
}
