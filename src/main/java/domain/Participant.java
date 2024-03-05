package domain;

public class Participant {

    protected final Cards hold;

    public Participant() {
        hold = new Cards();
    }

    public void receive(final Card card) {
        hold.add(card);
    }
}
