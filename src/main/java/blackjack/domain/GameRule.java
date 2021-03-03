package blackjack.domain;

public class GameRule {
    private static final int STANDARD = 16;

    public GameRule() {
    }

    public boolean isUnderStandard(int total){
        return total <= STANDARD;
    }
}
