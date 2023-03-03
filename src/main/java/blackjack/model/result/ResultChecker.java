package blackjack.model.result;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;

public class ResultChecker {

    public Result checkPlayerResult(Player player, Dealer dealer) {
        if(isPlayerWin(player, dealer)){
            return Result.WIN;
        }
        if(isPlayerLose(player, dealer)){
            return Result.LOSE;
        }
        return Result.TIE;
    }

    public boolean isPlayerWin(Player player, Dealer dealer) {
        if(player.isBlackjack() && !dealer.isBlackjack()){
            return true;
        }
        if(player.isStand() && dealer.isBust()){
            return true;
        }
        if(player.isStand() && dealer.isStand()){
            return player.getScore() > dealer.getScore();
        }
        return false;
    }

    public boolean isPlayerTie(Player player, Dealer dealer) {
        if(player.isBlackjack() && dealer.isBlackjack()){
            return true;
        }
        if(player.isStand() && dealer.isStand()){
            return player.getScore() == dealer.getScore();
        }
        return false;
    }

    public boolean isPlayerLose(Player player, Dealer dealer) {
        if(player.isBust()){
            return true;
        }
        if(player.isStand() && dealer.isBlackjack()){
            return true;
        }
        if(player.isStand() && dealer.isStand()){
            return player.getScore() < dealer.getScore();
        }
        return false;
    }
}
