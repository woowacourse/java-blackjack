package domain.participant;

import domain.GameResult;
import domain.card.Cards;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {
    public static final int DRAW_BOUNDARY = 16;

    private final Map<GameResult, Integer> result;

    private Dealer(Cards cards) {
        super(cards);
        this.result = new EnumMap<>(GameResult.class);
    }

    public static Dealer init() {
        return new Dealer(Cards.empty());
    }

    public static Dealer of(Cards cards) {
        return new Dealer(cards);
    }

    public boolean hasToDraw() {
        return this.getCardScore() <= DRAW_BOUNDARY;
    }

    public Map<Player, GameResult> getGameResult(List<Player> players) {
        Map<Player, GameResult> gameResult = new HashMap<>();
        for (Player player : players) {
            GameResult playerResult = rule.getResult(player.getCards(), getCards());
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
