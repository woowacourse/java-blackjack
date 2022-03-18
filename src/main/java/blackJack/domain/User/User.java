package blackJack.domain.User;

import blackJack.domain.Card.Card;
import blackJack.domain.Card.Cards;
import blackJack.utils.ExeptionMessage;

import java.util.ArrayList;
import java.util.List;

import static blackJack.domain.Card.CardFactory.CARD_CACHE;

public abstract class User {

    protected String name;
    protected Cards cards;

    public User(String name) {
        this.name = name;
        this.cards = new Cards(new ArrayList<>());
    }

    public void requestCard(Card card) {
        this.cards.add(card);
    }

    public void dealCard(Card card) {
        this.cards.add(card);
    }

    public abstract boolean isPossibleToAdd();

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getDeck();
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public boolean isBlackJack() {
        if (cards.isOnlyTwoCards() && cards.calculateScore() == 21) {
            return true;
        }
        return false;
    }

}
