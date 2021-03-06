package blackjack.controller;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = new BlackJackGame();
        Players players = initializePlayers(blackJackGame);
        askPlayersDraw(blackJackGame, players);
        OutputView.printResult(blackJackGame.calculateResult(players));
    }

    private Players initializePlayers(final BlackJackGame blackJackGame) {
        Players players = blackJackGame.createPlayers(InputView.askPlayerNames());
        OutputView.printPlayersCardsInformation(players);
        return players;
    }

    private void askPlayersDraw(final BlackJackGame blackJackGame, final Players players) {
        for (Player player : players.getGamblers()) {
            giveGamblerCards(blackJackGame, player);
        }
        giveDealerCards(blackJackGame);
        OutputView.printLineSeparator();
    }

    private void giveGamblerCards(final BlackJackGame blackJackGame, final Player player) {
        while (InputView.askDrawOrNot(player).isYes()) {
            blackJackGame.giveCard(player);
            OutputView.printPlayerCards(player);
        }
    }

    private void giveDealerCards(final BlackJackGame blackJackGame) {
        while (blackJackGame.isDealerAbleToDraw()) {
            blackJackGame.giveDealerCard();
            OutputView.printGiveDealer();
        }
    }
}
