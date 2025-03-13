package domain.participant;

import domain.card.Card;
import domain.card.CardHand;
import domain.game.Winning;

public class Dealer extends Gambler {

    private static final String DEALER_NAME = "딜러";
    private static final int TAKE_CARD_THRESHOLD = 16;

    public Dealer(CardHand cardHand) {
        super(DEALER_NAME, cardHand);
    }

    public Card getOpenCard() {
        return cardHand.getFirst();
    }

    @Override
    public boolean canTakeMoreCard() {
        return (calculateScore() <= TAKE_CARD_THRESHOLD);
    }

    public Winning judgeWinningForPlayer(Player player) {
        return Winning.determineForPlayer(player, this);
    }
}
