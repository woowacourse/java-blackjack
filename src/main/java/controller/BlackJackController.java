package controller;

import domain.model.Player;
import domain.service.BlackJackService;
import dto.InitialDto;
import dto.PlayerResultDto;
import dto.ResultDto;
import util.InputValidator;
import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final BlackJackService blackJackService;
    private final InputView inputView;
    private final OutputView outputView;
    private final Parser<String> stringParser;

    public BlackJackController(BlackJackService blackJackService, InputView inputView, OutputView outputView, Parser<String> stringParser) {
        this.blackJackService = blackJackService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.stringParser = stringParser;
    }

    public void run() {
        // 사용자 이름 입력 후 초기 카드 분배 출력
        String inputPlayerNames = inputView.inputPlayerNames();
        InputValidator.validatePlayerNames(inputPlayerNames);
        InitialDto initialDto = blackJackService.createPlayer(stringParser.splitToDelimiter(inputPlayerNames, ","));
        outputView.outputInitialMessage(initialDto);

        // 카드 추가 분배
        blackJackService.getAllPlayers().forEach(this::getAdditionalCard);

        // 딜려 추가 배부
        getAdditionalDealerCard();

        // 결과 출력
        ResultDto judgement = blackJackService.judgement();
        outputView.playerResultMessage(judgement);
    }

    public void getAdditionalCard(Player player) {
        if (player.isBurst()) return;

        String isTrue = inputView.inputAdditionalCard(player.getName());
        InputValidator.validateAdditionalCard(isTrue);
        if (isTrue.equals("y")) {
            PlayerResultDto playerResultDto = blackJackService.additionalCard(player);
            outputView.outputPlayerDeckDtos(playerResultDto);
            getAdditionalCard(player);
        }
    }

    public void getAdditionalDealerCard() {
        boolean dealerCanAppend = blackJackService.isDealerCanAppend();
        if (dealerCanAppend) {
            blackJackService.additionalDealerCard();
            outputView.outputDealerAdditionCardMessage();
            getAdditionalDealerCard();
        }
    }
}
