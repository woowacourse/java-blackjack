package controller;

import domain.model.Bets;
import domain.model.Player;
import domain.service.BlackJackService;
import dto.InitialDto;
import dto.PlayerResultDto;
import dto.ResultDto;
import java.util.List;
import util.InputValidator;
import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final BlackJackService blackJackService;
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(BlackJackService blackJackService, InputView inputView, OutputView outputView) {
        this.blackJackService = blackJackService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        // 사용자 이름 입력 후 초기 카드 분배 출력
        String inputPlayerNames = inputPlayers();
        InitialDto initialDto = blackJackService.createPlayer(Parser.splitToDelimiter(inputPlayerNames, ","));
        outputView.outputInitialMessage(initialDto);

        List<Player> players = blackJackService.getAllPlayers();

        players.forEach(this::getBetMoney);

        // 카드 추가 분배
        players.forEach(this::getAdditionalCard);
        // 딜러 추가 배부
        getAdditionalDealerCard();

        // 결과 출력
        ResultDto judgement = blackJackService.judgement();
        outputView.playerResultMessage(judgement);
    }

    public void getAdditionalCard(Player player) {
        if (player.isBurst()) {
            return;
        }

        String isTrue = readYesOrNo(player);
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

    public String inputPlayers() {
        String inputPlayerNames = inputView.inputPlayerNames();
        try {
            InputValidator.validatePlayerNames(inputPlayerNames);
        } catch (Exception e) {
            outputView.outputErrorMessage(e.getMessage());
            inputPlayers();
        }
        return inputPlayerNames;
    }

    public void getBetMoney(Player player) {
        String inputMoney = inputView.inputBetMoney(player.getName());
        int money = 0;
        try {
            money = Parser.toInt(inputMoney);
        } catch (Exception e) {
            outputView.outputErrorMessage(e.getMessage());
            getBetMoney(player);
        }
        blackJackService.addBet(player, money);
    }

    public String readYesOrNo(Player player) {
        String isTrue = inputView.inputAdditionalCard(player.getName());
        try {
            InputValidator.validateAdditionalCard(isTrue);
        } catch (Exception e) {
            outputView.outputErrorMessage(e.getMessage());
            readYesOrNo(player);
        }

        return isTrue;
    }
}
