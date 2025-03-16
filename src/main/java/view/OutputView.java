package view;

import controller.dto.CardsResultResponse;
import controller.dto.InitialDealResponse;
import controller.dto.InitialDealResponse.DealerDealResult;
import controller.dto.InitialDealResponse.PlayerDealResult;
import controller.dto.PlayerHitResponse;
import controller.dto.ProfitResultResponse;
import domain.Dealer;
import domain.Hand;
import domain.Player;
import domain.Players;
import java.util.List;
import view.support.OutputFormatter;

public class OutputView {

    private final OutputFormatter outputFormatter;

    public OutputView(OutputFormatter outputFormatter) {
        this.outputFormatter = outputFormatter;
    }

    public void printInitialCards(Dealer dealer, Players players) {
        List<String> playerNames = players.getAllPlayersName();
        String parsedPlayerNames = outputFormatter.formatPlayerNames(playerNames);

        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", parsedPlayerNames);
        System.out.printf("딜러카드: %s\n", outputFormatter.formatCard(dealer.getDealCard()));
        players.getPlayers().forEach(
                player ->
                        System.out.printf("%s카드: %s\n", player.getName(),
                                outputFormatter.formatCards(player.getHand()))
        );
    }

    public void printInitialDealResult(InitialDealResponse initialDealResponses) {
        DealerDealResult dealerDealResult = initialDealResponses.dealerDealResult();
        List<PlayerDealResult> playerInitialDealResult = initialDealResponses.playerInitialDealResults();

        List<String> playerNames = playerInitialDealResult.stream()
                .map(PlayerDealResult::name)
                .toList();
        String parsedPlayerNames = outputFormatter.formatPlayerNames(playerNames);
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", dealerDealResult.name(), parsedPlayerNames);
        System.out.printf("딜러카드: %s\n", outputFormatter.formatCard(dealerDealResult.card()));
        playerInitialDealResult.forEach(
                result -> System.out.printf("%s카드: %s\n", result.name(), outputFormatter.formatCards(result.cards()))
        );
    }

    public void printPlayerHitResult(PlayerHitResponse playerHitResponse) {
        String playerName = playerHitResponse.playerName();
        String playerCards = outputFormatter.formatCards(playerHitResponse.cards());
        System.out.printf("%s카드: %s\n", playerName, playerCards);
    }

    public void printCurrentCard(Player player) {
        System.out.printf("%s카드: %s\n", player.getName(), outputFormatter.formatCards(player.getHand()));
    }

    public void printDealerDraw() {
        System.out.print("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public void printDealerNoDraw() {
        System.out.print("\n딜러는 17이상이라 카드를 추가로 받지 않았습니다.\n");
    }

    public void printCardsResult(Dealer dealer, Players players) {
        Hand dealerHand = dealer.getHand();
        String parsedDealerCards = outputFormatter.formatCards(dealerHand);
        int dealerCardsSum = dealerHand.calculateSum();
        System.out.printf("\n딜러카드: %s - 결과: %d\n", parsedDealerCards, dealerCardsSum);

        players.getPlayers().forEach(player -> {
            Hand playerHand = player.getHand();
            String parsedPlayerCards = outputFormatter.formatCards(playerHand);
            int playerCardsSum = playerHand.calculateSum();
            System.out.printf("%s카드: %s - 결과: %d\n", player.getName(), parsedPlayerCards, playerCardsSum);
        });
    }

    public void printCardsResults(List<CardsResultResponse> cardsResultResponses) {
        System.out.print(System.lineSeparator());
        cardsResultResponses.forEach(response -> {
            String name = response.name();
            String parsedCards = outputFormatter.formatCards(response.cards());
            int handValue = response.handValue();
            System.out.printf("%s카드: %s - 결과: %d\n", name, parsedCards, handValue);
        });
    }

    public void printProfitResults(List<ProfitResultResponse> profitResultResponses) {
        System.out.println("\n## 최종 수익");
        profitResultResponses.forEach(response -> {
            System.out.printf("%s: %d\n", response.name(), response.profit());
        });
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
