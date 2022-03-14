package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.result.ScoreCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected final List<Card> cards;
    protected final String name;

    public Participant(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    abstract public boolean isDealer();

    abstract public boolean isUser();

    public String getName() {
        return this.name;
    }

    public boolean checkName(String name) {
        return this.name.equals(name);
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getCardSum() {
        return ScoreCalculator.cardSum(cards);
    }

}
