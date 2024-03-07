package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cards;

    public Player(final String name) {
        validateName(name);
        this.name = name;
        cards = new ArrayList<>();
    }

    private void validateName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    public void saveCard(final Card card) {
        cards.add(card);
    }

    public void saveCards(final List<Card> cards) {
        this.cards.addAll(cards);
    }

    public int calculateScoreWhileDraw() {
        int sum = 0;
        for (Card card : cards) {
            if (card.isAceCard()) {
                sum += 1;
                continue;
            }
            sum += card.getScore();
        }
        return sum;
    }


    public int calculateScore(final int blackJackScore) {
        int sum = cards.stream()
                .map(Card::getScore)
                .mapToInt(score -> score)
                .sum();
        int aceCardCount = 0;
        for (Card card : cards) {
            if (card.isAceCard()) {
                aceCardCount++;
            }
        }
        while (aceCardCount > 0 && sum > blackJackScore) {
            aceCardCount--;
            sum -= 10;
        }
        return sum;
    }

    public boolean isBlackJack(final int blackJackScore) {
        return this.calculateScore(blackJackScore) == blackJackScore;
    }

    public int getTotalSize() {
        return cards.size();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }


    public boolean hasMoreCard(final Dealer dealer) {
        return getTotalSize() > dealer.getTotalSize();
    }

    public boolean hasMoreScore(final Dealer dealer) {
        return calculateScore(21) > dealer.calculateScore(21);
    }

    public boolean hasSameScore(final Dealer dealer) {
        return calculateScore(21) == dealer.calculateScore(21);
    }
}
