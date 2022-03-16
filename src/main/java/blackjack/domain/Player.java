package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private final String name;
    private final Cards myCards;

    public Player(String name) {
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
}
