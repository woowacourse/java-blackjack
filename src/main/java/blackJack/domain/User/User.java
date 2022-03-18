package blackJack.domain.User;

import blackJack.domain.Card.Card;
import blackJack.domain.Card.Cards;
import blackJack.utils.ExeptionMessage;

import java.util.ArrayList;
import java.util.List;

import static blackJack.domain.Card.CardFactory.CARD_CACHE;

public abstract class User {

    public static final int WINNING_LIMIT = 21;
    protected String name;
    protected Cards cards;
    protected BettingMoney bettingMoney;

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
        if (cards.isOnlyTwoCards() && cards.calculateScore() == WINNING_LIMIT) {
            return true;
        }
        return false;
    }

    public boolean isBurst(){
        return this.getScore() > WINNING_LIMIT;
    }


}
