package controller;

import domain.BlackjackService;
import domain.Name;
import domain.Player;
import dto.PlayerDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackjackGame {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        List<String> names = inputView.readNames();
        List<Player> players = names.stream()
                .map(name -> new Player(new Name(name)))
                .toList();
        Player dealer = new Player(new Name("딜러"));
        BlackjackService blackjackService = new BlackjackService(dealer, players);

        play(blackjackService);
    }

    private void play(BlackjackService blackjackService) {
        startSetting(blackjackService);
        proceedPlayerTurn(blackjackService);
        proceedDealerTurn(blackjackService);
        handleResult(blackjackService);
    }

    private void startSetting(BlackjackService blackjackService) {
        blackjackService.initialDistribute();
        Player dealer = blackjackService.getDealer();
        PlayerDto dealerDto = PlayerDto.from(dealer);
        List<PlayerDto> playersDto = blackjackService.getPlayers()
                .stream()
                .map(PlayerDto::from)
                .toList();
        outputView.printSetting(dealerDto, playersDto);
    }

    private void proceedPlayerTurn(BlackjackService blackjackService) {
        for (int playerIndex = 0; playerIndex < blackjackService.countPlayers(); playerIndex++) {
            proceedOnePlayerTurn(blackjackService, playerIndex);
        }
    }

    private void proceedDealerTurn(BlackjackService blackjackService) {
        while (blackjackService.isDealerNotOver()) {
            blackjackService.addCardToDealer();
            outputView.printDealerOneMoreCard();
        }
    }

    private void proceedOnePlayerTurn(BlackjackService blackjackService, int playerIndex) {
        while (blackjackService.isPlayerNotOver(playerIndex) &&
                inputView.readCommand(blackjackService.getPlayerName(playerIndex).getName())) {
            blackjackService.addCardToPlayer(playerIndex);
            Player player = blackjackService.getPlayer(playerIndex);
            outputView.printCurrentCard(PlayerDto.from(player));
            outputView.printNewLine();
        }
    }

    private void handleResult(BlackjackService blackjackService) {
        Player dealer = blackjackService.getDealer();
        PlayerDto dealerDto = PlayerDto.from(dealer);
        List<PlayerDto> playersDto = blackjackService.getPlayers()
                .stream()
                .map(PlayerDto::from)
                .toList();
        outputView.printScoreResult(dealerDto, playersDto);
        handleVictory(blackjackService);
    }

    private void handleVictory(BlackjackService blackjackService) {
        Map<Player, Boolean> playerBooleanMap = blackjackService.calculateVictory();
        Map<String, Boolean> playerDtoBooleanMap = new LinkedHashMap<>();
        playerBooleanMap.forEach(
                (key, value) -> playerDtoBooleanMap.put(key.getName().getName(), playerBooleanMap.get(key)));
        outputView.printResult(playerDtoBooleanMap);
    }
}
