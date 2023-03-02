package domain.user;

import domain.Card.Card;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private final List<Card> cards;

    public User(List<Card> firstTurnCards) {
        cards = new ArrayList<>(firstTurnCards);
    }

//    public void receiveCard(){
//
//    }

//    List<Integer> getCardNumbers() {
//
//    }
//
//    List<String> getCardPatterns() {
//
//    }
//
    public List<Card> getCards() {
        return cards;
    }
//
//    void checkBustByScore(int score) {
//
//    }
}
