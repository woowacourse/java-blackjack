package blackjack.domain.participant;

import blackjack.domain.BettingResult;
import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private final BettingMoney bettingMoney;

    public Player(String name, List<Card> cards, int bettingMoney) {
        super(name, cards);
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    @Override
    public boolean canHit() {
        return getTotalScore() < BLACKJACK_SCORE;
    }

    public int calculateProfit(Participant dealer) {
        return BettingResult.of(this, dealer).getResult(bettingMoney);
    }
}
