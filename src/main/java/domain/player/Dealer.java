package domain.player;

import domain.card.BlackJackScore;
import domain.card.Card;
import domain.card.CardArea;

public class Dealer extends Participant {

    private static final Name DEALER_NAME = Name.of("딜러");
    private static final BlackJackScore DEALER_DONT_HIT_SCORE = BlackJackScore.of(17);

    public Dealer(final CardArea cardArea) {
        super(DEALER_NAME, cardArea);
    }

    @Override
    public boolean canHit() {
        return DEALER_DONT_HIT_SCORE.isLargerThan(score());
    }

    public Card firstCard() {
        return cardArea.firstCard();
    }

    public Revenue compete(final Gambler gambler) {
        if (isGamblerWin(gambler)) {
            return revenueForWinner(gambler);
        }
        if (gambler.isBust()) {
            return Revenue.lose(gambler.battingMoney());
        }
        if (isLargerScoreThan(gambler)) {
            return Revenue.lose(gambler.battingMoney());
        }
        return Revenue.draw(gambler.battingMoney());
    }

    private boolean isGamblerWin(final Gambler gambler) {
        if (gambler.isBust()) {
            return false;
        }
        return isBust() || gambler.isLargerScoreThan(this);
    }

    private Revenue revenueForWinner(final Gambler gambler) {
        if (gambler.isBlackJack()) {
            return Revenue.blackJackWin(gambler.battingMoney());
        }
        return Revenue.defaultWin(gambler.battingMoney());
    }
}
