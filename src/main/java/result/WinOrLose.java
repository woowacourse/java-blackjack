package result;

import user.Dealer;
import user.Player;

public enum WinOrLose {
    WIN, LOSE, DRAW;

    public static WinOrLose checkWinOrLose(Player player, Dealer dealer){
        if (player.score() > dealer.score()) {
            return WIN;
        }
        if (player.score() < dealer.score()) {
            return LOSE;
        }
        return DRAW;
    }

}
