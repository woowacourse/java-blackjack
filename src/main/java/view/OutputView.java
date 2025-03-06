package view;

import domain.Card;
import domain.GameResult;
import domain.GameResultStatus;
import domain.Participant;
import java.util.List;
import java.util.Map;
import view.support.OutputFormatter;

public class OutputView {

    private final OutputFormatter outputFormatter;

    public OutputView(OutputFormatter outputFormatter) {
        this.outputFormatter = outputFormatter;
    }

    public void printInitialCards(Participant dealer, List<Participant> players) {
        List<String> playerNames = players.stream()
                .map(Participant::name)
                .toList();
        String parsedPlayerNames = outputFormatter.formatPlayerNames(playerNames);

        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", dealer.name(), parsedPlayerNames);

        Card card = dealer.getFirstCard();
        System.out.printf("%s카드: %s\n", dealer.name(), outputFormatter.formatCard(card));

        players.forEach(
                player ->
                System.out.printf("%s카드: %s\n", player.name(), outputFormatter.formatCards(player.cards()))
        );
    }

    public void printCurrentCard(Participant player) {
        System.out.printf("%s카드: %s\n", player.name(), outputFormatter.formatCards(player.cards()));
    }

    public void printDealerDraw(String dealerName) {
        System.out.printf("\n%s는 16이하라 한장의 카드를 더 받았습니다.\n", dealerName);
    }

    public void printDealerNoDraw(String dealerName) {
        System.out.printf("\n%s는 17이상이라 카드를 추가로 받지 않았습니다.\n", dealerName);
    }

    public void printCardsResult(Participant dealer, List<Participant> players) {
        String parsedDealerCards = outputFormatter.formatCards(dealer.cards());
        int dealerCardsSum = dealer.calculateCardsSum();
        System.out.printf("\n%s카드: %s - 결과: %d\n", dealer.name(), parsedDealerCards, dealerCardsSum);

        players.forEach(player -> {
            String parsedPlayerCards = outputFormatter.formatCards(player.cards());
            int playerCardsSum = player.calculateCardsSum();
            System.out.printf("%s카드: %s - 결과: %d\n", player.name(), parsedPlayerCards, playerCardsSum);

        });
    }

    public void printGameResults(GameResult gameResult) {
        int winCount = gameResult.calculateWinCount();
        int loseCount = gameResult.calculateLoseCount();
        System.out.println("\n## 최종 승패");
        System.out.printf("딜러: %d승 %d패\n", loseCount, winCount);
        gameResult.getAllParticipants()
                .forEach(participant -> {
                    GameResultStatus gameResultstatus = gameResult.getGameResultstatus(participant);
                    String resultMessage = outputFormatter.formatGameResult(gameResultstatus);
                    System.out.printf("%s: %s\n", participant.name(), resultMessage);
                });
    }
}
