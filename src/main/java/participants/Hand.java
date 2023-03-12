package participants;

import java.util.ArrayList;
import java.util.List;

import card.Card;
import card.Score;

public class Hand {
    private static final int BLACK_JACK_CARD_COUNT = 2;
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Score calculateScore() {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        Score score = new Score(sum);
        if(containAce()) {
            return score.calculateAdditionalScore();
        }
        return score;
    }

    private boolean containAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public Card pickFirstCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("들고있는 카드가 없습니다.");
        }
        return cards.get(0);
    }

    public boolean isBlackjack() {
        return calculateScore().isMaxScore() && cards.size() == BLACK_JACK_CARD_COUNT;
    }
}
