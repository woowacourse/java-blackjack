package model;

import java.util.ArrayList;
import java.util.List;

import static controller.BlackJackGame.*;

public class User implements Comparable<User> {
    protected final String name;
    protected final CardHand cardHand;

    public User(String name, Deck deck, int initialDrawCount) {
        this.name = name;
        this.cardHand = deck.draw(initialDrawCount);
    }

    public User(String name, List<Card> cards){
        this.name = name;
        this.cardHand = new CardHand();
        cards.forEach(cardHand::addCard);
    }

    public String toStringCardHand() {
        List<String> cardNames = new ArrayList<>();

        for (Card card : cardHand) {
            cardNames.add(card.toString());
        }
        return String.join(COMMA, cardNames);
    }

    public void drawCard(Deck deck, int drawCount) {
        for (Card drawCard : deck.draw(drawCount)) {
            this.cardHand.addCard(drawCard);
        }
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    public boolean isBlackJack(){
        return cardHand.isBlackJack();
    }

    public boolean isMoreThanBlackJack(){
        return cardHand.isMoreThanBlackJack();
    }

    public int getScore() {
        return cardHand.getScore();
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(User o) {
        Integer score = getScore();
        return score.compareTo(o.getScore());
    }
}
