package blackjack.domain.strategy.hit;

import java.util.function.Supplier;

public class PlayerHitStrategy implements HitStrategy {

    public static final String YES = "y";
    public static final String NO = "n";

    private Supplier<String> supplier;

    public PlayerHitStrategy(Supplier<String> supplier) {
        this.supplier = supplier;
    }

    private void validateYesOrNo(String input) {
        if (!(YES.equalsIgnoreCase(input) || NO.equalsIgnoreCase(input))) {
            throw new IllegalArgumentException("입력한 값이 유효하지 않습니다. y 또는 n을 입력해주세요.");
        }
    }

    @Override
    public boolean isHit() {
        String input = supplier.get();
        validateYesOrNo(input);
        return input.equalsIgnoreCase(YES);
    }
}
