package blackjack.controller;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = new BlackJackGame();
        Players players = initializePlayers(blackJackGame);
        askPlayersDraw(blackJackGame, players);
        OutputView.printResult(blackJackGame.getResult(players));
    }

    private Players initializePlayers(final BlackJackGame blackJackGame){
        Players players = blackJackGame.createPlayers(InputView.askPlayerNames());
        OutputView.printPlayersCardsInformation(players);
        return players;
    }

    private void askPlayersDraw(final BlackJackGame blackJackGame, final Players players){
        for (Player player : players) {
            giveGamblerCards(blackJackGame, player);
        }
        giveDealerCards(blackJackGame);
        OutputView.printLineSeparator();
    }

    private void giveGamblerCards(final BlackJackGame blackJackGame, final Player player) {
        if (!(player instanceof Gambler)) {
            return;
        }

        while (InputView.askDrawOrNot(player).isYes() ) {
            blackJackGame.giveCard(player);
            OutputView.printPlayerCardsInformation(player);
        }
    }

    private void giveDealerCards(final BlackJackGame blackJackGame){
        while(blackJackGame.ableToDraw()){
            blackJackGame.giveDealerCard();
            OutputView.printGiveDealer();
        }
    }
}
