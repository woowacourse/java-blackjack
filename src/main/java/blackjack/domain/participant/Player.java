package blackjack.domain.participant;

import blackjack.domain.Betting;
import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Player extends Participant {

    private Betting betting;

    private static final int HIT_STANDARD = 21;

    public Player(Name name, List<Card> cards) {
        super(name, new Cards(cards));
    }

    public Player(Name name, List<Card> cards, Betting betting) {
        super(name, new Cards(cards));
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
