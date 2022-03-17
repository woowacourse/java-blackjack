package blackjack.domain;

import java.util.Collections;
import java.util.List;

public abstract class Participant {
    private final String name;
    private final Cards myCards;

    public Participant(String name) {
        this.name = name;
        this.myCards = Cards.generateCards();
    }

    public void addCard(Card card) {
        myCards.addCard(card);
    }

    public List<Card> getMyCards() {
        return Collections.unmodifiableList(myCards.getCards());
    }

    public String getName() {
        return name;
    }

    public int score() {
        return myCards.score();
    }

    public boolean isBurst() {
        return myCards.isBurst(score());
    }

    public abstract Cards pickOpenCardsInInitCards();
}
