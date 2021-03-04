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
        return getValue() <= DRAW_BOUND;
    }

    public void setCardOpenStrategy(CardOpenStrategy cardOpenStrategy) {
        this.cardOpenStrategy = cardOpenStrategy;
    }

    public List<Card> getCardsWithStrategy() {
        return cardOpenStrategy.getCardsWithStrategy(super.getCards());
    }
}
