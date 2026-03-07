package blackjack.model;

import java.util.List;

public abstract class Participant {
    private final String name;
    private final Hand hand;

    public Participant(String name, Hand hand) {
        validate(name);
        this.name = name;
        this.hand = hand;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("참가자의 이름은 null 이거나 empty 일 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    public void hit(Card card) {
        hand.addCard(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public abstract List<Card> getVisibleCards();
}
