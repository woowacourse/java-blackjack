package blackjack.model.participants;

import blackjack.model.blackjackgame.GameOutcomeStatus;
import blackjack.model.blackjackgame.Profit;

public final class Player extends Participant {
    private final Name name;
    private final Betting betting;

    public Player(Name name, Betting betting) {
        this.name = name;
        this.betting = betting;
    }

    @Override
    public boolean checkCanGetMoreCard() {
        return cards.canAddMore();
    }

    public Profit calculateGameOutcomeProfit(final Participant participant) {
        GameOutcomeStatus gameOutcomeStatus = cards.compareCardsScore(participant.cards);
        return gameOutcomeStatus.calculate(betting);
    }

    public Name getName() {
        return name;
    }
}
