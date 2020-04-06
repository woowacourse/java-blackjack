package domain.gamer;

import domain.card.Card;
import domain.card.providable.CardProvidable;
import domain.result.score.Score;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void drawCard(CardProvidable cardProvidable) {
        cards.add(cardProvidable.giveCard());
    }

    public Score calculateDefaultSum() {
        return cards.stream()
                .map(Card::extractScore)
                .reduce(Score.of(0), (a, b) -> a.plus(b));
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public Card getFirstCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("핸드가 비어있습니다.");
        }

        return cards.get(0);
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public List<Card> getCards() {
        return cards;
    }
}

