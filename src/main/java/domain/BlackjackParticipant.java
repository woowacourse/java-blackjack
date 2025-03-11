package domain;

import java.util.List;

public abstract class BlackjackParticipant {

    private static final String INVALID_NAME = "닉네임은 공백일 수 없습니다";
    protected final ParticipantHand hand;
    private final String name;
    protected BlackjackParticipant(String name) {
        hand = new ParticipantHand();
        validateNickname(name);
        this.name = name;
    }

    private void validateNickname(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
    }

    public void addDraw(TrumpCard trumpCard) {
        hand.addCard(trumpCard);
    }

    public Score calculateCardSum() {
        return hand.calculateCardSum();
    }

    abstract boolean isDrawable();

    public boolean isBust() {
        return hand.isBust();
    }

    public List<TrumpCard> trumpCards() {
        return hand.getCards();
    }

    public String name() {
        return name;
    }

    public boolean isDealer() {
        return false;
    }
}
