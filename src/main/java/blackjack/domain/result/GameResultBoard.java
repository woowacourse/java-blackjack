package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Outcome;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResultBoard {
    private final Map<PlayerName, GameResult> resultBoard = new HashMap<>();

    public GameResultBoard(Dealer dealer, List<Player> players) {
        Outcome dealerOutcome = dealer.calculateOutcome();
        for (Player player : players) {
            PlayerName playerName = player.getPlayerName();
            Outcome playerOutcome = player.calculateOutcome();
            GameResult gameResult = calculateGameResult(playerOutcome, dealerOutcome);
            resultBoard.put(playerName, gameResult);
        }
    }

    public GameResult findByPlayerName(PlayerName playerName) {
        return resultBoard.get(playerName);
    }

    public Map<PlayerName, GameResult> getResultBoard() {
        return resultBoard;
    }

    private static GameResult calculateGameResult(Outcome playerOutcome, Outcome dealerOutcome) {
        GameResult gameResult = playerOutcome.compete(dealerOutcome);
        if (playerOutcome.isBusted()) {
            gameResult = GameResult.LOSE;
        }
        if (playerOutcome.isBlackJack()) {
            gameResult = GameResult.WIN_BY_BLACK_JACK;
        }
        return gameResult;
    }
}
