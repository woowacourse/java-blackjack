package domain.user;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class User {
    private final List<Card> cards;

    public User(List<Card> firstTurnCards) {
        cards = new ArrayList<>(firstTurnCards);
    }

//    public void receiveCard(){
//
//    }

    public List<Integer> getCardNumbers() {
        return cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toList());
    }

    public List<String> getCardPatterns() {
        return cards.stream()
                .map(Card::getPattern)
                .collect(Collectors.toList());
    }

    public List<Card> getCards() {
        return cards;
    }
//
//    void checkBustByScore(int score) {
//
//    }
}
