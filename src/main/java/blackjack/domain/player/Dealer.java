package blackjack.domain.player;


import blackjack.domain.card.Card;
import blackjack.domain.player.strategy.CardOpenStrategy;
import blackjack.domain.player.strategy.OneCardOpenStrategy;
import java.util.List;

public class Dealer extends AbstractPlayer {
    private static final String DEALER_NAME = "딜러";
    private static final int DRAW_BOUND = 16;

    private CardOpenStrategy cardOpenStrategy;

    public Dealer() {
        super(DEALER_NAME);
        this.cardOpenStrategy = new OneCardOpenStrategy();
    }

    public void setCardOpenStrategy(CardOpenStrategy cardOpenStrategy) {
        this.cardOpenStrategy = cardOpenStrategy;
    }

    @Override
    public boolean isCanDraw() {
        return this.getScore() <= DRAW_BOUND;
    }

    @Override
    public List<Card> getCards() {
        return cardOpenStrategy.getCardsWithStrategy(super.getCards());
    }
}
