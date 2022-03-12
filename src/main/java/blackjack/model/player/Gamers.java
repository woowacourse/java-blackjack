package blackjack.model.player;

import blackjack.model.card.Card;
import java.util.List;
import java.util.stream.Collectors;

public class Gamers {
    private final List<Player> values;
    private int gamerOrder = 0;

    private Gamers(List<Gamer> gamers) {
        this.values = List.copyOf(gamers);
    }

    public static Gamers from(List<String> names) {
        List<Gamer> gamers = names.stream()
                .map(Gamer::new)
                .collect(Collectors.toList());
        return new Gamers(gamers);
    }

    public List<Player> getValues() {
        return values;
    }

    public boolean hasNextGamer() {
        return values.size() > gamerOrder;
    }

    public Player getCurrentValue() {
        return values.get(gamerOrder);
    }

    public void giveCurrentGamer(Card card) {
        values.get(gamerOrder).receive(card);
    }

    public boolean isCurrentGamerCanReceive() {
        return values.get(gamerOrder).canReceive();
    }

    public void nextGamer() {
        gamerOrder += 1;
    }
}
