package domain.participant;

import domain.GameResult;
import domain.card.Hand;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Dealer extends Participant {
    public static final int DRAW_BOUNDARY = 16;

    private final Map<GameResult, Integer> result;

    private Dealer(Hand hand) {
        super(hand);
        this.result = new EnumMap<>(GameResult.class);
    }

    public static Dealer init() {
        return new Dealer(Hand.empty());
    }

    public static Dealer of(Hand hand) {
        return new Dealer(hand);
    }

    public boolean hasToDraw() {
        return this.getCardScore() <= DRAW_BOUNDARY;
    }

    public Map<Player, GameResult> getGameResult(Players players) {
        Map<Player, GameResult> gameResult = new HashMap<>();
        for (Player player : players.getPlayers()) {
            GameResult playerResult = rule.getResult(player, this);
            gameResult.put(player, playerResult);
            GameResult dealerResult = playerResult.getReverse();
            result.put(dealerResult, result.getOrDefault(dealerResult, 0) + 1);
        }
        return gameResult;
    }

    public Map<GameResult, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
