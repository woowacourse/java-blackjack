package domain;

public class Participant {

    protected final Name name;
    protected final Cards hand;

    public Participant(final Name name) {
        this.name = name;
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

    public boolean isBust() {
        return hand.sum() > 21;
    }

    public boolean isBlackjack() {
        return hand.sum() == 21;
    }

    public Name name() {
        return name;
    }

    public Cards cards() {
        return hand;
    }
}
