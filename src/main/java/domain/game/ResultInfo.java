package domain.game;

import java.util.Arrays;

public enum ResultInfo {

    WIN("승", 1.0),
    DRAW("무", 0.0),
    DEFEAT("패", -1.0),
    BLACKJACK_WIN("블랙잭승", 1.5);

    private final String info;
    private final Double yield;

    ResultInfo(String info, Double yield) {
        this.info = info;
        this.yield = yield;
    }

    public String getInfo() {
        return info;
    }

    public Double getYield() {
        return yield;
    }

    public static ResultInfo from(String info){
        return Arrays.stream(values())
                .filter(resultInfo -> resultInfo.info.equals(info))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("[ERROR] 해당하는 결과를 찾을 수 없습니다!"));
    }
}
