import java.util.List;
import domain.BlackjackGame;
import java.util.function.Supplier;
import util.Parser;
import util.Validator;
import view.InputView;
import view.Message;
import view.OutputView;

public class Application {
    private final OutputView outputView;
    private final InputView inputView;
    private final Parser parser;
    private final BlackjackGame blackjackGame;
    private final Validator validator;

    public Application(InputView inputView, OutputView outputView, Parser parser, Validator validator, BlackjackGame blackjackGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.parser = parser;
        this.validator = validator;
        this.blackjackGame = blackjackGame;
    }

    public static void main(String[] args) {
        new Application(
            new InputView(),
            new OutputView(),
            new Parser(),
            new Validator(),
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
            List<String> parsedParticipantsName = parser.parseParticipantsName(participantsName);
            blackjackGame.saveParticipants(parsedParticipantsName);
            return null;
        });
    }

    private void shuffleCards() {
        blackjackGame.makeDeck();
        blackjackGame.dealCards();
        printDealResult();
    }

    public void selectToDealExtraCard() {
        List<String> requestMessages = getExtraCardRequestMessages();

        for(int index = 0; index < requestMessages.size(); index++) {
            askCardToPlayer(requestMessages.get(index), index);
        }
    }

    private void askCardToPlayer(String message, int index) {
        retryUntilSuccess(() -> {
            outputView.printMessage(message);
            String answer = inputView.readDealDecision();
            validator.validateAnswer(answer);
            determinePlayerContinue(answer, index);
            return null;
        });
    }

    private void printWinningResult() {
        outputView.printMessage(Message.FINAL_RESULT_ANNOUNCE);
        blackjackGame.evaluateGame().forEach(outputView::printMessage);
    }

    private void printEachHand() {
        outputView.printMessage(blackjackGame.makeDealerFinalResultDisplay());
        blackjackGame.makeUserFinalResultDisplay().forEach(outputView::printMessage);
    }

    private void dealDealerCard() {
        String dealerReceivedCard = determineDealToDealer();
        outputView.printMessage(dealerReceivedCard);
    }

    private String determineDealToDealer() {
        return blackjackGame.determineDealToDealer();
    }

    private void printDealResult() {
        outputView.printMessage(blackjackGame.makeDealCompleteDisplay());
        outputView.printMessage(blackjackGame.makeDealerCardsDisplay());
        blackjackGame.getUserCardsDisplays().forEach(outputView::printMessage);
    }

    private void determinePlayerContinue(String answer, int index) {
        if (answer.equalsIgnoreCase("y")) {
            String cardDrawMessage = blackjackGame.processPlayerDecision(index);
            outputView.printMessage(cardDrawMessage);
        }
    }

    private List<String> getExtraCardRequestMessages() {
        return blackjackGame.makeExtraCardRequests();
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
