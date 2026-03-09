package controller;

import domain.model.Player;
import domain.service.BlackJackService;
import dto.InitialDto;
import dto.PlayerResultDto;
import dto.ResultDto;
import util.InputValidator;
import util.Parser;
import view.View;

public class BlackJackController {

    private final BlackJackService blackJackService;
    private final View view;
    private final Parser<String> stringParser;

    public BlackJackController(BlackJackService blackJackService, View view, Parser<String> stringParser) {
        this.blackJackService = blackJackService;
        this.view = view;
        this.stringParser = stringParser;
    }

    public void run() {
        String inputPlayerNames = view.inputPlayerNames();
        InputValidator.validatePlayerNames(inputPlayerNames);
        InitialDto initialDto = blackJackService.createPlayer(stringParser.splitToDelimiter(inputPlayerNames, ","));
        view.outputInitialMessage(initialDto);

        blackJackService.getAllPlayers().forEach(this::getAdditionalCard);
        getAdditionalDealerCard();

        ResultDto resultDto = blackJackService.judgement();
        view.playerResultMessage(resultDto);
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
