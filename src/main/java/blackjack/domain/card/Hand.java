package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_SIZE = 2;
    private final List<Card> cards;

    Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = getMaxScore();
        int cardIndex = 0;
        while (score > BLACKJACK_SCORE && cardIndex < cards.size()) {
            Card card = cards.get(cardIndex);
            score = score + card.getMinScore() - card.getMaxScore();
            cardIndex++;
        }
        return score;
    }

    private int getMaxScore() {
        return cards.stream()
                .mapToInt(Card::getMaxScore)
                .sum();
    }

    public boolean isBusted() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_SIZE && calculateScore() == BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
