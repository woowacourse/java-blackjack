package blackjack.domain.participant.comparator;

import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class LoseComparator implements Comparator {
    private final Dealer dealer;
    private Comparator next;

    public LoseComparator(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public void setNext() {
        next = new NoCaseComparator();
    }

    @Override
    public WinTieLose compareWithPlayer(Player player) {
        if (player.getTotalScore() < dealer.getTotalScore()) {
            return WinTieLose.WIN;
        }
        setNext();
        return next.compareWithPlayer(player);
    }
}
