package balckjack.domain;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private static final int BLACKJACK_CARD_COUNT = 2;

    private final List<Card> cards;

    public CardDeck() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        validateNull(card);
        cards.add(card);
    }

    private void validateNull(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("아무런 카드도 입력되지 않았습니다.");
        }
    }

    public Score calculateScore() {
        Score standardSum = new Score(
            cards.stream().filter(card -> !card.isAce()).mapToInt(Card::getValue).sum());
        int aceCount = findAceCount();
        if (aceCount > 0) {
            return standardSum.addAceScore(aceCount);
        }
        return standardSum;
    }

    private int findAceCount() {
        return Math.toIntExact(cards.stream()
            .filter(Card::isAce)
            .count());
    }

    public boolean isBlackJack() {
        Score score = calculateScore();
        int count = cards.size();
        return score.equals(Score.BLACKJACK_SCORE) && count == BLACKJACK_CARD_COUNT;
    }

    public List<Card> getCards() {
        return cards;
    }
}
