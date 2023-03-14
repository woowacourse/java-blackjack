package domain.participant;

import domain.card.Card;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Hand {
    public static final int ADDITIONAL_SCORE_OF_ACE = 10;
    private final Deque<Card> cards;

    public Hand() {
        cards = new LinkedList<>();
    }

    public void add(Card card) {
        if (card.isAce()) {
            cards.addLast(card);
            return;
        }
        cards.addFirst(card);
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::score)
                .sum();
        Card lastCard = cards.getLast();
        if (!lastCard.isAce()) {
            return score;
        }
        return addExtraScoreIfAce(score);
    }

    private int addExtraScoreIfAce(int score) {
        if (score + ADDITIONAL_SCORE_OF_ACE > 21) {
            return score;
        }
        return score + ADDITIONAL_SCORE_OF_ACE;
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }
}
