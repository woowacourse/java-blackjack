package controller;

import dto.ParticipantsInitDTO;
import java.util.List;
import service.BlackjackService;
import util.DisplayFormatter;
import util.Parser;
import util.ServiceLocator;
import util.Validator;
import view.InputView;
import view.Message;
import view.OutputView;
import vo.Money;

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
        initParticipants();
        dealInitialCards();
        processAllPlayersHitOrStand();
        processDealerTurn();
        printFinalResult();
        printProfitResult();
    }

    private void initParticipants() {
        List<String> participantsNames = readPlayersName();
        List<ParticipantsInitDTO> participantsInitDTOS = participantsNames.stream()
                .map(userName -> new ParticipantsInitDTO(userName, readBettingMoney(userName)))
                .toList();
        blackjackService.initParticipant(participantsInitDTOS);
    }

    private void printFinalResult() {
        outputView.printMessage(blackjackService.makeDealerFinalResultDisplay());
        blackjackService.makeUserFinalResultDisplay().forEach(outputView::printMessage);
    }

    private void printProfitResult() {
        outputView.printMessage(Message.FINAL_PROFIT_ANNOUNCE);
        blackjackService.evaluateGame().forEach(outputView::printMessage);
    }

    private void processDealerTurn() {
        blackjackService.calculateDealerScore();
        String dealerDrawMessage = dealExtraCardToDealer();
        outputView.printMessage(dealerDrawMessage);
    }

    private String dealExtraCardToDealer() {
        return blackjackService.dealExtraCardIfNeeded();
    }

    private void dealInitialCards() {
        blackjackService.makeDeck();
        blackjackService.dealInitialCards();
        printDealResult();
    }

    private void printDealResult() {
        outputView.printMessage(blackjackService.makeUserNameFormat());
        outputView.printMessage(blackjackService.makeDealerOneCardDisplay());
        blackjackService.getUserCardsDisplays().forEach(outputView::printMessage);
    }

    private List<String> readPlayersName() {
        while (true) {
            try {
                outputView.printMessage(Message.INPUT_PARTICIPANTS_MESSAGE);
                String playersName = inputView.readPlayersName();
                validator.validatePlayersName(playersName);
                return parser.parsePlayersName(playersName);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Money readBettingMoney(String userName) {
        String getBettingMoneyRequestMessage = DisplayFormatter.formatBettingMoneyRequest(userName);
        while (true) {
            try {
                outputView.printMessage(getBettingMoneyRequestMessage);
                String bettingMoney = inputView.readBettingMoney();
                validator.validateEmptyBettingMoney(bettingMoney);
                Money parsedBettingMoney = parser.parseBettingMoney(bettingMoney);
                validator.validateNegativeBettingMoney(parsedBettingMoney.getValue());
                return parsedBettingMoney;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void processAllPlayersHitOrStand() {
        List<String> requestMessages = extraCardRequest();
        for (int index = 0; index < requestMessages.size(); index++) {
            processOnePlayerHitOrStand(index, requestMessages.get(index));
        }
    }

    private List<String> extraCardRequest() {
        return blackjackService.makeExtraCardRequests();
    }

    private void processOnePlayerHitOrStand(int index, String message) {
        while (canKeepPlaying(index)) {
            String answer = readHitOrStand(message);
            processHitOrStand(answer, index);

            if (isStopCommand(answer)) {
                break;
            }
        }
    }

    private String readHitOrStand(String message) {
        while (true) {
            try {
                outputView.printMessage(message);
                String answer = inputView.readYesOrNo();
                validator.validateAnswer(answer);
                return answer;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void processHitOrStand(String answer, int index) {
        String cardDrawMessage = "";
        if (answer.equalsIgnoreCase("y")) {
            cardDrawMessage = blackjackService.hit(index);
        }
        if (answer.equalsIgnoreCase("n")) {
            cardDrawMessage = blackjackService.stand(index);
        }
        outputView.printMessage(cardDrawMessage);
    }

    private boolean canKeepPlaying(int index) {
        return !blackjackService.isBust(index);
    }

    private boolean isStopCommand(String answer) {
        return answer.equalsIgnoreCase("n");
    }
}
