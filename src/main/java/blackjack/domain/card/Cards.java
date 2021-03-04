package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Score getScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getDenomination().getScore();
        }

        if(countAce() == 0){
            return new Score(score);
        }

        if (score + 10 <= 21) {
            score += 10;
        }

        return new Score(score);
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(card -> card.isAce())
                .count();
    }

    public boolean isBust(){
        return getScore().isBust();
    }

    public List<Card> cards(){
        return Collections.unmodifiableList(cards);
    }
}
