package blackjack.domain;

import java.util.List;

public class Player {

    public static final int BLACKJACK_NUMBER = 21;

    private final Cards cards = new Cards();
    private final Name name;

    public Player(String name) {
        this(new Name(name));
    }

    public Player(Name name) {
        this.name = name;
    }

    public void drawCard(Drawable drawable) {
        cards.add(drawable.draw());
    }

    public boolean isBust() {
        return getTotalNumber() > BLACKJACK_NUMBER;
    }

    public int getTotalNumber() {
        return cards.getTotalNumber();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public Score compete(Player otherPlayer) {
        if (!this.isBust() && !otherPlayer.isBust()) {
            return Score.compete(this.getTotalNumber(), otherPlayer.getTotalNumber());
        }
        if (this.isBust() && otherPlayer.isBust()) {
            return Score.DRAW;
        }
        if (otherPlayer.isBust()) {
            return Score.WIN;
        }
        return Score.LOSE;
    }
}
