package blackjack.domain;

public abstract class Participant {

    protected static final int BLACK_JACK_SCORE = 21;
    protected static final int ACE_ALTER_VALUE = 10;

    protected final Cards cards;

    public Participant() {
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int sum = cards.sum();
        int aceCount = cards.getAceCount();

        while (sum > BLACK_JACK_SCORE && aceCount > 0) {
            sum -= ACE_ALTER_VALUE;
            aceCount -= 1;
        }
        return sum;
    }

    abstract boolean canReceive();

    public Cards getCards() {
        return cards;
    }
}
