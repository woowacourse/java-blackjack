package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Status;

public class Player extends Participant {

    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        validateEmptyName(name);
        validateNameLength(name);
    }

    private void validateEmptyName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("공백은 허용되지 않습니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > 100) {
            throw new IllegalArgumentException("길이는 100자를 초과할 수 없습니다.");
        }
    }

    void hit(Card card) {
        cards.add(card);
        updateStatus();
    }

    private void updateStatus() {
        if (cards.getStatus() == Status.BUST) {
            super.status = Status.BUST;
        }
    }

    public void stay() {
        status = Status.STAY;
    }

    public String getName() {
        return name;
    }
}
