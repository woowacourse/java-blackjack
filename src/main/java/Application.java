import java.util.List;
import domain.BlackjackGame;
import java.util.function.Supplier;
import view.InputView;
import view.Message;
import view.OutputView;

public class Application {
    private final OutputView outputView;
    private final InputView inputView;
    private final BlackjackGame blackjackGame;

    public Application(InputView inputView, OutputView outputView, BlackjackGame blackjackGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackGame = blackjackGame;
    }

    public static void main(String[] args) {
        new Application(
            new InputView(),
            new OutputView(),
            new BlackjackGame()
        ).run();
    }

    public void run() {
        readParticipants();
        shuffleCards();
        selectToDealExtraCard();
        dealDealerCard();
        printEachHand();
        printWinningResult();
    }

    public void readParticipants() {
        retryUntilSuccess(() -> {
            outputView.printMessage(Message.INPUT_PARTICIPANTS_MESSAGE);
            String participantsName = inputView.readParticipantsName();
            blackjackGame.saveParticipants(participantsName);
            return null;
        });
    }

    private void shuffleCards() {
        blackjackGame.makeDeck();
        blackjackGame.dealCards();
        printDealResult();
    }

    public void selectToDealExtraCard() {
        List<String> participantNames = blackjackGame.getParticipantNames();
        for (int index = 0; index < participantNames.size(); index++) {
            askCardToPlayer(participantNames.get(index), index);
        }
    }

    private void askCardToPlayer(String name, int index) {
        retryUntilSuccess(() -> {
            outputView.printAskExtraCard(name);
            String answer = inputView.readDealDecision().trim();
            determinePlayerContinue(answer, index);
            return null;
        });
    }

    private void printWinningResult() {
        outputView.printMessage(Message.FINAL_RESULT_ANNOUNCE);
        outputView.printWinningResults(
            blackjackGame.getDealerResults(),
            blackjackGame.getUserResults(),
            blackjackGame.getParticipantNames()
        );
    }

    private void printEachHand() {
        outputView.printDealerFinalHand(blackjackGame.getDealer());
        blackjackGame.getUsers().forEach(outputView::printUserFinalHand);
    }

    private void dealDealerCard() {
        if (blackjackGame.dealToDealer()) {
            outputView.printDealerReceivedCard();
        }
    }

    private void printDealResult() {
        outputView.printDealComplete(blackjackGame.getParticipantNames());
        outputView.printDealerFirstCard(blackjackGame.getDealer().getFirstCard());
        blackjackGame.getUsers().forEach(outputView::printUserCards);
    }

    private void determinePlayerContinue(String answer, int index) {
        if (answer.equalsIgnoreCase("y")) {
            blackjackGame.processPlayerDecision(index);
            outputView.printUserCards(blackjackGame.getUsers().get(index));
        }
    }

    private <T> T retryUntilSuccess(Supplier<T> action) {
        try {
            return action.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return retryUntilSuccess(action);
        }
    }
}