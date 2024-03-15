package blackjack.domain.participants;

import blackjack.domain.card.Card;

public abstract class GameParticipant {

    public static final int MAX_SCORE = 21;

    protected final Hands hands;
    private final Name name;

    public GameParticipant(Name name, Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public abstract boolean canHit();

    protected abstract boolean isWin(GameParticipant participant);

    public Result takeOn(GameParticipant participant) {
        if (isTie(participant)) {
            return Result.TIE;
        }
        if (isWin(participant)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    protected boolean isTie(GameParticipant participant) {
        if (isBust() || participant.isBust()) {
            return false;
        }
        return isBlackjack() && participant.isBlackjack();
    }

    public void receiveHands(Hands newHands) {
        this.hands.receiveHands(newHands);
    }

    public void hit(Card card) {
        hands.addCard(card);
    }

    public int calculateScore() {
        return hands.calculateScore();
    }

    public boolean isBust() {
        return calculateScore() > MAX_SCORE;
    }

    public boolean isBlackjack() {
        return hands.isBlackjack();
    }

    public String getName() {
        return name.getName();
    }

    public Hands getHands() {
        return hands;
    }
}
