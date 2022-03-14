package blackJack.domain.User;

import blackJack.domain.Card.Card;
import blackJack.domain.Card.Cards;
import blackJack.utils.ExeptionMessage;

import java.util.ArrayList;

import static blackJack.domain.Card.CardFactory.CARD_CACHE;

public abstract class User {

    protected String name;
    protected Cards cards;

    public User(String name) {
        this.name = name;
        this.cards = new Cards(new ArrayList<>());
    }

    public void requestAddCard(){
        if(isPossibleToAdd()){
            Card card = CARD_CACHE.poll();
            this.cards.add(card);
            return;
        }
        throw new IllegalArgumentException(ExeptionMessage.CANNOT_ADD_CARD);
    }

    public void initCard(Card card){
        this.cards.add(card);
    }

    public abstract boolean isPossibleToAdd();

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public boolean isBlackJack(){
        if(cards.calculateScore() == 21){
            return true;
        }
        return false;
    };
}
