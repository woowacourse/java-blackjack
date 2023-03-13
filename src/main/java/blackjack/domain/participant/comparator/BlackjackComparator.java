package blackjack.domain.participant.comparator;

import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class BlackjackComparator implements Comparator {
    private final Dealer dealer;
    private Comparator next;

    public BlackjackComparator(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public void setNext() {
        next = new WinComparator(dealer);
    }

    @Override
    public WinTieLose compareWithPlayer(Player player) {
        if(player.isBlackjack()){
            return checkBlackjack();
        }
        setNext();
        return next.compareWithPlayer(player);
    }
    private WinTieLose checkBlackjack(){
        if(dealer.isBlackjack()){
            return WinTieLose.TIE;
        }
        return WinTieLose.BLACKJACK;
    }
}
