package model.matchplayerselect;

import model.Status;
import model.participator.Player;

public class SelectBlackJackAndCanMatchStrategy implements MatchPlayerSelectStrategy {
    @Override
    public boolean canSelect(Player player) {
        return player.canMatch() && player.getStatus().equals(Status.BLACKJACK);
    }
}
