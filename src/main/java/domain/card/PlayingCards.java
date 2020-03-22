package domain.card;

import java.util.List;

public class PlayingCards {
    private static final int BLACK_JACK = 21;
    private static final int BLACKJACK = 21;
    private static final int INIT_CARD_SIZE = 2;
    private List<Card> cards;

    public PlayingCards(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int calculateScore() {
        int result = cards.stream()
                .filter(card -> card.getSymbol().getValue() != Symbol.ACE.getValue())
                .mapToInt(Card::getValue)
                .sum();
        for (Card card : cards) {
            result = selectAce(result, card);
        }
        return result;
    }

    private int selectAce(int result, Card card) {
        if (card.getSymbol().getValue() == Symbol.ACE.getValue()) {
            card.changeAceCard(result);
            result += card.getValue();
        }
        return result;
    }

    public boolean isBlackJack() {
        return cards.size() == INIT_CARD_SIZE && calculateScore() == BLACKJACK;
    }

    public int size() {
        return cards.size();
    }

    public boolean isBust() {
        return BLACK_JACK < calculateScore();
    }
}
