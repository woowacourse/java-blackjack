package blackjack.domain.participant;

import blackjack.domain.BetAmount;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Gamer {

    protected final Cards cards;
    protected final BetAmount betAmount;

    public Gamer(final Cards cards) {
        this.cards = cards;
        this.betAmount = new BetAmount(0);
    }

    abstract public boolean canGetMoreCard();

    abstract public String getNickname();

    public List<Card> showAllCard() {
        return cards.getCards();
    }

    public void receiveCards(final Cards givenCards) {
        cards.addAll(givenCards);
    }

    public int calculateMaxSum() {
        return cards.calculateResult();
    }

    public void bet(final int betAmount) {
        this.betAmount.setMoney(betAmount);
    }
}
