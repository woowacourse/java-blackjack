package domain.participant;

import domain.card.Card;
import domain.result.Score;
import java.util.List;

public abstract class Participant {
    private final Hand hand;
    private final Name name;

    public Participant(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public boolean isFirstBlackJack() {
        return hand.size() == 2 && hand.calculateScore().isBlackjack();
    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public Score getScore() {
        return hand.calculateScore();
    }

    public String getName() {
        return name.name();
    }

    public List<String> getCardNames() {
        return hand.cards().stream()
                .map(Card::getCardName)
                .toList();
    }

    public String getFirstName() {
        return getCardNames().getFirst();
    }

    public abstract boolean canDraw();
}
