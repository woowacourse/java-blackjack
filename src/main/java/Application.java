import domain.BlackjackGame;
import domain.User;
import java.util.List;
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
        printInitialCardInformation();
        selectToDealExtraCard();
        dealDealerCard();
        printEachHand();
        printWinningResult();
    }

    public void readParticipants() {
        retryUntilSuccess(() -> {
            outputView.printMessage(Message.INPUT_PARTICIPANTS_MESSAGE);
            blackjackGame.prepare(inputView.readParticipantsName());
            return null;
        });
    }

    private void printInitialCardInformation() {
        outputView.printDealComplete(blackjackGame.getParticipantNames());
        outputView.printDealerFirstCard(blackjackGame.getDealer().getFirstCard());
        blackjackGame.getUsers().forEach(outputView::printUserCards);
    }

    public void selectToDealExtraCard() {
        List<User> participants = blackjackGame.getUsers();
        for (User participant : participants) {
            askCardToPlayer(participant);
        }
    }

    private void askCardToPlayer(User user) {
        String answer;
        do {
            outputView.printAskExtraCard(user.getName());
            answer = retryUntilSuccess(inputView::readDealDecision);
            dealMoreCard(answer, user);
        } while (answer.equalsIgnoreCase("y"));
    }

    private void dealMoreCard(String answer, User user) {
        if (answer.equalsIgnoreCase("y")) {
            blackjackGame.processPlayerDecision(user);
            outputView.printUserCards(user);
        }
    }

    private void dealDealerCard() {
        while (blackjackGame.dealToDealer()) {
            outputView.printDealerReceivedCard();
        }
    }

    private void printEachHand() {
        outputView.printDealerFinalHand(blackjackGame.getDealer());
        blackjackGame.getUsers().forEach(outputView::printUserFinalHand);
    }

    private void printWinningResult() {
        outputView.printMessage(Message.FINAL_RESULT_ANNOUNCE);
        outputView.printWinningResults(blackjackGame.getResult());
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
