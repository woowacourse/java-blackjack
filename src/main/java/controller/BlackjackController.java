package controller;

import java.util.List;
import service.BlackjackService;
import util.Parser;
import util.ServiceLocator;
import util.Validator;
import view.InputView;
import view.Message;
import view.OutputView;

public class BlackjackController {
    private final OutputView outputView;
    private final InputView inputView;
    private final Parser parser;
    private final BlackjackService blackjackService;
    private final Validator validator;

    public BlackjackController() {
        this.outputView = ServiceLocator.getOutputView();
        this.inputView = ServiceLocator.getInputView();
        this.parser = ServiceLocator.getParser();
        this.blackjackService = ServiceLocator.getBlackjackService();
        this.validator = ServiceLocator.getValidator();
    }

    public void run() {
        readParticipants();
        shuffleCards();
        readExtraCardCommand();
        dealDealerCard();
    }

    private void dealDealerCard() {
        calculateDealerScore();
        determineDealToDealer();
    }

    private void determineDealToDealer() {
        blackjackService.determineDealToDealer();
    }

    private void calculateDealerScore() {
        blackjackService.calculateDealerScore();
    }



    private void shuffleCards() {
        blackjackService.makeDeck();
        blackjackService.dealCards();
        printDealResult();
    }

    private void printDealResult() {
        outputView.printMessage(blackjackService.makeUserNameFormat());
        outputView.printMessage(blackjackService.makeDealerCardsDisplay());
        blackjackService.getUserCardsDisplays().forEach(outputView::printMessage);
    }

    public void readParticipants() {
        while(true) {
            try {
                outputView.printMessage(Message.INPUT_PARTICIPANTS_MESSAGE);
                String participantsName = inputView.readParticipantsName();
                List<String> parsedParticipantsName = parser.parseParticipantsName(participantsName);
                blackjackService.saveParticipants(parsedParticipantsName);
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
            String cardDrawMessage = blackjackService.processPlayerDecision(index);
            outputView.printMessage(cardDrawMessage);
        }
    }

    private List<String> extraCardRequest() {
        return blackjackService.makeExtraCardRequsts();
    }
}
