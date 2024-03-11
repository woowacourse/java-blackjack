package view;

import domain.game.DealerResult;
import domain.game.Game;
import domain.game.PlayerResults;

public class ResultView {

    public static void printDealerHitMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResult(Game game) {
        System.out.println("\n## 최종 승패");
        PlayerResults playerResults = game.generatePlayerResults();
        DealerResult dealerResult = playerResults.generateDealerResult();
        System.out.println("딜러: " + dealerResult.getResultsDetail());

        playerResults.getPlayerResults()
                .forEach(((player, result) ->
                        System.out.println(player.getName().value() + ": " + result.getResult())));
    }
}
