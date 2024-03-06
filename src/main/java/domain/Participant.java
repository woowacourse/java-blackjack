package domain;

public class Participant {

    protected final Cards hand;

    public Participant() {
        hand = new Cards();
    }

    public void receive(final Card card) {
        hand.add(card);
    }

    public int cardSum() {
        int total = hand.sum();
        if (hasAceAsEleven(total)) {
            return total + 10;
        }
        return total;
    }

    private boolean hasAceAsEleven(final int total) {
        return hand.hasAce() && total + 10 <= 21;
    }

    public Cards cards() {
        return hand;
    }
}
