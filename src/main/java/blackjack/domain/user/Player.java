package blackjack.domain.user;

import blackjack.domain.card.Deck;

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

    public void askDraw(String askIfMoreCard, Deck deck) {
        if (YES.equals(askIfMoreCard)) {
            draw(deck.pickSingleCard());
            return;
        }

        hand.convertStatusToStay();
    }

    @Override
    public boolean isHit() {
        return hand.getStatus() == HandStatus.HIT;
    }

    @Override
    public String getName() {
        return name;
    }
}
