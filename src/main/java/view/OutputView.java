package view;

import static result.GameStatus.*;

import card.Hand;
import participant.Dealer;
import result.PlayerResult;
import result.GameStatus;
import participant.Player;
import participant.Players;
import java.util.Map;
import view.support.OutputFormatter;

public class OutputView {

    private final OutputFormatter outputFormatter;

    public OutputView(OutputFormatter outputFormatter) {
        this.outputFormatter = outputFormatter;
    }

    public void printInitialStatus(Hand initialDealerHand, Map<String, Hand> PlayersNameAndInitialHand) {
        String parsedPlayerNames = outputFormatter.formatPlayerNames(PlayersNameAndInitialHand.keySet());

        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", parsedPlayerNames);
        System.out.printf("딜러카드: %s\n", outputFormatter.formatCards(initialDealerHand));
        PlayersNameAndInitialHand.forEach((key, value)
                -> System.out.printf("%s카드: %s\n", key, outputFormatter.formatCards(value)));
    }

    public void printCurrentCard(Player player) {
        System.out.printf("%s카드: %s\n", player.getName(), outputFormatter.formatCards(player.getCards()));
    }

    public void printDealerDraw() {
        System.out.print("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public void printDealerNoDraw() {
        System.out.print("\n딜러는 17이상이라 카드를 추가로 받지 않았습니다.\n");
    }

    public void printCardsResult(Dealer dealer, Players players) {
        String parsedDealerCards = outputFormatter.formatCards(dealer.getCards());
        int dealerCardsSum = dealer.getScore().score(); // ?
        System.out.printf("\n딜러카드: %s - 결과: %d\n", parsedDealerCards, dealerCardsSum);

        players.getPlayers().forEach(player -> {
            String parsedPlayerCards = outputFormatter.formatCards(player.getCards());
            int playerCardsSum = player.getScore().score(); // ?
            System.out.printf("%s카드: %s - 결과: %d\n", player.getName(), parsedPlayerCards, playerCardsSum);

        });
    }

//    public void printGameResults(PlayerResult playerResult) {
//        int winCount = playerResult.calculateStatusCount(WIN);
//        int loseCount = playerResult.calculateStatusCount(LOSE);
//        int drawCount = playerResult.calculateStatusCount(DRAW);
//        System.out.println("\n## 최종 승패");
//        System.out.printf("딜러: %d승 %d무 %d패\n", loseCount, drawCount, winCount);
//        playerResult.getAllPlayers()
//                .forEach(player -> {
//                    GameStatus gameStatus = playerResult.getGameResultStatus(player);
//                    String resultMessage = outputFormatter.formatGameResult(gameStatus);
//                    System.out.printf("%s: %s\n", player.getName(), resultMessage);
//                });
//    }

    public void printBustMessage() {
        System.out.println("카드의 합이 21을 초과하였습니다. 더이상 카드를 받을 수 없습니다.");
    }
}
