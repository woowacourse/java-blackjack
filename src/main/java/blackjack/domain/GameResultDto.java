package blackjack.domain;

public class GameResultDto {

    private final String name;
    private final double earning;

    public GameResultDto(final String name, final double earning) {
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
