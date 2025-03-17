package blackjack.domain.participant;

import blackjack.domain.BettingMoney;
import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.state.State;
import java.util.List;

public class Player extends Participant {
    private final ParticipantName name;
    private final BettingMoney bettingMoney;

    public Player(final State state, final ParticipantName name, final BettingMoney bettingMoney) {
        super(state);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public int calculateProfit(State otherState) {
        GameResult gameResult = state.determineResult(otherState);
        return gameResult.calculatePayout(bettingMoney);
    }

    @Override
    public boolean canHit() {
        return !state.isFinished();
    }

    @Override
    public List<Card> showStartCards() {
        List<Card> cards = state.getCards();
        return cards.subList(0,2);
    }

    @Override
    public String getName() {
        return name.getValue();
    }
}
