package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Command;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public void run() {
        List<String> playerNames = InputView.inputNames();
        BlackjackGame blackjackGame = new BlackjackGame(betMoney(playerNames));
        List<Participant> participants = blackjackGame.getParticipant();
        OutputView.printPlayersDefaultCard(participants);

        hit(blackjackGame);

        OutputView.printCardResult(blackjackGame.getCardResult(participants));
        OutputView.printGameResult(blackjackGame.getBettingResult());
    }

    private Map<String, Integer> betMoney(List<String> playerNames) {
        return playerNames.stream()
                .collect(Collectors.toMap(name -> name, InputView::inputBettingMoney));
    }

    private void hit(BlackjackGame blackjackGame) {
        List<Player> players = blackjackGame.getPlayers();
        for (Player player : players) {
            hitPlayer(blackjackGame, player);
        }
        hitDealer(blackjackGame);
    }

    private void hitPlayer(BlackjackGame blackjackGame, Player player) {
        Command command;

        do {
            command = Command.find(InputView.inputCommand(player));
            hit(blackjackGame, player, command);
            OutputView.printPlayerCards(player);
        } while (command.isHit() && player.canHit());
    }

    private void hit(BlackjackGame blackjackGame, Player player, Command command) {
        if (command.isHit()) {
            blackjackGame.hit(player);
        }
    }

    private void hitDealer(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        while (dealer.canHit()) {
            blackjackGame.hit(dealer);
            OutputView.printReceivingMoreCardOfDealer();
        }
    }
}
