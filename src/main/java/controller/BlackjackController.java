package controller;

import domain.Player;
import dto.BlackjackResultDto;
import dto.DealerResultDto;
import dto.PlayerResultDto;
import dto.PlayersDto;
import java.util.List;
import service.BlackjackService;
import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final BlackjackService blackjackService = new BlackjackService();

    public void start() {
        PlayersDto playersDto = inputPlayers();
        playersDto = blackjackService.dealInitialCards(playersDto);
        outputView.printPlayers(playersDto);
        outputView.printHandList(blackjackService.generaterHandDtoList(playersDto));
        inputHitOrStandOnPlayer(playersDto);

        boolean dealerPick = blackjackService.drawDealerCard(playersDto.dealer());
        if (dealerPick) {
            outputView.printDealerHit();
        }

        List<BlackjackResultDto> blackjackResult = getBlackjackResult(playersDto);
        outputView.printBlackjackResult(blackjackResult);
        DealerResultDto dealerResultDto = blackjackService.calculateDealerResult(playersDto);
        List<PlayerResultDto> playerResultDtoList = blackjackService.calculatePlayerResults(
            playersDto);
        outputView.printBlackjackStatistics(dealerResultDto, playerResultDtoList);
    }

    private void inputHitOrStandOnPlayer(PlayersDto playersDto) {
        for (Player player : playersDto.players()) {
            inputHitOrStand(player);
        }
    }

    private PlayersDto inputPlayers() {
        List<String> input = Parser.parseInput(inputView.inputPlayers(), ",");
        return blackjackService.createPlayers(input);
    }

    private void inputHitOrStand(Player player) {
        String hitOrStand = inputView.inputHitOrStand(player.getName());
        blackjackService.validateHitOrStand(hitOrStand); // 이 검증이 서비스의 역할인가? 그건 잘 모르겠어. Validator?
        if (blackjackService.isNo(hitOrStand)) {
            outputView.printHand(blackjackService.generateHandDto(player));
            System.out.println();
            return;
        }

        drawCardOnPlayer(player, hitOrStand);
    }

    private void drawCardOnPlayer(Player player, String hitOrStand) {
        while (blackjackService.shouldRepeat(player, hitOrStand)) {
            blackjackService.updatePlayer(player);
            outputView.printHand(blackjackService.generateHandDto(player));
            System.out.println();
            hitOrStand = inputView.inputHitOrStand(player.getName());
            blackjackService.validateHitOrStand(hitOrStand);
        }
    }

    public List<BlackjackResultDto> getBlackjackResult(PlayersDto playersDto) {
        return blackjackService.generateBlackjackResultDto(playersDto);
    }


}
