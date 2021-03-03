package blackjack.domain.user;

import blackjack.domain.card.Card;

public class Player extends User {
    private static final String YES = "y";

    private final String name;

    private Player(String name) {
        validateNotEmptyName(name);
        this.name = name;
    }

    public static Player create(String name) {
        return new Player(name);
    }

    private void validateNotEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("빈 이름이 입력되었습니다.");
        }
    }

    @Override
    public boolean isHit() {
        return hand.getStatus() == HandStatus.HIT;
    }

    @Override
    public String getName() {
        return name;
    }

    public void askDraw(String askIfMoreCard, Card card) {
        if(YES.equals(askIfMoreCard)) {
            draw(card);
            return;
        }

        hand.convertStatusToStay();
    }
}
