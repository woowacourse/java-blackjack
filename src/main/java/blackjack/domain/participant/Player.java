package blackjack.domain.participant;

import blackjack.domain.Betting;
import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private final Betting betting;

    private static final int HIT_STANDARD = 21;

    public Player(Name name, List<Card> cards, Betting betting) {
        super(name, cards);
        this.betting = betting;
    }

    @Override
    public List<Card> showInitialCards() {
        return List.copyOf(getCards());
    }

    public boolean isHittable() {
        return getScore().isLessThan(HIT_STANDARD);
    }

    public int calculateProfit(Outcome outcome) {
        return (int) (betting.getBetMoney() * outcome.getProfitRate());
    }
}
