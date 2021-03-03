package blackjack.domain.player;

import blackjack.domain.card.Cards;

public class User extends AbstractPlayer {
    private boolean isDrawStop = false;

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

    public void draw(String value) {
        drawInputValidate(value);
        if ("y".equals(value)) {
            drawCard(Cards.getCards().draw());
            return;
        }
        isDrawStop = true;
    }

    private void drawInputValidate(String value) {
        if (!("y".equals(value) || "n".equals(value))) {
            throw new IllegalArgumentException("입력은 y 또는 n만 가능합니다.");
        }
    }

    public boolean isDrawStop() {
        return isDrawStop;
    }

    private boolean isOverBlackJack() {
        return getValue() > BLACKJACK;
    }
}
