package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Cards {

    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_SPECIAL_SCORE = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void receive(Card receivedCard) {
        cards.add(receivedCard);
    }

    public void receive(List<Card> receivedCards) {
        cards.addAll(receivedCards);
    }

    public int calculateScore() {
        final int score = initialScore();
        if (score > BLACKJACK_SCORE) {
            return score;
        }

        return IntStream.rangeClosed(0, countAce())
            .map(index -> (score + index * ACE_SPECIAL_SCORE))
            .filter(number -> number <= BLACKJACK_SCORE)
            .max()
            .orElseThrow(() -> new IllegalStateException("[ERROR] 점수 계산에 실패했습니다."));
    }

    private int initialScore() {
        return cards.stream()
            .mapToInt(Card::score)
            .sum();
    }

    private int countAce() {
        return (int) cards.stream()
            .filter(card -> card.getRank() == Rank.ACE)
            .count();
    }

    public List<Card> getCards() {
        return cards;
    }
}
