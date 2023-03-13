package blackjack.domain.participant.comparator;

import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class InitialComparator implements Comparator{
    private final Dealer dealer;
    private Comparator next;
    public InitialComparator(Dealer dealer){
        this.dealer = dealer;
    }
    @Override
    public void setNext() {
        next = new BlackjackComparator(dealer);
    }

    @Override
    public WinTieLose compareWithPlayer(Player player) {
        setNext();
        return next.compareWithPlayer(player);
    }
}
