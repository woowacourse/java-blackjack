package domain;

public class Participant {
    private final Cards cards;
    protected final GameRule rule;

    public Participant(Cards cards) {
        this.cards = cards;
        this.rule = new BlackjackRule();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getCardScore() {
        return rule.getScore(cards);
    }

    public boolean isBurst() {
        return rule.isBurst(cards);
    }

    public Cards getCards() {
        return cards;
    }
}
