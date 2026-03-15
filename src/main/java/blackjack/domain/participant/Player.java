package blackjack.domain.participant;

import blackjack.domain.betting.BettingResult;
import blackjack.domain.betting.DividendPolicy;
import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private BettingAmount bettingAmount;

    public Player(String name, Hand hand) {
        super(name, hand);
        this.bettingAmount = BettingAmount.initial();
    }

    @Override
    public List<Card> getInitialCards() {
        return hand.getCards();
    }

    public void bet(int bettingAmount) {
        this.bettingAmount = this.bettingAmount.register(bettingAmount);
    }

    public long calculateProfit(DividendPolicy dividendPolicy, BettingResult bettingResult) {
        return dividendPolicy.calculate(bettingAmount.getBettingAmount(), bettingResult);
    }
}
