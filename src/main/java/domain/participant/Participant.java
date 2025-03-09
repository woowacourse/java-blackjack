package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.rule.BlackjackRule;
import domain.rule.GameRule;

public class Participant {
    private final Cards cards;
    private final GameRule rule;

    protected Participant(Cards cards) {
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

    public GameRule getRule() {
        return rule;
    }
}
