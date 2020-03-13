package domain.card;

import java.util.List;

import static domain.card.Deck.INIT_CARDS_SIZE;

public class PlayingCards {
    private static final int ACE_BONUS = 10;
    private static final int BLACKJACK_MAX_NUMBER = 21;

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
        if (card.isAce() && result + ACE_BONUS <= BLACKJACK_MAX_NUMBER) {
            return result + ACE_BONUS;
        }
        return result;
    }

    public int countCards() {
        return cards.size();
    }

    public boolean isBust() {
        return BLACKJACK_MAX_NUMBER < calculateScore();
    }

    public boolean isNotBust() {
        return BLACKJACK_MAX_NUMBER >= calculateScore();
    }

    public boolean isBlackJack() {
        return countCards() == INIT_CARDS_SIZE && calculateScore() == BLACKJACK_MAX_NUMBER;
    }

    public boolean isNotBlackJack() {
        return countCards() != INIT_CARDS_SIZE || calculateScore() != BLACKJACK_MAX_NUMBER;
    }

    public List<Card> getCards() {
        return cards;
    }
}
