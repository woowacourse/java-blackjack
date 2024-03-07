package blackjack.domain;

import java.util.List;

abstract class Participant {

    private final Name name;
    protected final Hand hand;

    protected Participant(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public void hit(Card card) {
        validateIsPlayable();

        hand.add(card);
    }

    private void validateIsPlayable() {
        if (!isPlayable()) {
            throw new IllegalStateException("카드를 더이상 받을 수 없습니다.");
        }
    }

    protected abstract boolean isPlayable();

    public List<Card> getCards() {
        return hand.getCards();
    }

    public String getName() {
        return name.getValue();
    }
}
