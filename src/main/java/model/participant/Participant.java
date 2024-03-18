package model.participant;

import java.util.Collections;
import java.util.List;
import model.card.Card;
import model.card.Cards;
import model.game.action.CheckAction;
import model.game.action.HitAction;

public abstract class Participant implements HitAction, CheckAction {

    private final Name name;
    private Cards cards;

    protected Participant(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    @Override
    public void hit(Card card) {
        cards = cards.add(card);
    }

    @Override
    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    @Override
    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    @Override
    public boolean isNotBurst() {
        return !isBurst();
    }

    @Override
    public boolean isBurst() {
        return cards.isBurst();
    }

    public int cardSize() {
        return cards.size();
    }

    public int score() {
        return cards.score();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }
}
