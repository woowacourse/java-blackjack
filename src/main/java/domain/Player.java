package domain;

import exception.ErrorMessage;

public class Player {

    protected final String name;
    protected final Hand hand;

    public Player(String name) {
        validate(name);
        this.name = name;
        this.hand = new Hand();
    }

    private void validate(String name) {
        validateNotBlank(name);
        validatePlayerNameLength(name);
    }

    private void validatePlayerNameLength(String name) {
        if (!(2 <= name.length() && name.length() <= 5)) { // TODO: 상수?
            throw new IllegalArgumentException(ErrorMessage.PLAYER_NAME_LENGTH_OUT_OF_RANGE.getMessage());
        }
    }

    private void validateNotBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_NAME_BLANK.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public boolean isBust() {
        return hand.calculateScore() > 21; // TODO: 상수?
    }

    public int calculateScore() {
        return hand.calculateScore();
    }
}
