package domain.user;

import domain.card.Card;
import domain.game.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HandCard {
    private final List<Card> cards;

    public HandCard() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public String getNames() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(","));
    }

    public int getScore() {
        int sum = cards.stream()
                .mapToInt(Card::getValue)
                .sum();
        return getAceScore(sum);
    }

    private int getAceScore(int sum) {
        if (sum <= (Rule.getBlackjackFullScore() - Rule.getAceBonusScore()) && hasAce()) {
            return sum + Rule.getAceBonusScore();
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isOver() {
        return Rule.getBlackjackFullScore() < getScore();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public boolean isBlackJack() {
        return Rule.getBlackjackFullScore() == getScore();
    }

    public int getSize() {
        return cards.size();
    }
}
