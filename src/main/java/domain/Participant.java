package domain;

import java.util.List;

public abstract class Participant {

    private static final int INITIAL_CARD_COUNT = 2;

    protected final Name name;
    protected final Hand hand;

    protected Participant(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void pick(Card card) {
        hand.addNewCard(card);
    }

    public boolean isBust() {
        return getTotalScore().isBust();
    }

    public boolean isNotBust() {
        return !this.isBust();
    }

    public abstract Score getTotalScore();

    public int getTotalScoreToValue() {
        return getTotalScore().getValue();
    }

    public abstract boolean isMoreCardAble();

    public boolean isBlackjack() {
        return getTotalScore().isMaxScore() && hand.getSize() == INITIAL_CARD_COUNT;
    }

    public boolean isNotBlackjack() {
        return !this.isBlackjack();
    }

    public List<Card> getCardList() {
        return List.copyOf(hand.getCards());
    }

    public Name getName() {
        return name;
    }

    public String getNameToValue() {
        return name.getValue();
    }

}
