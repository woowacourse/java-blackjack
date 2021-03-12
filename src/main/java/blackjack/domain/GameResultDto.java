package blackjack.domain;

import java.util.Map;

public class GameResultDto {

    private final String name;
    private final double earning;

    public GameResultDto(String name, double earning) {
        this.name = name;
        this.earning = earning;
    }

    public String getName() {
        return name;
    }

    public double getEarning() {
        return earning;
    }
}
