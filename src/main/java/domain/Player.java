package domain;

import util.ErrorMessage;

public class Player {
    private static final int MIN_NAME_SIZE = 2;
    private static final int MAX_NAME_SIZE = 7;

    private final String name;
    private final BlackjackHand blackjackHand;

    public Player (String name, BlackjackHand blackjackHand) {
        validateNameLength(name);
        this.name = name;
        this.blackjackHand = blackjackHand;
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_SIZE || name.length() > MAX_NAME_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_NAME.getMessage());
        }
    }

    public BlackjackHand getBlackjackHand() {
        return blackjackHand;
    }

    public void addHand(Card card){
        blackjackHand.add(card);
    }
}
