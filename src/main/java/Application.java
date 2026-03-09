import java.util.List;
import domain.BlackjackGame;
import util.Parser;
import util.ServiceLocator;
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

    public Application() {
        this.outputView = ServiceLocator.getOutputView();
        this.inputView = ServiceLocator.getInputView();
        this.parser = ServiceLocator.getParser();
        this.blackjackGame = ServiceLocator.getBlackjackService();
        this.validator = ServiceLocator.getValidator();
    }

    public static void main(String[] args) {
        new Application().run();
    }

    public void run() {
        readParticipants();
        shuffleCards();
        readExtraCardCommand();
        dealDealerCard();
        printFinalResult();
        printWinningResult();
    }

    private void printWinningResult() {
        outputView.printMessage(Message.FINAL_RESULT_ANNOUNCE);
        blackjackGame.evaluateGame().forEach(outputView::printMessage);
    }

    private void printFinalResult() {
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

    private void shuffleCards() {
        blackjackGame.makeDeck();
        blackjackGame.dealCards();
        printDealResult();
    }

    private void printDealResult() {
        outputView.printMessage(blackjackGame.makeUserNameFormat());
        outputView.printMessage(blackjackGame.makeDealerCardsDisplay());
        blackjackGame.getUserCardsDisplays().forEach(outputView::printMessage);
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

    public void readExtraCardCommand() {
        List<String> getUsersRequestMessages = extraCardRequest();

        for(int index = 0; index < getUsersRequestMessages.size(); index++) {
            boolean flag = true;
            while(flag) {
                try {
                    outputView.printMessage(getUsersRequestMessages.get(index));
                    String answer = inputView.readYesOrNo();
                    validator.validateAnswer(answer);
                    determinePlayerContinue(answer, index);
                    flag = false;
                } catch (IllegalArgumentException e) {
                    outputView.printErrorMessage(e.getMessage());
                }
            }
        }
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

    private List<String> extraCardRequest() {
        return blackjackGame.makeExtraCardRequsts();
    }
}
