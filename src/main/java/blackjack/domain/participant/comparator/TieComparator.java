package blackjack.domain.participant.comparator;

import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class TieComparator implements Comparator {
    private final Dealer dealer;
    private Comparator next;

    public TieComparator(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public void setNext() {
        next = new LoseComparator(dealer);
    }

    @Override
    public WinTieLose compareWithPlayer(Player player) {
        if (dealer.isBlackjack()) {
            return WinTieLose.LOSE;
        }
        if (player.getTotalScore() == dealer.getTotalScore()) {
            return WinTieLose.TIE;
        }
        setNext();
        return next.compareWithPlayer(player);
    }
}
