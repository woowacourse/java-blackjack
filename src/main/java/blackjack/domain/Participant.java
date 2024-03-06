package blackjack.domain;

public abstract class Participant {
    protected final Hands hands;

    protected Participant(final Hands hands) {
        this.hands = hands;
    }

    public Score calculate() {
        int sum = hands.sum();
        int aceCount = hands.countAce();

        while (sum > BlackjackStatus.BLACKJACK_NUMBER && aceCount > 0) {
            sum = sum - 10;
            aceCount--;
        }
        return new Score(sum);
    }

    public void addCard(final Card card) {
        hands.add(card);
    }

    public BlackjackStatus getStatus() {
        return BlackjackStatus.from(calculate());
    }

    public Hands getHands() {
        return new Hands(hands.getCards());
    }
}
