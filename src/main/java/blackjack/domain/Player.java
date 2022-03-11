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

    public Score compete(Player player) {
        if (player.isBust()) {
            return getScoreWithBust();
        }
        if (this.isBust()) {
            return Score.LOSE;
        }

        return Score.compare(this.getTotalNumber(), player.getTotalNumber());
    }

    public boolean isBust() {
        return getTotalNumber() > BLACKJACK_NUMBER;
    }

    private Score getScoreWithBust() {
        if (this.isBust()) {
            return Score.DRAW;
        }
        return Score.WIN;
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
}
