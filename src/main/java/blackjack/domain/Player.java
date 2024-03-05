package blackjack.domain;

public class Player {
    private final Name name;
    private final Cards cards;

    public Player(final Name name, final Cards cards) {
        this.name = name;
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

    public Name getName() {
        return name;
    }
}
