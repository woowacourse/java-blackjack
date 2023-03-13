package blackjack.domain.participant.comparator;

import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class WinComparator implements Comparator {
    private final Dealer dealer;
    private Comparator next;

    public WinComparator(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public void setNext() {
        next = new TieComparator(dealer);
    }

    @Override
    public WinTieLose compareWithPlayer(Player player) {
        if (player.getTotalScore() > dealer.getTotalScore()) {
            return WinTieLose.WIN;
        }
        setNext();
        return next.compareWithPlayer(player);
    }
}
