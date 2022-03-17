package model.participator.matchplayerselect;

import model.participator.Player;

public class SelectNotMatchedPlayerStrategy implements MatchPlayerSelectStrategy {
    @Override
    public boolean canSelect(Player player) {
        return !player.hasMatched();
    }
}
