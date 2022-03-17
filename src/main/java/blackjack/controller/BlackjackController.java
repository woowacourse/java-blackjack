package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Command;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.domain.entry.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputNames());
        List<Participant> participants = blackjackGame.getParticipant();
        List<Player> players = blackjackGame.getPlayers();
        betMoney(players);

        OutputView.printPlayersDefaultCard(participants);

        hit(blackjackGame, players);

        OutputView.printCardResult(blackjackGame.getCardResult(participants));
        OutputView.printGameResult(blackjackGame.getGameResult());
    }

    private void betMoney(List<Player> players) {
        for (Player player: players) {
            player.initBettingMoney(InputView.inputBettingMoney(player));
        }
    }

    private void hit(BlackjackGame blackjackGame, List<Player> players) {
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
