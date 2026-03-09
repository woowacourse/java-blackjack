package model.participant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.card.Card;

public class Dealer extends Participant {
    private static final int DRAW_THRESHOLD = 16;

    private boolean firstTurn = true;

    private Dealer(String name) {
        super(name);
    }

    public static Participant from(String input) {
        return new Dealer(input);
    }

    @Override
    public List<String> open() {
        if (firstTurn) {
            firstTurn = false;
            return List.of(hands.getFirst().toString());
        }

        return hands.stream()
                .map(Card::toString)
                .toList();
    }

    public boolean needDraw() {
        return this.calculateScore() <= DRAW_THRESHOLD;
    }

    public Map<String, Integer> calculateStatistics(List<Player> players) {
        return players.stream()
                .map(this::judgeResult)
                .collect(Collectors.toMap(
                        key -> key,
                        key -> 1,
                        Integer::sum
                ));
    }

    private String judgeResult(Player player) {
        if (beats(player)) {
            return "승";
        }

        return "패";
    }
}
