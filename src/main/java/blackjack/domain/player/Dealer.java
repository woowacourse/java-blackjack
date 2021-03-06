package blackjack.domain.player;


import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends AbstractPlayer {
    private static final String DEALER_NAME = "딜러";
    private static final int DRAW_BOUND = 16;
    private CardOpenStrategy cardOpenStrategy;

    public Dealer() {
        super(DEALER_NAME);
        cardOpenStrategy = new OneCardOpenStrategy();
    }

    @Override
    public boolean isCanDraw() {
        return getScore() <= DRAW_BOUND;
    }

    public void setCardOpen(CardOpenStrategy cardOpenStrategy) {
        this.cardOpenStrategy = cardOpenStrategy;
    }

    @Override
    public List<Card> getCards() {
        return cardOpenStrategy.getCards(super.getCards());
    }
}