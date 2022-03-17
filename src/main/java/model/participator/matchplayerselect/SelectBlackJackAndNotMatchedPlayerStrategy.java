package model.participator.matchplayerselect;

import model.Status;
import model.participator.Player;

public class SelectBlackJackAndNotMatchedPlayerStrategy implements MatchPlayerSelectStrategy {
    @Override
    public boolean canSelect(Player player) {
        return !player.hasMatched() && player.getStatus().equals(Status.BLACKJACK);
    }
}
