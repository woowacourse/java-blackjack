package model;

import exception.IllegalDrawException;

import java.util.ArrayList;
import java.util.List;

import static controller.BlackJackGame.*;

public abstract class User implements Comparable<User> {
    protected final String name;
    protected final CardHand cardHand;

    public User(String name, Deck deck) {
        this.name = name;
        this.cardHand = new CardHand();
        firstDraw(deck);
    }

    public User(String name, List<Card> cards) {
        this.name = name;
        this.cardHand = new CardHand();
        cards.forEach(cardHand::addCard);
    }

    public void firstDraw(Deck deck) {
        if (!cardHand.isEmpty()) {
            throw new IllegalDrawException("2장 Draw는 패가 없는 경우에만 가능합니다.");
        }
        for (int i = 0; i < INITIAL_DRAW_COUNT; i++) {
            cardHand.addCard(deck.draw());
        }
    }

    public void additionalDraw(Deck deck) {
        for (int i = 0; i < ADDITIONAL_DRAW_COUNT; i++) {
            cardHand.addCard(deck.draw());
        }
    }

    public String toStringCardHand() {
        List<String> cardNames = new ArrayList<>();

        for (Card card : cardHand) {
            cardNames.add(card.toString());
        }
        return String.join(COMMA, cardNames);
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    public boolean isBlackJack() {
        return cardHand.isBlackJack();
    }

    public abstract boolean isHitBound();

    public boolean isLowerThanBlackJack() {
        return cardHand.isLowerThanBlackJack();
    }

    public int getScore() {
        return cardHand.getScore();
    }

    public String getName() {
        return name;
    }

    public boolean isLowerThan(User other) {
        return this.getScore() < other.getScore();
    }

    public boolean isSameWith(User other) {
        return this.getScore() == other.getScore();
    }

    @Override
    public int compareTo(User o) {
        Integer score = getScore();
        return score.compareTo(o.getScore());
    }
}
