package controller;

import constant.PlayerAction;
import domain.bet.Money;
import domain.participant.Name;
import domain.participant.Names;
import domain.participant.Player;
import domain.participant.Players;
import dto.BlackjackProfitDto;
import dto.BlackjackResultDto;
import java.util.ArrayList;
import java.util.List;
import service.BlackjackService;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackService blackjackService;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackService = initializeService();
    }

    private BlackjackService initializeService() {
        Names names = Names.from(inputView.inputPlayers());
        List<Player> playerList = collectBets(names);
        return new BlackjackService(new Players(playerList));
    }

    public void start() {
        blackjackService.dealInitialCards();
        printPlayerCards();
        inputAllPlayerActions();
        boolean dealerHit = blackjackService.drawDealerCard();
        if (dealerHit) {
            outputView.printDealerHit();
        }
        printBlackjackResult();
        printBlackjackStatistics();
    }

    private List<Player> collectBets(Names names) {
        List<Player> players = new ArrayList<>();
        for (Name name : names.value()) {
            String input = inputView.inputBetAmount(name.value());
            players.add(new Player(name, Money.from(input)));
        }
        return players;
    }

    private void printPlayerCards() {
        outputView.printPlayers(blackjackService.getAllPlayerNames());
        outputView.printlnPlayer(blackjackService.getDealerPlayerDto());
        outputView.printPlayerList(blackjackService.getAllPlayerDto());
    }

    private void printBlackjackStatistics() {
        BlackjackProfitDto blackjackProfitDto = blackjackService.calculateBlackjackResult();
        outputView.printBlackjackStatistics(blackjackProfitDto);
    }

    private void printBlackjackResult() {
        List<BlackjackResultDto> blackjackResult = blackjackService.generateBlackjackResultDto();
        outputView.printBlackjackResult(blackjackResult);
    }

    private void inputAllPlayerActions() {
        int playerCount = blackjackService.getPlayerCount();
        for (int playerIndex = 0; playerIndex < playerCount; playerIndex++) {
            inputPlayerAction(playerIndex);
        }
    }

    private void inputPlayerAction(int playerIndex) {
        boolean isStandSelected = true;
        while (isStandSelected) {
            isStandSelected = isStandSelected(playerIndex);
        }
    }

    private boolean isStandSelected(int playerIndex) {
        String name = blackjackService.getPlayerName(playerIndex);
        String input = inputView.inputHitOrStand(name);
        if (PlayerAction.from(input).isStand()) {
            outputView.printlnPlayer(blackjackService.createPlayerDto(playerIndex));
            return false;
        }
        blackjackService.updatePlayer(playerIndex);
        outputView.printlnPlayer(blackjackService.createPlayerDto(playerIndex));
        return true;
    }
}
