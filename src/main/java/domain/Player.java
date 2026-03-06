package domain;

import util.ErrorMessage;

import java.util.List;

public class Player {
    private static final int MIN_NAME_SIZE = 2;
    private static final int MAX_NAME_SIZE = 7;

    private final String name;
    private final Hand hand;

    public Player (String name, Hand hand) {
        validateNameLength(name);
        this.name = name;
        this.hand = hand;
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_SIZE || name.length() > MAX_NAME_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_NAME.getMessage());
        }
    }

    public Hand getHand() {
        return hand;
    }

    public List<Card> getCards(){
        return getHand().getCards();
    }

    public void addHand(Card card){
        hand.add(card);
    }

    public String getName() {
        return name;
    }
}
