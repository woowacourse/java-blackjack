package blackjack.model.player;

import blackjack.model.card.Card;
import java.util.List;
import java.util.stream.Collectors;

public class Gamers {
    private static final String ERROR_DUPLICATE_NAME = "[ERROR] 중복되는 이름이 있습니다.";

    private final List<Player> values;
    private int gamerOrder = 0;

    private Gamers(List<Gamer> entries) {
        this.values = List.copyOf(entries);
    }

    public static Gamers from(List<String> names) {
        checkDuplicate(names);
        List<Gamer> entries = names.stream()
                .map(Gamer::new)
                .collect(Collectors.toList());
        return new Gamers(entries);
    }

    private static void checkDuplicate(List<String> names) {
        if (countDistinct(names) != names.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NAME);
        }
    }

    private static int countDistinct(List<String> names) {
        return (int) names.stream()
                .distinct()
                .count();
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
