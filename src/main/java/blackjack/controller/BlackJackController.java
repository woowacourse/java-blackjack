package blackjack.controller;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = new BlackJackGame(InputView.askPlayerNames());
        askGamblerBet(blackJackGame);
        OutputView.printInitialCards(blackJackGame.getDealer(), blackJackGame.getGamblers());
        drawAdditionalCards(blackJackGame);
        OutputView.printResult(blackJackGame.getResult());
    }

    private void askGamblerBet(BlackJackGame blackJackGame) {
        for (Gambler gambler : blackJackGame.getGamblers()) {
            blackJackGame.bet(gambler, InputView.askBettingMoney(gambler));
        }
    }

    private void drawAdditionalCards(final BlackJackGame blackJackGame) {
        for (Player gambler : blackJackGame.getGamblers()) {
            askGamblerDraw(blackJackGame, gambler);
        }
        giveDealerCards(blackJackGame);
    }

    private void askGamblerDraw(final BlackJackGame blackJackGame, final Player gambler) {
        while (InputView.askDrawOrNot(gambler).isYes()) {
            blackJackGame.giveCard(gambler);
            OutputView.printPlayerCardsInformation(gambler);
        }
    }

    private void giveDealerCards(final BlackJackGame blackJackGame) {
        Dealer dealer = blackJackGame.getDealer();
        while (dealer.ableToDraw()) {
            blackJackGame.giveCard(dealer);
            OutputView.informDealerReceived();
        }
    }
}
