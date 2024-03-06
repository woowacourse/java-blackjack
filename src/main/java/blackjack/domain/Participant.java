package blackjack.domain;

public abstract class Participant {
    protected final Cards cards;

    protected Participant(final Cards cards) {
        this.cards = cards;
    }

    public Score calculate() {
        int sum = cards.sum();
        int aceCount = cards.countAce();

        while (sum > BlackjackStatus.BLACKJACK_NUMBER && aceCount > 0) {
            sum = sum - 10;
            aceCount--;
        }
        return new Score(sum);
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public BlackjackStatus getStatus() {
        return BlackjackStatus.from(calculate());
    }
}
