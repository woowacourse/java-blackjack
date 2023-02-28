package blackjackgame.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Guest {
    Name name;
    List<Card> cards;

    public Guest(Name name, Card firstCard, Card secondCard) {
        this.name = name;
        this.cards = new ArrayList<>(List.of(firstCard, secondCard));
    }

    public int getScore() {
        int totalScore = 0;
        for (Card card : cards) {
            totalScore += card.getScore();
        }
        return totalScore;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Collection<Object> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
