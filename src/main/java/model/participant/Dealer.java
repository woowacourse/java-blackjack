package model.participant;

import static model.GameRule.DEALER_DRAW_THRESHOLD;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.card.Cards;

public class Dealer extends Participant {
    private boolean firstTurn = true;

    private Dealer(String name) {
        super(name);
    }

    public static Participant from(String input) {
        return new Dealer(input);
    }

    @Override
    public Cards open() {
        validateHasCards();

        if (firstTurn) {
            firstTurn = false;
            return Cards.from(List.of(hands.getFirst()));
        }

        return Cards.from(hands);
    }

    @Override
    public boolean beats(Participant participant) {
        if (participant.isBust()) {
            return true;
        }

        if (isBust()) {
            return false;
        }

        return calculateScore() >= participant.calculateScore();
    }

    public boolean needDraw() {
        return this.calculateScore() <= DEALER_DRAW_THRESHOLD;
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
