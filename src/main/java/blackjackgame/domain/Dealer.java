package blackjackgame.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Dealer {
    List<Card> cards;

    public Dealer() {
        this.cards = new ArrayList<>();
    }

    public int getScore() {
        int totalScore = 0;
        for (Card card : cards) {
            totalScore += card.getScore();
        }
        return totalScore;
    }

    // TODO : 메서드 네이밍 고민 후 수정하기
    public boolean isPick() {
        return getScore() <= 16;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Collection<Object> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
