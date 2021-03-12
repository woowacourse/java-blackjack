package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.cardopen.CardOpenStrategy;
import blackjack.domain.player.cardopen.OneCardOpenStrategy;
import java.util.List;
import java.util.Map;

public class Dealer extends AbstractPlayer {
    private static final String DEALER_NAME = "딜러";
    private static final int DRAW_BOUND = 16;
    private CardOpenStrategy cardOpenStrategy;

    public Dealer() {
        this(Cards.getInstance().draw(), Cards.getInstance().draw());
    }

    public Dealer(Card fistCard, Card SecondCard) {
        super(DEALER_NAME, fistCard, SecondCard);
        cardOpenStrategy = new OneCardOpenStrategy();
        dealerStateCheck();
    }

    private void dealerStateCheck() {
        if (getScore() > DRAW_BOUND && !isBlackjack()) {
            stay();
        }
    }

    public void setCardOpen(CardOpenStrategy cardOpenStrategy) {
        this.cardOpenStrategy = cardOpenStrategy;
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
        return cardOpenStrategy.getCards(super.getCards());
    }

    public int profit(Map<Name, Integer> usersResult) {
        int dealerAmount = 0;
        for (Name name : usersResult.keySet()) {
            dealerAmount -= usersResult.get(name);
        }
        return dealerAmount;
    }
}