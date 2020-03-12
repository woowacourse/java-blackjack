package domain.card;

import java.util.List;

public class PlayingCards {
    private static final int ACE_BONUS = 10;
    private static final int BLACK_JACK = 21;

    private List<Card> cards;

    public PlayingCards(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        return cards.stream()
                .reduce(sumScore(), this::addAceBonus, Integer::sum);
    }

    private int sumScore() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    private int addAceBonus(int result, Card card) {
        if ((card.isAce()) && (result + ACE_BONUS <= BLACK_JACK)) {
            return result + ACE_BONUS;
        }
        return result;
    }

    public int countCards() {
        return cards.size();
    }

    public boolean isBust() {
        return BLACK_JACK < calculateScore();
    }

    public boolean isNotBust() {
        return BLACK_JACK >= calculateScore();
    }

    public List<Card> getCards() {
        return cards;
    }
}
