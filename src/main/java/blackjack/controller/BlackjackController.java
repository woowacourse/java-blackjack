package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Command;
import blackjack.domain.PlayerName;
import blackjack.domain.entry.Dealer;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public void run() {
        List<PlayerName> playerNames = PlayerName.from(InputView.inputNames());
        BlackjackGame blackjackGame = new BlackjackGame(betMoney(playerNames));
        OutputView.printPlayersDefaultCard(blackjackGame.getParticipant());

        hit(blackjackGame, playerNames);

        OutputView.printCardResult(blackjackGame.getCardResult());
        OutputView.printGameResult(blackjackGame.getBettingResult());
    }

    private Map<PlayerName, Integer> betMoney(List<PlayerName> playerNames) {
        return playerNames.stream()
                .collect(Collectors.toMap(name -> name, InputView::inputBettingMoney));
    }

    private void hit(BlackjackGame blackjackGame, List<PlayerName> playerNames) {
        for (PlayerName playerName : playerNames) {
            hitPlayer(blackjackGame, playerName);
        }
        hitDealer(blackjackGame);
    }

    private void hitPlayer(BlackjackGame blackjackGame, PlayerName playerName) {
        Command command;

        do {
            command = Command.find(InputView.inputCommand(playerName));
            hit(blackjackGame, playerName, command);
            OutputView.printPlayerCards(blackjackGame.find(playerName));
        } while (blackjackGame.canHit(command, playerName));
    }

    private void hit(BlackjackGame blackjackGame, PlayerName playerName, Command command) {
        if (command.isHit()) {
            blackjackGame.hit(playerName);
        }
    }

    private void hitDealer(BlackjackGame blackjackGame) {
        while (blackjackGame.canDealerHit()) {
            blackjackGame.hit(Dealer.NAME);
            OutputView.printReceivingMoreCardOfDealer();
        }
    }
}
