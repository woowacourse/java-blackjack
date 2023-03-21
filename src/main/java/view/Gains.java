package view;

import domain.participant.Dealer;
import java.util.Iterator;
import java.util.List;

public class Gains implements Iterable<Gain> {

    private final List<Gain> playerGains;

    public Gains(List<Gain> gains) {
        this.playerGains = gains;
    }

    public Gain getDealerGain() {
        double dealerGain = -1 * playerGains.stream()
            .mapToDouble(Gain::getBetAmount)
            .sum();
        return new Gain(Dealer.NAME, dealerGain);
    }

    @Override
    public Iterator<Gain> iterator() {
        return playerGains.iterator();
    }
}
