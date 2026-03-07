package controller;

import constant.PolicyConstant;
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
        PlayersDto playersDto = initializeGame();
        inputHitOrStandOnPlayer(playersDto);
        boolean dealerPick = blackjackService.drawDealerCard(playersDto.dealer());
        if (dealerPick) {
            outputView.printDealerHit();
        }
        printBlackjackResult(playersDto);
        printBlackjackStatistics(playersDto);
    }

    private void printBlackjackStatistics(PlayersDto playersDto) {
        DealerResultDto dealerResultDto = blackjackService.calculateDealerResult(playersDto);
        List<PlayerResultDto> playerResultDtoList = blackjackService.calculatePlayerResults(
            playersDto);
        outputView.printBlackjackStatistics(dealerResultDto, playerResultDtoList);
    }

    private void printBlackjackResult(PlayersDto playersDto) {
        List<BlackjackResultDto> blackjackResult = getBlackjackResult(playersDto);
        outputView.printBlackjackResult(blackjackResult);
    }

    private PlayersDto initializeGame() {
        PlayersDto playersDto = inputPlayers();
        playersDto = blackjackService.dealInitialCards(playersDto);
        outputView.printPlayers(playersDto);
        outputView.printHandList(blackjackService.generaterHandDtoList(playersDto));
        return playersDto;
    }

    private void inputHitOrStandOnPlayer(PlayersDto playersDto) {
        for (Player player : playersDto.players()) {
            inputHitOrStand(player);
        }
    }

    private PlayersDto inputPlayers() {
        List<String> input = Parser.parseInput(inputView.inputPlayers(), PolicyConstant.DELIMITER);
        return blackjackService.createPlayers(input);
    }

    private void inputHitOrStand(Player player) {
        String hitOrStand = inputView.inputHitOrStand(player.getName());
        blackjackService.validateHitOrStand(hitOrStand);
        if (blackjackService.isNo(hitOrStand)) {
            outputView.printlnHand(blackjackService.generateHandDto(player));
            return;
        }

        drawCardOnPlayer(player, hitOrStand);
    }

    private void drawCardOnPlayer(Player player, String hitOrStand) {
        while (blackjackService.shouldRepeat(player, hitOrStand)) {
            blackjackService.updatePlayer(player);
            outputView.printlnHand(blackjackService.generateHandDto(player));
            hitOrStand = inputView.inputHitOrStand(player.getName());
            blackjackService.validateHitOrStand(hitOrStand);
        }
    }

    public List<BlackjackResultDto> getBlackjackResult(PlayersDto playersDto) {
        return blackjackService.generateBlackjackResultDto(playersDto);
    }
}
