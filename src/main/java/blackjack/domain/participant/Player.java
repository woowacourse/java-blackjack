package blackjack.domain.participant;

import blackjack.domain.BettingMoney;
import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.state.HandState;
import java.util.List;

public class Player extends Participant {
    private final ParticipantName name;
    private final BettingMoney bettingMoney;

    public Player(final HandState handState, final ParticipantName name, final BettingMoney bettingMoney) {
        super(handState);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public int calculateProfit(HandState otherHandState) {
        GameResult gameResult = handState.determineResult(otherHandState);
        return gameResult.calculatePayout(bettingMoney);
    }

    @Override
    public boolean canHit() {
        return !handState.isFinished();
    }

    @Override
    public List<Card> showStartCards() {
        List<Card> cards = handState.getCards();
        return cards.subList(0,2);
    }

    @Override
    public String getName() {
        return name.getValue();
    }
}
