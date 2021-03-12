package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.dealercardstate.DealerCardOpenState;
import blackjack.domain.player.dealercardstate.OneDealerCardOpenState;
import java.util.List;
import java.util.Map;

public class Dealer extends AbstractPlayer {
    private static final String DEALER_NAME = "딜러";
    private static final int DRAW_BOUND = 16;
    private DealerCardOpenState dealerCardOpenState;

    public Dealer() {
        this(Cards.getInstance().draw(), Cards.getInstance().draw());
    }

    public Dealer(Card fistCard, Card SecondCard) {
        super(DEALER_NAME, fistCard, SecondCard);
        dealerCardOpenState = new OneDealerCardOpenState();
        dealerStateCheck();
    }

    private void dealerStateCheck() {
        if (getScore() > DRAW_BOUND && !isBlackjack()) {
            stay();
        }
    }

    @Override
    public void drawRandomOneCard() {
        super.drawRandomOneCard();
        if (!isFinished()) {
            stay();
        }
    }

    @Override
    public List<Card> getCards() {
        List<Card> cards = dealerCardOpenState.cards(super.getCards());
        dealerCardOpenState = dealerCardOpenState.stateChange();
        return cards;
    }

    public int profit(Map<Name, Integer> usersResult) {
        int dealerAmount = 0;
        for (Name name : usersResult.keySet()) {
            dealerAmount -= usersResult.get(name);
        }
        return dealerAmount;
    }
}