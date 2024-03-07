package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class PlayerCards {
    private final List<Card> cards;

    public PlayerCards(List<Card> cards) {
        this.cards = cards;
    }

    public static PlayerCards createEmptyCards() {
        return new PlayerCards(new ArrayList<>());
    }

    public void append(Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        int scoreValue = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        Score score = new Score(scoreValue);
        int currentAceAmount = getAceCount();

        if (currentAceAmount > 0 && score.isBusted()) {
            return score.convertToSmallAce(currentAceAmount);
        }
        return score;
    }

    public boolean isBusted() {
        Score score = calculateScore();
        return score.isBusted();
    }

    private int getAceCount() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    public List<Card> getCards() {
        return cards;
    }
}
