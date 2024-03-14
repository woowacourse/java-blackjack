package domain.card;

import static domain.BlackjackGame.BLACKJACK_SCORE;

public class Hand {
    private static final int ACE_AS_ELEVEN = 10;
    private final Cards cards;

    public Hand() {
        this(new Cards());
    }

    public Hand(Cards cards) {
        this.cards = cards;
    }

    public Hand(Hand hand) {
        this(hand.cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Card peek() {
        return cards.peek();
    }

    public int score() {
        int total = cards.sum();
        if (hasAceAsEleven(total)) {
            return total + ACE_AS_ELEVEN;
        }
        return total;
    }

    private boolean hasAceAsEleven(final int total) {
        return cards.hasAce() && total + ACE_AS_ELEVEN <= BLACKJACK_SCORE;
    }

    public Cards getCards() {
        return new Cards(cards);
    }
}
