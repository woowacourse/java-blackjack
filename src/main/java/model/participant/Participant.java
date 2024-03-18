package model.participant;

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

    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    @Override
    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

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

    public String getFirstCard() {
        return cards.getCards()
            .get(0)
            .toString();
    }

    public List<String> getCardsInfo() {
        return cards.getCards()
            .stream()
            .map(Card::toString)
            .toList();
    }
}
