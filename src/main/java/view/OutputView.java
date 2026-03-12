package view;

import domain.*;
import meesage.OutputMessage;

import java.util.List;
import java.util.Map;

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

    public void printFinalResult(BlackjackResult blackjackResult) {
        System.out.println(OutputMessage.FINAL_MESSAGE.getMessage());
        printFinalDealerResult(blackjackResult);
        printFinalPlayerResult(blackjackResult);
    }

    private void printFinalDealerResult(BlackjackResult blackjackResult) {
        System.out.printf(OutputMessage.DEALER_RESULT_FORMAT.getMessage(), blackjackResult.dealerProfit());
        printEmptyLine();
    }

    private void printFinalPlayerResult(BlackjackResult blackjackResult) {
        for (Map.Entry<Player, BetResult> result : blackjackResult.getPlayerBets().entrySet()) {
            Player player = result.getKey();
            long playerProfit = blackjackResult.playerProfit(result);

            System.out.printf(OutputMessage.PLAYER_RESULT_FORMAT.getMessage(), player.getName(), playerProfit);
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
