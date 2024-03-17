package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Gamers {
    private final List<Gamer> gamers;
    private final BetAmount betAmount = new BetAmount();

    public Gamers(final List<Gamer> gamers) {
        this.gamers = gamers;
    }

    public List<Gamer> listOf() {
        return Collections.unmodifiableList(gamers);
    }

    public List<String> names() {
        return gamers.stream()
                .map(Gamer::getName)
                .toList();
    }

    public List<Hand> hands() {
        return gamers.stream()
                .map(Gamer::getHand)
                .toList();
    }

    public List<Integer> scores() {
        return gamers.stream()
                .map(Gamer::calculateResultScore)
                .toList();
    }

    public void saveBettingAmounts(final Map<Gamer, Integer> bettingAmounts) {
        betAmount.save(Collections.unmodifiableMap(bettingAmounts));
    }

    public List<Integer> betAmounts() {
        List<Integer> betAmounts = new ArrayList<>();
        for (Gamer gamer : gamers) {
            betAmounts.add(betAmount.getAmount(gamer));
        }
        return Collections.unmodifiableList(betAmounts);
    }
}
