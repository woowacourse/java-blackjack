package domain.gameresult;

import domain.player.Bet;
import domain.player.Player;

import java.util.Map;
import java.util.function.BiConsumer;

public class GameResult {
    private final Map<Player, Bet> resultOfPlayers;

    public GameResult(Map<Player, Bet> resultOfPlayers) {
        this.resultOfPlayers = resultOfPlayers;
    }

    public static GameResult from(Map<Player, Bet> result) {
        return new GameResult(result);
    }

    public void doLogicWithNameAndBetValue(BiConsumer<String, Integer> logic) {
        Map.copyOf(resultOfPlayers).forEach((player, bet) ->
                logic.accept(player.getName(), bet.getValue()));
    }
}
