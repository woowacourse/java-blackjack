package model.participant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.card.Card;

public class Dealer extends Participant {
    public static final int DRAW_THRESHOLD = 16;

    private boolean firstTurn = true;

    private Dealer(String name) {
        super(name);
    }

    public static Dealer from(String input) {
        return new Dealer(input);
    }

    @Override
    public List<Card> open() {
        if (firstTurn) {
            firstTurn = false;
            return List.of(hands.getFirst());
        }

        return hands.asList();
    }

    @Override
    public boolean beats(Participant participant) {
        if (!(participant instanceof Player player)) {
            throw new IllegalArgumentException("딜러는 플레이어와만 승패를 판정할 수 있습니다.");
        }

        return !player.beats(this);
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

    @Override
    public String toString() {
        return "Dealer{" +
                "name='" + getName() + '\'' +
                "firstTurn=" + firstTurn +
                ", hands=" + hands +
                '}';
    }
}
