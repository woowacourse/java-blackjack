package domain;

import static domain.BlackjackGame.DEALER_THRESHOLD;
import static domain.GameResultStatus.PUSH;
import static domain.GameResultStatus.LOSE;
import static domain.GameResultStatus.WIN;

public class Dealer extends Participant {

    private static final Name DEFAULT_DEALER_NAME = new Name("딜러");

    private final Cards deck;

    public Dealer(final Cards cards) {
        super(DEFAULT_DEALER_NAME);
        deck = cards;
    }

    public Card peek() {
        return hand.peek();
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void shuffle() {
        deck.shuffle();
    }

    public void deal(final Participant participant) {
        participant.receive(drawCard());
    }

    public boolean doesGetCard() {
        return cardSum() <= DEALER_THRESHOLD;
    }

    public GameResultStatus resultStatusOf(final Player player) {
        if (isBothBust(player) || this.cardSum() == player.cardSum()) {
            return PUSH;
        }
        if (isWin(player)) {
            return WIN;
        }
        return LOSE;
    }

    private boolean isWin(final Player player) {
        return !player.isBust()
                && (this.isBust() || (this.cardSum() < player.cardSum()));
    }

    private boolean isBothBust(final Player player) {
        return this.isBust() && player.isBust();
    }
}
