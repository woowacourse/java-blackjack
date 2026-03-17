package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.betting.DividendPolicy;
import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private static final int VISIBLE_CARD_COUNT = 2;

    private BettingAmount bettingAmount;

    public Player(String name, Hand hand) {
        super(name, hand);
        this.bettingAmount = BettingAmount.initial();
    }

    @Override
    public List<Card> getInitialCards() {
        return hand.getInitCards(VISIBLE_CARD_COUNT);
    }

    public void bet(int bettingAmount) {
        this.bettingAmount = this.bettingAmount.register(bettingAmount);
    }

    public long calculateProfit(DividendPolicy dividendPolicy, GameResult gameResult) {
        return dividendPolicy.calculate(bettingAmount.getBettingAmount(), gameResult);
    }
}
