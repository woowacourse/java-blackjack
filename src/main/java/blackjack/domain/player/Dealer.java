package blackjack.domain.player;

import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.player.cardopen.CardOpenStrategy;
import blackjack.domain.player.cardopen.OneCardOpenStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<ResultType, Integer> getResult(Map<Name, ResultType> usersResult) {
        Map<ResultType, Integer> dealerResult = new HashMap<>();
        for (Name name : usersResult.keySet()) {
            dealerResult.put(usersResult.get(name), dealerResult.getOrDefault(usersResult.get(name), 0) + 1);
        }
        return dealerResult;
    }
}