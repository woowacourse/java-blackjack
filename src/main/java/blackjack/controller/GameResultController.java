package blackjack.controller;

import blackjack.domain.Gamer;
import blackjack.domain.Players;
import blackjack.view.OutputView;
import java.util.Map;

public class GameResultController {

    private GameResultController() {

    }

    public static void getPlayersCardsAndResult(Players players) {
        for (Gamer gamer : players.getAllParticipant()) {
            OutputView.printGameResultPoint(gamer);
        }
    }

    public static void getResult(Players players) {
        Map<String, Integer> result = players.calculateResult();

        OutputView.printDealerResult(result);
        OutputView.printPlayerResult(players);
    }
}