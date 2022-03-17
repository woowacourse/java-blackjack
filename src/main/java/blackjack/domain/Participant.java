package blackjack.domain;

import static blackjack.domain.BlackjackGame.BLACKJACK_NUMBER;

import java.util.List;

public abstract class Participant {

    protected final Cards cards;

    protected final Name name;

    protected Participant(String name) {
        this(new Name(name));
    }

    protected Participant(Name name) {
        this.cards = new Cards();
        this.name = name;
    }

    public abstract boolean isDrawable();

    public abstract Score compete(Participant participant);

    public void drawCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    protected Score getScoreWithBlackjack(Participant player) {
        if (player.isBlackjack()) {
            return Score.DRAW;
        }
        return Score.WIN;
    }

    public boolean isBust() {
        return getTotalNumber() > BLACKJACK_NUMBER;
    }

    public int getTotalNumber() {
        return cards.getTotalNumber();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public String getName() {
        return name.getValue();
    }
}
