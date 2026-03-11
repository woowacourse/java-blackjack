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
        String inputPlayerNames = view.inputPlayerNames();
        InputValidator.validatePlayerNames(inputPlayerNames);
        InitialDto initialDto = blackJackService.createPlayer(StringParser.parse(inputPlayerNames));
        blackJackService.getAllPlayers().forEach(this::createBetting);
        view.outputInitialMessage(initialDto);

        blackJackService.getAllPlayers().forEach(this::getAdditionalCard);
        getAdditionalDealerCard();

        ResultDto resultDto = blackJackService.judgement();
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
            PlayerResultDto playerResultDto = blackJackService.additionalCard(player);
            view.outputPlayerDeckDtos(playerResultDto);
            getAdditionalCard(player);
        }
    }

    public void getAdditionalDealerCard() {
        boolean dealerCanAppend = blackJackService.isDealerCanAppend();
        if (dealerCanAppend) {
            blackJackService.additionalDealerCard();
            view.outputDealerAdditionCardMessage();
            getAdditionalDealerCard();
        }
    }
}
