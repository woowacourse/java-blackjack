package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.result.WinningResult;

public class Dealer extends Participant {

    public static final String DEALER_NAME = "딜러";
    public static final int DEALER_MIN_SCORE = 17;

    private Dealer() {
        this.name = DEALER_NAME;
    }

    public static Dealer startWithTwoCards(final Deck deck) {
        final Dealer dealer = new Dealer();
        dealer.drawCard(deck);
        dealer.drawCard(deck);
        return dealer;
    }

    public void continueDraw(final Deck deck) {
        while (isPossibleToDrawCard()) {
            drawCard(deck);
        }
    }

    private boolean isPossibleToDrawCard() {
        return calculateScore() < DEALER_MIN_SCORE;
    }

    public WinningResult judgeWinner(final Player player) {
        if (player.isBurst()) {
            return WinningResult.LOSS;
        }
        if (this.isBurst()) {
            return WinningResult.WIN;
        }

        return WinningResult.valueOf(player.isHigherThan(this));
    }

}
