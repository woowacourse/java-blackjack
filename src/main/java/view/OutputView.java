package view;

import static domain.result.GameResultStatus.*;

import domain.card.Card;
import domain.participant.Dealer;
import domain.result.GameResult;
import domain.result.GameResultStatus;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import view.support.OutputFormatter;

public class OutputView {

    private final OutputFormatter outputFormatter;

    public OutputView(OutputFormatter outputFormatter) {
        this.outputFormatter = outputFormatter;
    }

    public void printInitialCards(Card dealerCard, Players players) {
        List<String> playerNames = players.getAllPlayersName();
        String parsedPlayerNames = outputFormatter.formatPlayerNames(playerNames);

        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", parsedPlayerNames);
        System.out.printf("딜러카드: %s\n", outputFormatter.formatCard(dealerCard));
        players.getPlayers().forEach(
                player ->
                System.out.printf("%s카드: %s\n", player.getName(), outputFormatter.formatCards(player.getCards()))
        );
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
        int dealerCardsSum = dealer.calculateCardsSum();
        System.out.printf("\n딜러카드: %s - 결과: %d\n", parsedDealerCards, dealerCardsSum);

        players.getPlayers().forEach(player -> {
            String parsedPlayerCards = outputFormatter.formatCards(player.getCards());
            int playerCardsSum = player.calculateCardsSum();
            System.out.printf("%s카드: %s - 결과: %d\n", player.getName(), parsedPlayerCards, playerCardsSum);

        });
    }

    public void printGameResults(GameResult gameResult) {
        int winCount = gameResult.calculateStatusCount(WIN);
        int loseCount = gameResult.calculateStatusCount(LOSE);
        int drawCount = gameResult.calculateStatusCount(DRAW);
        System.out.println("\n## 최종 승패");
        System.out.printf("딜러: %d승 %d무 %d패\n", loseCount, drawCount, winCount);
        gameResult.getAllPlayers()
                .forEach(player -> {
                    GameResultStatus gameResultStatus = gameResult.getGameResultstatus(player);
                    String resultMessage = outputFormatter.formatGameResult(gameResultStatus);
                    System.out.printf("%s: %s\n", player.getName(), resultMessage);
                });
    }

    public void printBustMessage() {
        System.out.println("카드의 합이 21을 초과하였습니다. 더이상 카드를 받을 수 없습니다.");
    }
}
