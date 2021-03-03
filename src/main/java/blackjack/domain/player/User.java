package blackjack.domain.player;

public class User extends AbstractPlayer {
    private static final String YES = "y";

    public User(String name) {
        super(name);
    }

    @Override
    public boolean isCanDraw() {
        return !(isDrawStop() || isOverBlackJack());
    }

    public boolean isDrawContinue(String input) {
        drawInputValidate(input);
        if (YES.equals(input)) {
            return true;
        }
        stopDraw();
        return false;
    }

    private void drawInputValidate(String value) {
        if (!(YES.equals(value) || "n".equals(value))) {
            throw new IllegalArgumentException("입력은 y 또는 n만 가능합니다.");
        }
    }

    private boolean isOverBlackJack() {
        return getValue() > BLACKJACK;
    }
}
