import domain.BlackjackGame;
import domain.User;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import view.InputView;
import view.Message;
import view.OutputView;
import vo.Bet;
import vo.Name;

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
        prepareParticipants();
        printInitialCardInformation();
        selectToDealExtraCard();
        dealDealerCard();
        printEachHand();
        printWinningResult();
    }

    public void prepareParticipants() {
        List<Name> names = readParticipants();
        Map<Name, Bet> bets = readBetAmount(names);
        blackjackGame.prepare(names, bets);
    }

    private List<Name> readParticipants() {
        return retryUntilSuccess(() -> {
            outputView.printMessage(Message.INPUT_PARTICIPANTS_MESSAGE);
            return inputView.readParticipantsName();
        });
    }

    private Map<Name, Bet> readBetAmount(List<Name> names) {
        Map<Name, Bet> bets = new LinkedHashMap<>();
        for (Name name : names) {
            Bet bet = retryUntilSuccess(() -> {
                outputView.printAskBetAmount(name.getName());
                return inputView.readBetAmount();
            });
            bets.put(name, bet);
        }
        return bets;
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

    private void askCardToPlayer(User user) {
        while (wantExtraCard(user)) {
            dealMoreCard(user);
        }
    }

    private boolean wantExtraCard(User user) {
        if (user.checkBust()) {
            outputView.printMessage(Message.BUST_ANNOUNCE);
            return false;
        }
        outputView.printAskExtraCard(user.getName());
        return retryUntilSuccess(inputView::readDealDecision);
    }

    private void dealMoreCard(User user) {
        blackjackGame.processPlayerDecision(user);
        outputView.printUserCards(user);
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
