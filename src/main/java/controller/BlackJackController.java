package controller;

import domain.model.Player;
import domain.service.BlackJackService;
import dto.InitialDto;
import dto.PlayerResultDto;
import dto.ResultDto;
import util.InputValidator;
import util.StringParser;
import view.View;

public class BlackJackController {

    private final BlackJackService blackJackService;
    private final View view;

    public BlackJackController(BlackJackService blackJackService, View view) {
        this.blackJackService = blackJackService;
        this.view = view;
    }

    public void run() {
        initializeGame();
        handleAdditionalCardPhase();
        displayGameResult();
    }

    private void initializeGame() {
        InitialDto initialDto = createPlayers();
        createBets();
        view.outputInitialMessage(initialDto);
    }

    private void createBets() {
        blackJackService.getAllPlayers().forEach(this::createBetting);
    }

    private InitialDto createPlayers() {
        String inputPlayerNames = view.inputPlayerNames();
        InputValidator.validatePlayerNames(inputPlayerNames);
        return blackJackService.createPlayer(StringParser.parse(inputPlayerNames));
    }

    private void handleAdditionalCardPhase() {
        blackJackService.getAllPlayers().forEach(this::getAdditionalCard);
        getAdditionalDealerCard();
    }

    private void displayGameResult() {
        ResultDto resultDto = blackJackService.getGameResult();
        view.playerResultMessage(resultDto);
    }

    private void createBetting(Player player) {
        String bettingPriceInput = view.inputPlayerBetting(player.getName());
        InputValidator.validateBettingPrice(bettingPriceInput);
        blackJackService.createBetting(player, Integer.parseInt(bettingPriceInput));
    }

    public void getAdditionalCard(Player player) {
        if (player.isBurst()) return;

        String isTrue = view.inputAdditionalCard(player.getName());
        InputValidator.validateAdditionalCard(isTrue);
        if (isTrue.equals("y")) {
            PlayerResultDto playerResultDto = blackJackService.assignAdditionalCard(player);
            view.outputPlayerDeckDtos(playerResultDto);
            getAdditionalCard(player);
        }
    }

    public void getAdditionalDealerCard() {
        boolean dealerCanAppend = blackJackService.isDealerCanAppend();
        if (dealerCanAppend) {
            blackJackService.assignAdditionalDealerCard();
            view.outputDealerAdditionCardMessage();
            getAdditionalDealerCard();
        }
    }
}
