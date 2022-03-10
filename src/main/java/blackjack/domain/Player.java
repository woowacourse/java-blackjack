package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final int MAX_SCORE = 21;
    private String name;
    private List<Card> myCards;

    public Player(String name) {
        this.name = name;
        this.myCards = new ArrayList<>();
    }

    public void addCard(Card card) {
        myCards.add(card);
    }

    public List<Card> getMyCards() {
        return myCards;
    }

    public String getName() {
        return name;
    }

    public int score() {
        return myCards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }
    
    public boolean isBurst() {
        return score() > MAX_SCORE;
    }
}
