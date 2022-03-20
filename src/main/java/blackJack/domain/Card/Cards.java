package blackJack.domain.Card;

import java.util.*;

public class Cards {
    public static final int BUST_LINE = 21;
    public static final int EXTRA_SCORE = 10;

    private List<Card> cards;

    public Cards(List<Card> cards) {
        Collections.shuffle(new ArrayList<>(cards));
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();
        if(cards.stream().anyMatch(Card::isAce)){
            score = addAceScore(score);
        }

        return score;
    }

    private int addAceScore(int score) {
        if (score + EXTRA_SCORE <= BUST_LINE) {
            score += EXTRA_SCORE;
        }
        return score;
    }

    public boolean isOnlyTwoCards() {
        return cards.size() == 2;
    }

    public Object getFirst() {
        return cards.get(0).getCardInfo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
