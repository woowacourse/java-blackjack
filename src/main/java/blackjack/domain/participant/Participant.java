package blackjack.domain.participant;

import blackjack.domain.deck.Card;
import java.util.List;

public abstract class Participant {

    protected final Name name;
    protected final Hands hands;

    public Participant(String name) {
        this.name = new Name(name);
        this.hands = new Hands();
    }

    public boolean addCard(Card card) {
        hands.addCard(card);
        return !isBurst();
    }

    public List<Card> getHandsCards() {
        return hands.getHands();
    }

    public String getName() {
        return name.getName();
    }

    public int getHandsScore() {
        return hands.getHandsScore()
                    .getScore();
    }

    public boolean isBurst() {
        return hands.isBurst();
    }
}
