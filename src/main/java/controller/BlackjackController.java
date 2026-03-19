package controller;

import dto.DealerResultDTO;
import dto.ParticipantsInitDTO;
import dto.ProfitResultDTO;
import dto.UserCardsDTO;
import dto.UserResultDTO;
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
        DealerResultDTO dealerResultDTO = blackjackService.makeDealerFinalResult();
        List<UserResultDTO> userResultDTOS = blackjackService.makeUserFinalResult();
        outputView.printFinalResult(dealerResultDTO, userResultDTOS);
    }

    private void printProfitResult() {
        outputView.printMessage(Message.FINAL_PROFIT_ANNOUNCE);
        ProfitResultDTO profitResultDTO = blackjackService.evaluateGame();
        outputView.printProfitResult(profitResultDTO);
    }

    private void processDealerTurn() {
        blackjackService.calculateDealerScore();
        if (blackjackService.dealExtraCardIfNeeded()) {
            outputView.printDealerDrawMessage();
        }
    }

    private void dealInitialCards() {
        blackjackService.makeDeck();
        blackjackService.dealInitialCards();
        printDealResult();
    }

    private void printDealResult() {
        List<String> userNames = blackjackService.getParticipantsNames();
        outputView.printInitMessages(userNames);
        outputView.printDealerFirstCard(blackjackService.getDealerFirstCard());
        outputView.printUserCards(blackjackService.getUserCards());
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
        List<String> playerNames = blackjackService.getPlayerNames();
        for (int index = 0; index < playerNames.size(); index++) {
            String name = playerNames.get(index);
            processOnePlayerHitOrStand(index, name);
        }
    }

    private void processOnePlayerHitOrStand(int index, String name) {
        while (canKeepPlaying(index)) {
            outputView.printExtraCardRequest(name);
            String answer = readHitOrStand();
            UserCardsDTO userCardsDTO = processHitOrStand(answer, index);
            outputView.printSingleUserCards(userCardsDTO);

            if (isStopCommand(answer)) {
                break;
            }
        }
    }

    private boolean canKeepPlaying(int index) {
        return !blackjackService.isBust(index);
    }

    private String readHitOrStand() {
        while (true) {
            try {
                String answer = inputView.readYesOrNo();
                validator.validateAnswer(answer);
                return answer;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private UserCardsDTO processHitOrStand(String answer, int index) {
        if (answer.equalsIgnoreCase("y")) {
            return blackjackService.hit(index);
        }
        return blackjackService.stand(index);
    }

    private boolean isStopCommand(String answer) {
        return answer.equalsIgnoreCase("n");
    }
}
