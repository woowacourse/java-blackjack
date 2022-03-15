package model.matchplayerselect;

import model.participator.Player;

public class SelectCanMatchStrategy implements MatchPlayerSelectStrategy {
    @Override
    public boolean canSelect(Player player) {
        return player.canMatch();
    }
}
