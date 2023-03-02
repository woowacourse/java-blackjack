package blackjack.domain;

public class Participant {

    private static final int BLACK_JACK_SCORE = 21;
    private static final int ACE_ALTER_VALUE = 10;

    private final Cards cards;

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

    public Cards getCards() {
        return cards;
    }
}
