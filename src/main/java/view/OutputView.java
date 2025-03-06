package view;

import domain.Card;
import domain.Dealer;
import domain.GameResult;
import domain.GameResultStatus;
import domain.Player;
import java.util.List;
import view.support.OutputFormatter;

public class OutputView {

    private final OutputFormatter outputFormatter;

    public OutputView(OutputFormatter outputFormatter) {
        this.outputFormatter = outputFormatter;
    }

    public void printInitialCards(Dealer dealer, List<Player> players) {
        List<String> playerNames = players.stream()
                .map(Player::getName)
                .toList();
        String parsedPlayerNames = outputFormatter.formatPlayerNames(playerNames);

        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", parsedPlayerNames);

        Card card = dealer.getFirstCard();
        System.out.printf("딜러카드: %s\n", outputFormatter.formatCard(card));

        players.forEach(
                player ->
                System.out.printf("%s카드: %s\n", player.getName(), outputFormatter.formatCards(player.getCards()))
        );
    }

    public void printCurrentCard(Player player) {
        System.out.printf("%s카드: %s\n", player.getName(), outputFormatter.formatCards(player.getCards()));
    }

    public void printDealerDraw() {
        System.out.printf("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public void printDealerNoDraw() {
        System.out.printf("\n딜러는 17이상이라 카드를 추가로 받지 않았습니다.\n");
    }

    public void printCardsResult(Dealer dealer, List<Player> players) {
        String parsedDealerCards = outputFormatter.formatCards(dealer.getCards());
        int dealerCardsSum = dealer.calculateCardsSum();
        System.out.printf("\n딜러카드: %s - 결과: %d\n", parsedDealerCards, dealerCardsSum);

        players.forEach(player -> {
            String parsedPlayerCards = outputFormatter.formatCards(player.getCards());
            int playerCardsSum = player.calculateCardsSum();
            System.out.printf("%s카드: %s - 결과: %d\n", player.getName(), parsedPlayerCards, playerCardsSum);

        });
    }

    public void printGameResults(GameResult gameResult) {
        int winCount = gameResult.calculateWinCount();
        int loseCount = gameResult.calculateLoseCount();
        System.out.println("\n## 최종 승패");
        System.out.printf("딜러: %d승 %d패\n", loseCount, winCount);
        gameResult.getAllPlayers()
                .forEach(player -> {
                    GameResultStatus gameResultstatus = gameResult.getGameResultstatus(player);
                    String resultMessage = outputFormatter.formatGameResult(gameResultstatus);
                    System.out.printf("%s: %s\n", player.getName(), resultMessage);
                });
    }
}
