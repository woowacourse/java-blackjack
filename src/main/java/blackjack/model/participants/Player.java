package blackjack.model.participants;

import blackjack.model.blackjackgame.PlayerProfitCalculator;
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

    public Profit getProfit(final Participant participant) {
        return betting.getProfit(getScoreStatus(participant));
    }

    private PlayerProfitCalculator getScoreStatus(final Participant participant) {
        return cards.getPlayerResultStatus(participant.cards);
    }

    public Name getName() {
        return name;
    }
}
