package blackjack.domain.player;

public class User extends AbstractPlayer {

    public User() {
        super();
    }

    public User(String name) {
        super(name);
    }

    @Override
    public boolean isCanDraw() {
        return !(isDrawStop() || isOverBlackJack());
    }


    public boolean isDrawContinue(String input) {
        drawInputValidate(input);
        if ("y".equals(input)) {
            return true;
        }
        stopDraw();
        return false;
    }

    private void drawInputValidate(String value) {
        if (!("y".equals(value) || "n".equals(value))) {
            throw new IllegalArgumentException("입력은 y 또는 n만 가능합니다.");
        }
    }

    private boolean isOverBlackJack() {
        return getValue() > BLACKJACK;
    }
}
