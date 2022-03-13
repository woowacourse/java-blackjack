package BlackJack.domain.User;

import BlackJack.domain.Card.Card;
import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.Card.Cards;
import BlackJack.domain.Result;
import BlackJack.utils.ExeptionMessage;

import java.util.ArrayList;

import static BlackJack.domain.Card.CardFactory.CARD_CACHE;

public abstract class User {

    protected String name;
    protected Cards cards;
    protected Result result;

    public User(String name) {
        this.name = name;
        this.cards = new Cards(new ArrayList<>());
    }

    public void addCard(){
        if(checkPossibleAdd()){
            Card card = CARD_CACHE.poll();
            this.cards.add(card);
            return;
        }
        throw new IllegalArgumentException(ExeptionMessage.CANNOT_ADD_CARD);
    }

    public void initCard(Card card){
        this.cards.add(card);
    }

    public abstract boolean checkPossibleAdd();

    public abstract boolean checkBlackJack();

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public int getScore() {
        return cards.calculateScore();
    }
}
