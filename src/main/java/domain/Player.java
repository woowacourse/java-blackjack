package domain;

import meesage.OutputMessage;

public class Player {

    private final Cards cards;
    private final String name;

    public Player(Cards cards, String name) {
        this.cards = cards;
        this.name = name;
    }

    public static Player of(Cards cards, String name) {
        return new Player(cards, name);
    }

    public boolean isBust() {
        return cards.isBust(cards.calculateScore());
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int getScoreOrZeroIfBust(){
        return cards.getScoreOrZeroIfBust();
    }

    public String getName() {
        return name;
    }

    public boolean isInitialHand() {
        return cards.size() == Policy.FIRST_DRAW_SIZE;
    }

    public Cards getCards() {
        return cards;
    }
}
