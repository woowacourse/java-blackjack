package domain;

import static domain.BlackJackGame.DEALER_THRESHOLD;
import static domain.BlackjackGameResultStatus.DRAW;
import static domain.BlackjackGameResultStatus.LOSE;
import static domain.BlackjackGameResultStatus.WIN;

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

    public BlackjackGameResultStatus resultStatusOf(final Player player) {
        if (isBothBust(player) || this.cardSum() == player.cardSum()) {
            return DRAW;
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
