package view;

import domain.*;
import view.mesage.OutputMessage;

import java.util.List;

public class OutputView {

    public void initialDrawMessage(List<String> names) {
        printEmptyLine();
        System.out.printf(
                OutputMessage.DEAL_INITIAL_CARDS.getMessage(),
                String.join(OutputMessage.DELIMITER.getMessage(), names)
        );
        printEmptyLine();
    }

    public void printDealerCards(Dealer dealer) {
        System.out.printf(
                OutputMessage.PARTICIPANT_CARDS.getMessage(),
                OutputMessage.DEALER.getMessage(),
                String.join(OutputMessage.DELIMITER.getMessage(), dealer.getCards().getCardsInfo())
        );
        printEmptyLine();
    }

    public void printPlayersCards(Players players) {
        for (Player player : players.getPlayers()) {
            printPlayerCards(player);
        }
    }

    public void printPlayerCards(Player player) {
        System.out.printf(
                OutputMessage.PARTICIPANT_CARDS.getMessage(),
                player.getName(),
                String.join(OutputMessage.DELIMITER.getMessage(), player.getCards().getCardsInfo())
        );
        printEmptyLine();
    }

    public void dealerDrawMessage() {
        printEmptyLine();
        System.out.println(OutputMessage.DEALER_DRAW_CARD.getMessage());
        printEmptyLine();
    }

    public void printFinalResults(List<PlayerResultDto> playerResults) {
        System.out.println(OutputMessage.FINAL_MESSAGE.getMessage());

        long dealerProfit = -1 * playerResults.stream()
                .mapToLong(PlayerResultDto::profit)
                .sum();
        printFinalDealerResult(dealerProfit);
        printFinalPlayerResult(playerResults);
    }

    private void printFinalDealerResult(long profit) {
        System.out.printf(OutputMessage.DEALER_RESULT_FORMAT.getMessage(), profit);
        printEmptyLine();
    }

    private void printFinalPlayerResult(List<PlayerResultDto> playerResults) {
        for (PlayerResultDto playerResult : playerResults) {
            System.out.printf(OutputMessage.PLAYER_RESULT_FORMAT.getMessage(), playerResult.name(), playerResult.profit());
            printEmptyLine();
        }
    }

    public void printLine(String message) {
        System.out.println(message);
    }

    public void printEmptyLine() {
        System.out.println();
    }
}
