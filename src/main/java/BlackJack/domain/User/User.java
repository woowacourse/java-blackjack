package BlackJack.domain.User;

import BlackJack.domain.Card.Card;
import BlackJack.domain.Card.Cards;
import BlackJack.domain.Result;

import java.util.ArrayList;

public abstract class User {

    protected String name;
    protected Cards cards;
    protected Result result;

    public User(String name) {
        this.name = name;
        this.cards = new Cards(new ArrayList<>());
    }

    public void addCard(Card card){
        if(checkPossibleAdd(cards.calculateScore())){
            this.cards.add(card);
        }
    }

    abstract boolean checkPossibleAdd(int currentScore);

    abstract void checkBlackJack();

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
