package blackjack.domain.game.participantsState;

import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.HashMap;

public class PlayerState {
    private final Player player;
    private final Dealer dealer;
    private ResultCalculator resultCalculator;
    public PlayerState(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
        setBothBustCase();
        setOnlyPlayerBustCase();
        setOnlyDealerBustCase();
        setBothNotBustCase();
    }
    public void calculateResult(HashMap<Player, WinTieLose> playersResult){
        this.resultCalculator.calculateResult(playersResult,player,dealer);
    }
    private void setBothNotBustCase() {
        if(dealer.isNotBust()&& player.isNotBust()){
            this.resultCalculator = new BothNotBustState();
        }
    }

    private void setOnlyDealerBustCase() {
        if(dealer.isBust()&& player.isNotBust()){
            this.resultCalculator = new OnlyDealerBustState();
        }
    }

    private void setOnlyPlayerBustCase() {
        if(dealer.isNotBust()&&player.isBust()){
            this.resultCalculator = new OnlyPlayerBustState();
        }
    }

    private void setBothBustCase(){
        if(dealer.isBust()&& player.isBust()){
            this.resultCalculator = new BothBustState();
        }
    }
}
