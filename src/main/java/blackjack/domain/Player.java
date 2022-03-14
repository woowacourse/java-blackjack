package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private static final int MAX_SCORE = 21;
    private static final int ADJUST_ACE_SCORE = 10;
    private static final int SUBSTRING_INDEX_START = 0;
    private static final int SUBSTRING_INDEX_END = 1;

    private static final String ACE = "A";

    private final String name;
    private final List<Card> myCards;

    public Player(String name) {
        this.name = name;
        this.myCards = new ArrayList<>();
    }

    public void addCard(Card card) {
        myCards.add(card);
    }

    public List<Card> getMyCards() {
        return Collections.unmodifiableList(myCards);
    }

    public String getName() {
        return name;
    }

    private int rawScore() {
        return myCards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    public int score() {
        int score = rawScore();
        if (isBurst() && containAceCard()) {
            score -= ADJUST_ACE_SCORE;
        }
        return score;
    }
    
    public boolean isBurst() {
        return rawScore() > MAX_SCORE;
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    public boolean containCard(Card card) {
        return myCards.contains(card);
    }

    public boolean containAceCard() {
        List<String> cardNames = myCards.stream()
                .map(Card::getName)
                .map(name -> name.substring(SUBSTRING_INDEX_START, SUBSTRING_INDEX_END))
                .collect(Collectors.toList());
        return cardNames.contains(ACE);
    }
}
