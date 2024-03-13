package blackjack.model.participants;

import blackjack.model.blackjackgame.PlayerProfitCalculator;
import blackjack.model.blackjackgame.Profit;

public final class Player extends Participant {
    private final PlayerInfo playerInfo;

    public Player(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    @Override
    public boolean checkCanGetMoreCard() {
        return cards.canAddMore();
    }

    public Profit getProfit(final Participant participant) {
        return playerInfo.getProfit(getScoreStatus(participant));
    }

    private PlayerProfitCalculator getScoreStatus(final Participant participant) {
        return cards.getPlayerResultStatus(participant.cards);
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }
}
