import java.util.List;
import domain.BlackjackGame;
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
        while(true) {
            try {
                outputView.printMessage(Message.INPUT_PARTICIPANTS_MESSAGE);
                String participantsName = inputView.readParticipantsName();
                List<String> parsedParticipantsName = parser.parseParticipantsName(participantsName);
                blackjackGame.saveParticipants(parsedParticipantsName);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void shuffleCards() {
        blackjackGame.makeDeck();
        blackjackGame.dealCards();
        printDealResult();
    }

    public void selectToDealExtraCard() {
        List<String> requestMessages = getExtraCardRequestMessages();

        for(int index = 0; index < requestMessages.size(); index++) {
            boolean flag = true;
            while(flag) {
                try {
                    outputView.printMessage(requestMessages.get(index));
                    String answer = inputView.readDealDecision();
                    validator.validateAnswer(answer);
                    determinePlayerContinue(answer, index);
                    flag = false;
                } catch (IllegalArgumentException e) {
                    outputView.printErrorMessage(e.getMessage());
                }
            }
        }
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
        calculateDealerScore();
        String dealerReceivedCard = determineDealToDealer();
        outputView.printMessage(dealerReceivedCard);
    }

    private String determineDealToDealer() {
        return blackjackGame.determineDealToDealer();
    }

    private void calculateDealerScore() {
        blackjackGame.calculateDealerScore();
    }

    private void printDealResult() {
        outputView.printMessage(blackjackGame.makeUserCardsDisplay());
        outputView.printMessage(blackjackGame.makeDealerCardsDisplay());
        blackjackGame.getUserCardsDisplays().forEach(outputView::printMessage);
    }

    private void determinePlayerContinue(String answer, int index) {
        if (answer.equalsIgnoreCase("y")) {
            String cardDrawMessage = blackjackGame.processPlayerDecision(index);
            outputView.printMessage(cardDrawMessage);
        }
        if (answer.equalsIgnoreCase("n")) {
            blackjackGame.calculateScore(index);
        }
    }

    private List<String> getExtraCardRequestMessages() {
        return blackjackGame.makeExtraCardRequests();
    }
}
